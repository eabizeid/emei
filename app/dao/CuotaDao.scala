package dao

import javax.inject.{Inject, Singleton}
import models.{CuotaApi, CuotaBase, Pago, PagoApi}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CuotaDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends Tables with HasDatabaseConfigProvider[JdbcProfile] {
  val _profile = profile
  import _profile.api._


  def getCuotas(ids:Seq[Int]) = {
    cuotasbase.filter(_.id.inSet(ids))
  }
  def create(valorBase: Double, anio: Int, mes:Int): Future[CuotaBase] = db.run {
    // We create a projection of just the anio, since we're not inserting a value for the id column

    (cuotasbase.map(p => (p.valor, p.anio, p.mes))
      // Now define it to return the id, because we want to know what id was generated for the Alumno
      returning cuotasbase.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((tupla, id) => CuotaBase(id,tupla._1,tupla._2,tupla._3))
      // And finally, insert the Familia into the database
      ) += (valorBase, anio, mes)



  }
}
