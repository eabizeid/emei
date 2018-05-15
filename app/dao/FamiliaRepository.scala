package dao

import javax.inject.{Inject, Singleton}
import models.Familia
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

/**
  * A repository for alumnos.
  *
  * @param dbConfigProvider The Play db config provider. Play will inject this for you.
  */
@Singleton
class FamiliaRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  // We want the JdbcProfile for this provider
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  // These imports are important, the first one brings db into scope, which will let you do the actual db operations.
  // The second one brings the Slick DSL into scope, which lets you define the table and other queries.
  import dbConfig._
  import profile.api._

  /**
    * Here we define the table. It will have a name of alumnos
    */


  /**
    * Create a Anio with the given name and age.
    *
    * This is an asynchronous operation, it will return a future of the created Alumno, which can be used to obtain the

  def create( descripcion: String, observaciones:String): Future[Familia] = db.run {
    // We create a projection of just the anio, since we're not inserting a value for the id column

    (familias.map(f => (f.descripcion, f.observaciones))
      // Now define it to return the id, because we want to know what id was generated for the Alumno
      returning familias.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((tupla, id) => Familia(id, tupla._1, tupla._2))
      // And finally, insert the Anio into the database
      ) += (descripcion, observaciones)
  }

  /**
    * List all the alumnos in the database.
    */
  def list(): Future[Seq[Familia]] = db.run {
    familias.result
  }**/
}

