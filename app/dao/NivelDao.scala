package dao

import javax.inject.{Inject, Singleton}
import models._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class NivelDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends Tables with HasDatabaseConfigProvider[JdbcProfile] {


  val _profile = profile
  import _profile.api._

  def findAll(implicit ec: ExecutionContext): Future[List[NivelApi]] = {
    db.run(getNiveles().result ) map {
      dataTuples =>
        val niveles = dataTuples.map {n => NivelApi(n.id, n.nivel)}

        niveles.toList
      }
  }


  private def getNiveles(maybeId: Option[Int] = None) = {
    val nivelesQuery  = maybeId match {
      case None => niveles
      case Some(id) => niveles.filter(_.id === id )
    }



    nivelesQuery
  }


}
