package dao

import javax.inject.{Inject, Singleton}
import models._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class GradoDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends Tables with HasDatabaseConfigProvider[JdbcProfile] {


  val _profile = profile
  import _profile.api._

  def findAll(implicit ec: ExecutionContext): Future[List[GradoApi]] = {
    db.run(getGrados().result ) map {
      dataTuples =>
        val grados = dataTuples.map {n => GradoApi(n.id, n.grado)}
       
        grados.toList
      }
  }


  private def getGrados(maybeId: Option[Int] = None) = {
    val nivelesQuery  = maybeId match {
      case None => grados
      case Some(id) => grados.filter(_.id === id )
    }


    nivelesQuery
  }


}
