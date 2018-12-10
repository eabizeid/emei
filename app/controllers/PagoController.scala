package controllers

import java.time.LocalDate

import dao._
import javax.inject._
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.Forms.mapping
import play.api.mvc._
import slick.lifted.QueryBase

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class PagoController @Inject()(familiaDao: FamiliaDao, pagosDao: PagoDao, cuotaDao: CuotaDao, alumnosDao: AlumnoDao, inscripcionDao: InscripcionDao, cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {


  val buscarForm: Form[BuscarCuotasPorFamiliaForm] = Form {
    mapping(
      "familiaId" -> number,
      "valorCuotaBase" -> text,
      "deuda"-> text
    )(BuscarCuotasPorFamiliaForm.apply)(BuscarCuotasPorFamiliaForm.unapply)
  }

  val pagoForm: Form[PagoForm] = Form {
    mapping(
      "cuotasAPagar" -> text,
      "interes" -> text,
      "descuentoHermano" -> text,
      "descuentoEspecial" -> text,
      "totalAPagar" -> text,
      "recibo"->text,
      //"tipoPago"->text,
      "pagoParcial" -> boolean
    )(PagoForm.apply)(PagoForm.unapply)
  }

  def showPagarCuota = Action.async { implicit request =>

    familiaDao.findAllWithoutAlumnos(ec)  map { familias =>
      Ok(views.html.pagarCuota(buscarForm, familias))
    }
  }

  def buscar = Action.async {implicit request =>
    val familia = request.body.asFormUrlEncoded.get("familia").head
    pagosDao.findPagosPendientes(ec, familia.toInt ).map{pagos =>
      Ok(views.html.resultadosBuscarPagosPorFamilia(pagos))}

  }

  def generarObligacionDePagoMensual = Action.async { implicit request =>
    cuotaDao.cuotaExists(LocalDate.now().getYear, LocalDate.now().getMonthValue).map { existe =>
      if (existe) Redirect(routes.PagoController.showPagarCuota()).flashing("success" -> "Ya existe cuota baase para este mes y año")
      else {
        val valor = request.body.asFormUrlEncoded.get("valorCuotaBase").head
        val cuotaBase = cuotaDao.create(valor.toDouble, LocalDate.now().getYear, LocalDate.now().getMonthValue)
        cuotaBase.map { c =>
          alumnosDao.findAll.map { flias =>
            flias.map { f => pagosDao.create(c.id, f.id, valor.toDouble * f.alumnos.size) }
          }
        }
        Redirect(routes.PagoController.showPagarCuota()).flashing("success" -> "Cuotas generadas para todas las familias")
      }
    }
  }


  def generarInscripcion = Action.async { implicit request =>
    inscripcionDao.inscripcionExists(LocalDate.now().getYear + 1).map { existe =>
      if (existe) Redirect(routes.PagoController.showPagarCuota()).flashing("success" -> "Ya existe inscripcion generada para próximo año")
      else {
        val valor = request.body.asFormUrlEncoded.get("valorInscripcion").head
        val inscripcion = inscripcionDao.create(valor.toDouble, LocalDate.now().getYear + 1)
        inscripcion.map { c =>
          alumnosDao.findAll.map { flias =>
            flias.map { f => pagosDao.createPagoInscripcion(c.id, f.id, valor.toDouble * f.alumnos.size) }
          }
        }
        Redirect(routes.PagoController.showPagarCuota()).flashing("success" -> "Inscripciones generadas para todas las familias")
      }
    }
  }


  def generarPago = Action.async { implicit request =>

    val cuotasAPagar = request.body.asFormUrlEncoded.get("cuotasAPagar").map(_.toInt)



    val pagosAlumnos = for {
      pagos <-  pagosDao.getPagosPendientesByIds(ec, cuotasAPagar)
      alumnos<-  alumnosDao.findBy(ec, Some(pagos.head.familia), None, None, None)
    } yield (pagos, alumnos)
     pagosAlumnos.map {
       case (pagos, alumnos) =>  Ok(views.html.showPagar(pagoForm, sumarPagos(pagos), alumnos.head.alumnos.toList , cuotasAPagar.mkString(",")))
     }



  }


  def generarPagoInscripcion = Action.async { implicit request =>

    val cuotasAPagar = request.body.asFormUrlEncoded.get("inscripcionesAPagar").map(_.toInt)



    val pagosAlumnos = for {
      pagos <-  pagosDao.getInscripcionesPendientesByIds(ec, cuotasAPagar)
      alumnos<-  alumnosDao.findBy(ec, Some(pagos.head.familia), None, None, None)
    } yield (pagos, alumnos)
    pagosAlumnos.map {
      case (pagos, alumnos) =>  Ok(views.html.showPagarInscripcion(pagoForm, sumarInscripciones(pagos), alumnos.head.alumnos.toList , cuotasAPagar.mkString(",")))
    }



  }
  def realizarPago = Action.async {implicit request =>

    pagoForm.bindFromRequest.fold(
      errorForm => {
        val cuotasAPagar = request.body.asFormUrlEncoded.get("cuotasAPagar").head
        val pagosAlumnos = for {
          pagos <-  pagosDao.getPagosPendientesByIds(ec, cuotasAPagar.split(",").map(_.toInt))
          alumnos<-  alumnosDao.findBy(ec, Some(pagos.head.familia), None, None, None)
        } yield (pagos, alumnos)
        pagosAlumnos.map {
          case (pagos, alumnos) => Ok(views.html.showPagar(errorForm, sumarPagos(pagos), alumnos.head.alumnos.toList, cuotasAPagar))
        }
      },
      pago => {

          pagosDao.getPagosPendientesByIds(ec, pago.cuotasAPagar.split(",").map(_.toInt)).map { pagos =>
            var totalPagos = sumarPagos(pagos) //cuanto falta pagar
            if (!pago.pagoParcial) {
              var totalAPagar = pago.totalAPagar.toDouble
              pagos.map{ p =>
                pagosDao.resolver(p.id, totalAPagar, pago.descuentoHermano.toDouble + pago.descuentoEspecial.toDouble, pago.interes.toDouble, pago.recibo );
              }

            } else {

              var total = pago.totalAPagar.toDouble
              pagos.map { p=>
                if (total >= p.totalSinDescuento) {
                  pagosDao.resolver(p.id, p.valorCuotaBase, 0D, 0D, pago.recibo)
                  total= total - p.totalSinDescuento
                } else {
                  pagosDao.resolverParcial(p.id, total+ p.pagoParcial, pago.recibo)
                }

              }
            }
            Redirect(routes.PagoController.showPagarCuota()).flashing("success" -> "Pagos Realizados Correctamente")
        }
      }
    )
  }


  def realizarPagoInscripcion = Action.async {implicit request =>

    pagoForm.bindFromRequest.fold(
      errorForm => {
        val cuotasAPagar = request.body.asFormUrlEncoded.get("cuotasAPagar").head
        val pagosAlumnos = for {
          pagos <-  pagosDao.getInscripcionesPendientesByIds(ec, cuotasAPagar.split(",").map(_.toInt))
          alumnos<-  alumnosDao.findBy(ec, Some(pagos.head.familia), None, None, None)
        } yield (pagos, alumnos)
        pagosAlumnos.map {
          case (pagos, alumnos) => Ok(views.html.showPagar(pagoForm, sumarInscripciones(pagos), alumnos.head.alumnos.toList, cuotasAPagar))
        }
      },
      pago => {

        pagosDao.getInscripcionesPendientesByIds(ec, pago.cuotasAPagar.split(",").map(_.toInt)).map { inscripciones =>
          var totalPagos = sumarInscripciones(inscripciones) //cuanto falta pagar
          if (!pago.pagoParcial) {
            var totalAPagar = pago.totalAPagar.toDouble
            inscripciones.map{ p =>
              pagosDao.resolverInscripcion(p.id, totalAPagar, pago.descuentoHermano.toDouble + pago.descuentoEspecial.toDouble, pago.interes.toDouble, pago.recibo);
            }

          } else {

            var total = pago.totalAPagar.toDouble
            inscripciones.map { p=>
              if (total >= p.totalSinDescuento) {
                pagosDao.resolverInscripcion(p.id, p.monto, 0D, 0D, pago.recibo)
                total= total - p.totalSinDescuento
              } else {
                pagosDao.resolverInscripcionParcial(p.id, total+ p.pagoParcial, pago.recibo)
              }

            }
          }
          Redirect(routes.PagoController.showPagarCuota()).flashing("success" -> "Pagos Realizados Correctamente")
        }
      }
    )
  }

  def showCargarDeudaAnterior = Action.async { implicit request =>

    val familiaId = request.body.asFormUrlEncoded.get("familia").map(_.toInt).head

    familiaDao.getFamiliaById(ec, familiaId).map { f =>
      Ok(views.html.deudaAnterior(buscarForm, f.head))
    }

  }

  def cargarDeudaAnterior = Action.async { implicit request =>
    val familiaId = request.body.asFormUrlEncoded.get("familia").map(_.toInt).head
    val deuda: Double = request.body.asFormUrlEncoded.get("deuda").map(_.toDouble).head

    familiaDao.getFamiliaById(ec, familiaId).flatMap { f =>
      val familia = f.head
      familiaDao.update(familia.id, familia.descripcion, familia.observaciones, deuda).map { _ =>
        Redirect(routes.PagoController.showPagarCuota()).flashing("success" -> "Se cargo deuda correctamente")
      }
    }
  }


  def consultarDeudores = Action.async { implicit request =>
    familiaDao.getDeudores(ec).map {familias =>
      Ok(views.html.deudores(familias.toList))
    }
  }

  private def sumarPagos(pagos: List[PagoApi]): Double = {
    pagos match {
      case pago :: tail =>   determinarValorASumar(pago) + sumarPagos(tail) // if there is an element, add it to the sum of the tail
      case Nil => 0 // if there are no elements, then the sum is 0
    }
  }

  private def sumarInscripciones(inscripciones: List[PagoInscripcionApi]): Double = {
    inscripciones match {
      case inscripcion :: tail =>   determinarValorASumar(inscripcion) + sumarInscripciones(tail) // if there is an element, add it to the sum of the tail
      case Nil => 0 // if there are no elements, then the sum is 0
    }
  }

  private def determinarValorASumar (inscripcion: PagoInscripcionApi) = {
    if (inscripcion.pagoParcial == 0) {
      inscripcion.totalSinDescuento
    } else  inscripcion.totalSinDescuento - inscripcion.pagoParcial
  }


  private def determinarValorASumar (pago:PagoApi) = {
    if (pago.pagoParcial == 0) {
      pago.totalSinDescuento
    } else  pago.totalSinDescuento - pago.pagoParcial
  }

  def buscarInscripcionesPendientes = Action.async {implicit request =>
    val familia = request.body.asFormUrlEncoded.get("familia").head
    pagosDao.findInscripcionesPendientes(ec, familia.toInt ).map{inscripciones =>
      Ok(views.html.resultadosBuscarInscripcionesPorFamilia(inscripciones))}
  }
}
case class BuscarCuotasPorFamiliaForm(familiaId: Int, valorCuotaBase: String, deuda: String)
case class PagoForm (cuotasAPagar: String,interes:String, descuentoHermano: String, descuentoEspecial:String, totalAPagar: String, recibo:String, /*tipoPago: String,*/ pagoParcial: Boolean)


