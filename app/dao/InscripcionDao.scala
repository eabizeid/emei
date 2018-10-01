package dao

import javax.inject.{Inject, Singleton}
import models.Inscripcion
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

@Singleton
class InscripcionDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends Tables with HasDatabaseConfigProvider[JdbcProfile] {
  val _profile = profile
  import _profile.api._


  def getInscripciones(ids:Seq[Int]) = {
    inscripcion.filter(_.id.inSet(ids))
  }
  def create(valor: Double, anio: Int): Future[Inscripcion] = db.run {
    // We create a projection of just the anio, since we're not inserting a value for the id column

    (inscripcion.map(p => (p.valor, p.anio))
      // Now define it to return the id, because we want to know what id was generated for the Alumno
      returning inscripcion.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((tupla, id) => Inscripcion(id,tupla._2, tupla._1))
      // And finally, insert the Familia into the database
      ) += (valor, anio)



  }
}
