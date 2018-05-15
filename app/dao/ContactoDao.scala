package dao

import javax.inject.{Inject, Singleton}
import models._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class ContactoDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends Tables with HasDatabaseConfigProvider[JdbcProfile] {


  val _profile = profile
  import _profile.api._

  def findAll(implicit ec: ExecutionContext): Future[List[ContactoApi]] = {
    db.run(getContactos().result ) map {
      dataTuples =>
        val contactos = dataTuples.map {n => ContactoApi(n.id, n.descripcion, n.familiaId)}
       
        contactos.toList
      }
  }


  private def getContactos(maybeId: Option[Int] = None) = {
    val nivelesQuery  = maybeId match {
      case None => contactos
      case Some(id) => contactos.filter(_.id === id )
    }


    nivelesQuery
  }

  def create( descripcion: String, mail: String, phone: String, familiaId:Int ): Future[Contacto] = db.run {
    // We create a projection of just the anio, since we're not inserting a value for the id column
    (contactos.map(f => (f.descripcion, f.email, f.phone, f.familiaId))
      // Now define it to return the id, because we want to know what id was generated for the Alumno
      returning contactos.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((tupla, id) => Contacto(id, tupla._1, tupla._2, tupla._3, tupla._4))
      // And finally, insert the Familia into the database
      ) += (descripcion, mail, phone, familiaId)

  }



}
