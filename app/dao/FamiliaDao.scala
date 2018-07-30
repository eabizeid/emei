package dao

import controllers.ContactoForm
import javax.inject.{Inject, Singleton}
import models._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class FamiliaDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends Tables with HasDatabaseConfigProvider[JdbcProfile] {


  val _profile = profile
  import _profile.api._

  def findAllWithoutAlumnos(implicit ec: ExecutionContext): Future[List[FamiliaApi]] = {
    db.run(getFamilias().result ) map {
      dataTuples =>
        val familias = dataTuples.map { f =>
          FamiliaApi(f.id, f.descripcion, f.observaciones, List(), f.deuda)
        }
        familias.toList
    }
  }

  def getFamiliaById(implicit ec: ExecutionContext, id:  Int) = {
    db.run(getFamilias(Some(id)).result) map   {
      dataTuples =>
        val familias = dataTuples.map { f =>
          FamiliaApi(f.id, f.descripcion, f.observaciones, List(), f.deuda)
        }
        familias.toList
    }
  }

  private def getFamilias(maybeId: Option[Int]= None) = {

    maybeId match {
      case Some(id) => familias.filter(_.id ===id)
      case None     => familias
    }

  }

  def create( descripcion: String, observaciones:String, deuda: Double ): Future[Familia] = db.run {
    // We create a projection of just the anio, since we're not inserting a value for the id column

    (familias.map(f => (f.descripcion, f.observaciones, f.deuda))
      // Now define it to return the id, because we want to know what id was generated for the Alumno
      returning familias.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((tupla, id) => Familia(id, tupla._1, tupla._2, tupla._3))
      // And finally, insert the Familia into the database
      ) += (descripcion, observaciones, deuda)



  }

}
