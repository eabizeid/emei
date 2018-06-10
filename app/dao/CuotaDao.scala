package dao

import javax.inject.{Inject, Singleton}
import models.{Familia, NivelApi, Pago, PagoApi}
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
        val pagos = dataTuples.map {pct => PagoApi(pct._1.id, pct._1.recibo, pct._2.valor, pct._2.mes, pct._2.anio, pct._3.descripcion,pct._1.familia.toString, pct._1.descuentoAplicado, pct._1.interes, pct._1.pagoParcial, pct._1.resuelto)}

        pagos.toList
    }
  }


  private def getPagos(familiaId: Int) = {
    val filtroPagosPorfamilia  = pagos.filter(_.familia === familiaId )
    val filtroPagosPorFamiliaYConPagosParciales = filtroPagosPorfamilia.filterNot(_.resuelto === false)

    val filtroPagosPorFamiliaYConPagosParcialesConCuotas = for {
      (pago, cuota) <- filtroPagosPorFamiliaYConPagosParciales.join(cuotasbase).on(_.cuota === _.id)

    } yield (pago, cuota)


    val filtroPagosPorFamiliayCOnPagosParcialesConCuotasYTipoPago = for {
      ((pago, cuota), tipoPago) <- filtroPagosPorFamiliaYConPagosParcialesConCuotas.join(tipoPagos).on(_._1.tipoPago === _.id)
    }yield (pago, cuota, tipoPago)

    filtroPagosPorFamiliayCOnPagosParcialesConCuotasYTipoPago
  }


  def create(cuota: Int, familia:Int): Future[Pago] = db.run {
    // We create a projection of just the anio, since we're not inserting a value for the id column

    (pagos.map(p => (p.recibo, p.cuota, p.tipoPago, p.familia, p.descuentoAplicado, p.interes, p.pagoParcial, p.resuelto))
      // Now define it to return the id, because we want to know what id was generated for the Alumno
      returning pagos.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((tupla, id) => Pago(id,tupla._1,tupla._2,tupla._3,tupla._4,tupla._5,tupla._6,tupla._7, tupla._8))
      // And finally, insert the Familia into the database
      ) += ("", cuota, null, familia, null, null, null, false)



  }
}
