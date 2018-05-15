package dao

import javax.inject.{Inject, Singleton}
import models._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class NivelGradoDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends Tables with HasDatabaseConfigProvider[JdbcProfile] {


  val _profile = profile
  import _profile.api._

  def findAll(implicit ec: ExecutionContext): Future[List[NivelGradoApi]] = {
    db.run(getNivelesGrados().result ) map {
      dataTuples =>
       dataTuples.map {
          case(nivelGrado, nivel, grado) => NivelGradoApi(id = nivelGrado.id, nivel=NivelApi( nivel.id, nivel.nivel), grado=GradoApi(grado.id, grado.grado))
        }.toList

      }
  }


  private def getNivelesGrados(maybeId: Option[Int] = None) = {
    val nivelesGradoQuery  = maybeId match {
      case None => nivelGrados
      case Some(id) => nivelGrados.filter(_.id === id )
    }

    val withNivel = for {
      (nivelGrado, nivel) <- nivelesGradoQuery.join(niveles).on(_.nivel === _.id)
    } yield (nivelGrado, nivel)


    val withGrado = for {
      ((nivelGrado, nivel), grado) <- withNivel.join(grados).on(_._1.grado === _.id)
    } yield (nivelGrado, nivel, grado)

    withGrado
  }


}
