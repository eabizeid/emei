package controllers

import java.time.LocalDate

import dao._
import javax.inject._
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.Forms.mapping
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class PagoController @Inject()(familiaDao: FamiliaDao, pagosDao: PagoDao, cuotaDao: CuotaDao, alumnosDao: AlumnoDao, cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  val buscarForm: Form[BuscarCuotasPorFamiliaForm] = Form {
    mapping(
      "familiaId" -> number,
      "valorCuotaBase" -> text
    )(BuscarCuotasPorFamiliaForm.apply)(BuscarCuotasPorFamiliaForm.unapply)
  }

  val pagoForm: Form[PagoForm] = Form {
    mapping(
      "cuotasAPagar" -> text,
      "totalAPagar" -> text,
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

  def generarObligacionDePagoMensual = Action.async{ implicit request =>
    val valor = request.body.asFormUrlEncoded.get("valorCuotaBase").head

    val cuotaBase = cuotaDao.create(valor.toDouble, LocalDate.now().getYear, LocalDate.now().getMonthValue)

    cuotaBase.map{ c =>
      familiaDao.findAllWithoutAlumnos.map{ flias =>
       flias.map { f =>pagosDao.create(c.id, f.id) }

        }
      Redirect(routes.PagoController.showPagarCuota()).flashing("success" -> "Cuotas generadas para todas las familias")

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

  def realizarPago = Action.async {implicit request =>

    pagoForm.bindFromRequest.fold(
      errorForm => {
        val cuotasAPagar = request.body.asFormUrlEncoded.get("cuotasAPagar").head
        val pagosAlumnos = for {
          pagos <-  pagosDao.getPagosPendientesByIds(ec, cuotasAPagar.split(",").map(_.toInt))
          alumnos<-  alumnosDao.findBy(ec, Some(pagos.head.familia), None, None, None)
        } yield (pagos, alumnos)
        pagosAlumnos.map {
          case (pagos, alumnos) => Ok(views.html.showPagar(pagoForm, sumarPagos(pagos), alumnos.head.alumnos.toList, cuotasAPagar))
        }
      },
      pago => {

          pagosDao.getPagosPendientesByIds(ec, pago.cuotasAPagar.split(",").map(_.toInt)).map { pagos =>
            var totalPagos = sumarPagos(pagos)
            if (!pago.pagoParcial) {
              pagos.map{ p =>
                pagosDao.resolver(p.id);
              }

            } else {
              var total = pago.totalAPagar.toDouble
              pagos.map { p=>
                if (total >= p.valorCuotaBase) {
                  pagosDao.resolver(p.id)
                  total= total - p.valorCuotaBase
                } else {
                  pagosDao.resolverParcial(p.id, total)
                }

              }
            }
            Redirect(routes.PagoController.showPagarCuota()).flashing("success" -> "Pagos Realizados Correctamente")
        }
      }
        )


  }

  private def sumarPagos(pagos: List[PagoApi]): Double = {
    pagos match {
      case pago :: tail =>   determinarValorASumar(pago) + sumarPagos(tail) // if there is an element, add it to the sum of the tail
      case Nil => 0 // if there are no elements, then the sum is 0
    }
  }

  private def determinarValorASumar (pago:PagoApi) = {
    if (pago.pagoParcial == 0) {
      pago.valorCuotaBase
    } else  pago.valorCuotaBase - pago.pagoParcial
  }
}
case class BuscarCuotasPorFamiliaForm(familiaId: Int, valorCuotaBase: String)
case class PagoForm (cuotasAPagar: String, totalAPagar: String, pagoParcial: Boolean)


