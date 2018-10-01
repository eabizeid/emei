package dao

import javax.inject.{Inject, Singleton}
import models._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PagoDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends Tables with HasDatabaseConfigProvider[JdbcProfile] {

  val _profile = profile
  import _profile.api._

  def findPagosPendientes(implicit ec: ExecutionContext, familiaId: Int) ={
    db.run(getPagos(familiaId).result ) map {
      dataTuples =>
        val pagos = dataTuples.map {pct => PagoApi(pct._1.id, pct._1.recibo, pct._2.valor, pct._1.totalSinDescuento, pct._2.mes, pct._2.anio, pct._3.getOrElse("").toString,pct._1.familia.toString, pct._1.descuentoAplicado, pct._1.interes, pct._1.pagoParcial, pct._1.resuelto)}

        pagos.toList
    }
  }




  private def getPagos(familiaId: Int) = {
    val filtroPagosPorfamilia  = pagos.filter(_.familia === familiaId )
    val filtroPagosPorFamiliaYConPagosParciales = filtroPagosPorfamilia.filter(_.resuelto === false)

    val filtroPagosPorFamiliaYConPagosParcialesConCuotas = for {
      (pago, cuota) <- filtroPagosPorFamiliaYConPagosParciales.join(cuotasbase).on(_.cuota === _.id)

    } yield (pago, cuota)


    val filtroPagosPorFamiliayCOnPagosParcialesConCuotasYTipoPago = for {
      ((pago, cuota), tipoPago) <- filtroPagosPorFamiliaYConPagosParcialesConCuotas.joinLeft(tipoPagos).on(_._1.tipoPago === _.id)
    }yield (pago, cuota, tipoPago)

    filtroPagosPorFamiliayCOnPagosParcialesConCuotasYTipoPago
  }



  def findInscripcionesPendientes(implicit ec: ExecutionContext, familiaId: Int) = {
    db.run(getInscripciones(familiaId).result ) map {
      dataTuples =>
        val inscripciones = dataTuples.map {pct => PagoInscripcionApi(pct._1.id, pct._2.valor, pct._1.totalSinDescuento, pct._2.anio, pct._3.getOrElse("").toString,pct._1.familia.toString, pct._1.descuentoAplicado, pct._1.interes, pct._1.pagoParcial, pct._1.resuelto)}

        inscripciones.toList

    }
  }


  def getPagosPendientesByIds(implicit ec: ExecutionContext, ids: Seq[Int]) = {
    val byIds = pagos.filter(_.id.inSet(ids))

    val byIdsYAgregoCuotasBase = for {
      (pago, cuota) <- byIds.join(cuotasbase).on(_.cuota === _.id)
    }yield (pago, cuota)

    val byIdsYAgregoCuotasBaseYTipoPago = for {
      ((pago, cuota), tipoPago) <- byIdsYAgregoCuotasBase.joinLeft(tipoPagos).on(_._1.tipoPago === _.id)
    }yield (pago, cuota, tipoPago)


    db.run(byIdsYAgregoCuotasBaseYTipoPago.result) map { data =>
      data.map{ pct =>
        PagoApi(pct._1.id, pct._1.recibo, pct._2.valor, pct._1.totalSinDescuento,pct._2.mes, pct._2.anio, pct._3.getOrElse("").toString,pct._1.familia.toString, pct._1.descuentoAplicado, pct._1.interes, pct._1.pagoParcial, pct._1.resuelto)

      }.toList
    }
  }



  def getInscripcionesPendientesByIds(implicit ec: ExecutionContext, ids: Seq[Int]) = {
    val byIds = pagoInscripcion.filter(_.id.inSet(ids))

    val byIdsYAgregoInscripcion= for {
      (pago, cuota) <- byIds.join(inscripcion).on(_.inscripcion === _.id)
    }yield (pago, cuota)

    val byIdsYAgregoInscripcionYTipoPago = for {
      ((pago, cuota), tipoPago) <- byIdsYAgregoInscripcion.joinLeft(tipoPagos).on(_._1.tipoPago === _.id)
    }yield (pago, cuota, tipoPago)


    db.run(byIdsYAgregoInscripcionYTipoPago.result) map { data =>
      data.map{ pct =>
        PagoInscripcionApi(pct._1.id, pct._2.valor,  pct._1.totalSinDescuento, pct._2.anio, pct._3.getOrElse("").toString,pct._1.familia.toString, pct._1.descuentoAplicado, pct._1.interes, pct._1.pagoParcial, pct._1.resuelto)

      }.toList
    }
  }



  private def getInscripciones(familiaId: Int) = {
    val filtroPagosPorfamilia  = pagoInscripcion.filter(_.familia === familiaId )
    val filtroPagosPorFamiliaYConPagosParciales = filtroPagosPorfamilia.filter(_.resuelto === false)

    val filtroPagosPorFamiliaYConPagosParcialesConCuotas = for {
      (pago, cuota) <- filtroPagosPorFamiliaYConPagosParciales.join(inscripcion).on(_.inscripcion === _.id)

    } yield (pago, cuota)


    val filtroPagosPorFamiliayCOnPagosParcialesConCuotasYTipoPago = for {
      ((pago, cuota), tipoPago) <- filtroPagosPorFamiliaYConPagosParcialesConCuotas.joinLeft(tipoPagos).on(_._1.tipoPago === _.id)
    }yield (pago, cuota, tipoPago)

    filtroPagosPorFamiliayCOnPagosParcialesConCuotasYTipoPago
  }

  def resolver(id:Int, totalAPagar: Double, descuentoAplicado: Double, interes: Double) = db.run{
    val q = for { c <- pagos if c.id === id } yield (c.resuelto, c.pagoParcial, c.descuentoAplicado, c.interes)
    q.update(true, totalAPagar, descuentoAplicado, interes)
  }

  def resolverParcial(id:Int, pagoParcial: Double) = db.run{
    val q = for { c <- pagos if c.id === id } yield c.pagoParcial
    q.update(pagoParcial)
  }

  def resolverInscripcion(id:Int, totalAPagar: Double, descuentoAplicado: Double, interes: Double) = db.run{
    val q = for { c <- pagoInscripcion if c.id === id } yield (c.resuelto, c.pagoParcial, c.descuentoAplicado, c.interes)
    q.update(true, totalAPagar, descuentoAplicado, interes)
  }

  def resolverInscripcionParcial(id:Int, pagoParcial: Double) = db.run{
    val q = for { c <- pagoInscripcion if c.id === id } yield c.pagoParcial
    q.update(pagoParcial)
  }

  def create(cuota: Int, familia:Int, total:Double): Future[Pago] = db.run {
    // We create a projection of just the anio, since we're not inserting a value for the id column

    (pagos.map(p => (p.recibo, p.cuota, p.totalSinDescuento, p.tipoPago, p.familia, p.descuentoAplicado, p.interes, p.pagoParcial, p.resuelto))
      // Now define it to return the id, because we want to know what id was generated for the Alumno
      returning pagos.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((tupla, id) => Pago(id,tupla._1,tupla._2,tupla._3,tupla._4,tupla._5,tupla._6,tupla._7, tupla._8, tupla._9))
      // And finally, insert the Familia into the database
      ) += ("", cuota, total,  0, familia, 0, 0, 0,  false)



  }

  def createPagoInscripcion(inscripcion: Int, familia:Int, total: Double): Future[PagoInscripcion] = db.run {
    // We create a projection of just the anio, since we're not inserting a value for the id column

    (pagoInscripcion.map(p => ( p.inscripcion, p.totalSinDescuento, p.tipoPago, p.familia, p.descuentoAplicado, p.interes, p.pagoParcial, p.resuelto))
      // Now define it to return the id, because we want to know what id was generated for the Alumno
      returning pagoInscripcion.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((tupla, id) => PagoInscripcion(id,tupla._1, tupla._2,tupla._3,tupla._4,tupla._5,tupla._6,tupla._7, tupla._8))
      // And finally, insert the Familia into the database
      ) += (inscripcion, total, 0, familia, 0, 0, 0, false)



  }
}
