package dao

import javax.inject.Inject
import javax.inject.Singleton
import models._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.QueryBase

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class AlumnoDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends Tables with HasDatabaseConfigProvider[JdbcProfile] {


  val _profile = profile
  import _profile.api._

  def findAll(implicit ec: ExecutionContext): Future[List[FamiliaApi]] = {
    db.run(getAlumnos().result ) map {
      dataTuples =>
        val groupedByFamilia  = dataTuples.groupBy(_._3)
        val familias: List[FamiliaApi]  = groupedByFamilia.map {
          case (familiaRow, tuples ) =>
            val alumnos = tuples.map(_._1).map {a =>
              AlumnoApi(a.id, a.legajo, a.nombres, a.apellidos, a.nivel)}
            FamiliaApi(familiaRow.id, familiaRow.descripcion, familiaRow.observaciones, alumnos)
        }.toList
        familias
      }
  }

  def findById(implicit ec: ExecutionContext, id: Int) : Future[FamiliaApi] = {
    db.run(getAlumnos(Some(id)).result) map {
      dataTuples =>
        val groupedByFamilia  = dataTuples.groupBy(_._3)
        val familias: List[FamiliaApi]  = groupedByFamilia.map {
          case (familiaRow, tuples ) =>
            val alumnos = tuples.map(_._1).map {a =>

              AlumnoApi(a.id, a.legajo, a.nombres, a.apellidos, a.nivel)}
            FamiliaApi(familiaRow.id, familiaRow.descripcion, familiaRow.observaciones, alumnos)
        }.toList
        familias.head
    }
  }


  def findBy(implicit ec: ExecutionContext,familia: Option[String], nivel: Option[String], grado:Option[String], legajo:Option[String]): Future[List[FamiliaApi]] = {
    db.run(getAlumnos(familia, nivel, grado, legajo).result ) map {
      dataTuples =>
        val groupedByFamilia  = dataTuples.groupBy(_._3)
        val familias: List[FamiliaApi]  = groupedByFamilia.map {
          case (familiaRow, tuples ) =>
            val alumnos = tuples.map(_._1).map {a =>
              AlumnoApi(a.id, a.legajo, a.nombres, a.apellidos, a.nivel)
            }
            FamiliaApi(familiaRow.id, familiaRow.descripcion, familiaRow.observaciones, alumnos)
        }.toList
        familias
    }
  }


  private def getAlumnos(maybeId: Option[Int] = None) = {
    val alumnosQuery  = maybeId match {
      case None => alumnos.filter(_.activo === true)
      case Some(id) => alumnos.filter(_.id === id )
    }

    val withNivelGrado = for {
      (alumno, nivelGrado) <- alumnosQuery.join(nivelGrados).on(_.nivel === _.id)
    } yield (alumno, nivelGrado)

    val  withFamily = for {
      ((alumno, nivelGrado), familia) <- withNivelGrado.join(familias).on(_._1.familia === _.id)
    } yield (alumno, nivelGrado, familia)

    withFamily.sortBy(_._3.descripcion.asc)
  }

  private def getAlumnos(familia: Option[String], nivel: Option[String], grado:Option[String], legajo:Option[String] ) = {
   val alumnosQuery = legajo match {
     case Some(legajo) => if (legajo!="") alumnos.filter(_.legajo like s"%$legajo%") else alumnos.filter(_.activo === true)
     case None => alumnos.filter(_.activo === true)

   }

    val withFamily = familia match {
      case None => alumnosQuery
      case Some(f) => if (f!="") alumnosQuery.filter(_.familia === f.toInt) else alumnosQuery
    }


    val withNivel = nivel match {
      case Some(n) => {
        if (n!="") {
          val nivelGradosIds = nivelGrados.filter(_.nivel === n.toInt).map(_.id)
          withFamily.filter(_.nivel in nivelGradosIds)
        }else
          withFamily

      }
    }

    val withGrado = grado match {
      case Some(n) => {
        if (n!="") {
          val nivelGradosIds = nivelGrados.filter(_.grado === n.toInt).map(_.id)
          withNivel.filter(_.nivel in nivelGradosIds)
        }else
          withNivel

      }
    }


    val withNivelGrado = for {
      (alumno, nivelGrado) <- withGrado.join(nivelGrados).on(_.nivel === _.id)
    } yield (alumno, nivelGrado)

    val  withFamilias = for {
      ((alumno, nivelGrado), familia) <- withNivelGrado.join(familias).on(_._1.familia === _.id)
    } yield (alumno, nivelGrado, familia)

    withFamilias.sortBy(_._3.descripcion.asc)
  }


  def create( legajo: String, nombres:String, apellidos: String, familia:Int, nivelgrado: Int): Future[Alumno] = db.run {
    // We create a projection of just the anio, since we're not inserting a value for the id column

    (alumnos.map(f => (f.legajo, f.nombres, f.apellidos, f.nivel, f.familia))
      // Now define it to return the id, because we want to know what id was generated for the Alumno
      returning alumnos.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((tupla, id) => Alumno(id, tupla._1, tupla._2,  tupla._3,  tupla._4, tupla._5, true))
      // And finally, insert the Alumno into the database
      ) += (legajo, nombres, apellidos, nivelgrado, familia)
  }

  def update(id: Int, legajo: String, nombres:String, apellidos: String, familia:Int, nivelgrado: Int, activo: Boolean) = db.run{
    alumnos.insertOrUpdate(Alumno(id, legajo, nombres, apellidos, nivelgrado, familia, activo))
  }



}
