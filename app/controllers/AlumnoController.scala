package controllers

import dao._
import javax.inject._
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.i18n._
import play.api.libs.json.Json
import play.api.mvc._
import play.api.routing.JavaScriptReverseRouter

import scala.concurrent.{ExecutionContext, Future}

class AlumnoController @Inject()(alumnoDao: AlumnoDao,nivelDao: NivelDao, gradoDao: GradoDao, nivelGradosDao: NivelGradoDao,
                                 familiaDao: FamiliaDao, contactoDao: ContactoDao, cc: MessagesControllerComponents
                                )(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  /**
    * The mapping for the alumno form.
    */
  val alumnoForm: Form[CreateAlumnoForm] = Form {
    mapping(
      "legajo" -> nonEmptyText,
      "nombres" -> nonEmptyText,
      "apellidos" -> nonEmptyText,
      "nivel" -> number,
      "familia" -> number,
      "id" -> number
    //  "age" -> number.verifying(min(0), max(140))
    )(CreateAlumnoForm.apply)(CreateAlumnoForm.unapply)
  }

  val familiaForm: Form[CreateFamiliaForm] = Form {
    mapping(
      "descripcion" -> text,
      "observaciones" -> text,
      "contactos" -> seq(
        mapping(
          "label" -> nonEmptyText,
          "email" -> optional(email),
          "phones" -> nonEmptyText

        )(ContactoForm.apply)(ContactoForm.unapply)
      )

    )(CreateFamiliaForm.apply)(CreateFamiliaForm.unapply)
  }

  /**
    * The createAlumno action.
    */
  def createAlumno = Action { implicit request =>
    Ok(views.html.createAlumno(alumnoForm))
  }

  /**
    * The add alumno action.
    *
    * This is asynchronous, since we're invoking the asynchronous methods on AlumnoRepository.
    */
  def addAlumno = Action.async { implicit request =>

    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle succes.
    alumnoForm.bindFromRequest.fold(
      // The error function. We return the createAlumno page with the error form, which will render the errors.
      // We also wrap the result in a successful future, since this action is synchronous, but we're required to return
      // a future because the alumno creation function returns a future.
      errorForm => {

        val alumnosNivelesGrados = for {
          alumnos <- alumnoDao.findAll(ec)
          niveles <- nivelDao.findAll(ec)
          grados <- gradoDao.findAll(ec)
          nivelesGrados<- nivelGradosDao.findAll(ec)
          familias <- familiaDao.findAllWithoutAlumnos(ec)
        } yield (alumnos, niveles, grados, nivelesGrados, familias)

        alumnosNivelesGrados.map {
          case (alumnos, niveles, grados, nivelesGrados, familias) =>  Ok(views.html.alumnos(errorForm, familiaForm, alumnos, niveles, grados, nivelesGrados, familias))
        }

      },
      // There were no errors in the from, so create the alumno.
      alumno => {
        if(alumno.id == null) {
          alumnoDao.create(alumno.legajo, alumno.nombres, alumno.apellidos, alumno.familia, alumno.nivel).map { _ =>
            // If successful, we simply redirect to the createAlumno page.
            Redirect(routes.AlumnoController.getAlumnos()).flashing("success" -> "Alumno Creado")
          }
        }else {
            alumnoDao.update(alumno.id, alumno.legajo, alumno.nombres, alumno.apellidos, alumno.familia, alumno.nivel, true).map { _ =>
              Redirect(routes.AlumnoController.getAlumnos()).flashing("success" -> "Alumno Creado")
            }
        }
      }

    )
  }


  def addFamilia= Action.async { implicit request =>

    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle succes.
    familiaForm.bindFromRequest.fold(
      // The error function. We return the createAlumno page with the error form, which will render the errors.
      // We also wrap the result in a successful future, since this action is synchronous, but we're required to return
      // a future because the alumno creation function returns a future.
      errorForm => {

        val alumnosNivelesGrados = for {
          alumnos <- alumnoDao.findAll(ec)
          niveles <- nivelDao.findAll(ec)
          grados <- gradoDao.findAll(ec)
          nivelesGrados<- nivelGradosDao.findAll(ec)
          familias <- familiaDao.findAllWithoutAlumnos(ec)
        } yield (alumnos, niveles, grados, nivelesGrados, familias)

        alumnosNivelesGrados.map {
          case (alumnos, niveles, grados, nivelesGrados, familias) =>  Ok(views.html.alumnos(alumnoForm, errorForm, alumnos, niveles, grados, nivelesGrados, familias))
        }

      },
      // There were no errors in the from, so create the alumno.
      familia => {
        familiaDao.create(familia.descripcion, familia.observaciones).map { f =>
          // If successful, we simply redirect to the createAlumno page.
          for (contacto <-familia.contactos) {

            contactoDao.create(contacto.label, contacto.email.getOrElse(""),contacto.phones, f.id).map { _=>
              Redirect(routes.AlumnoController.getAlumnos()).flashing("success" -> "Alumno Creado")
            }
          }
          Redirect(routes.AlumnoController.getAlumnos()).flashing("success" -> "Alumno Creado")
        }
      }
    )
  }

  /**
    * A REST endpoint that gets all the people as JSON.
    */
  def getAlumnos = Action.async { implicit request =>


    val alumnosNivelesGrados = for {
      alumnos <- alumnoDao.findAll(ec)
      niveles <- nivelDao.findAll(ec)
      grados <- gradoDao.findAll(ec)
      nivelesGrados<- nivelGradosDao.findAll(ec)
      familias <- familiaDao.findAllWithoutAlumnos(ec)
    } yield (alumnos, niveles, grados, nivelesGrados, familias)

    alumnosNivelesGrados.map {
      case (alumnos, niveles, grados, nivelesGrados, familias) => Ok(views.html.alumnos(alumnoForm, familiaForm, alumnos, niveles, grados, nivelesGrados, familias))
    }


  }

  def buscar = Action.async { implicit  request =>

    val legajo =  request.body.asFormUrlEncoded.get("legajo").head

    val familia = request.body.asFormUrlEncoded.get("familia").head
    val nivel = request.body.asFormUrlEncoded.get("nivel").head
    val grado = request.body.asFormUrlEncoded.get("grado").head

    alumnoDao.findBy(ec,Some(familia), Some(nivel), Some(grado), Some(legajo)) map { alumnos =>
      Ok(views.html.resultadosBuscar(alumnos))
    }

  }

  def modificar(id:Int) = Action.async { implicit request =>

    val alumnosNivelesGrados = for {
      alumno <-  alumnoDao.findById(ec, id)
      alumnos <- alumnoDao.findAll(ec)
      niveles <- nivelDao.findAll(ec)
      grados <- gradoDao.findAll(ec)
      nivelesGrados<- nivelGradosDao.findAll(ec)
      familias <- familiaDao.findAllWithoutAlumnos(ec)
    } yield (alumno, alumnos, niveles, grados, nivelesGrados, familias)

    alumnosNivelesGrados.map {
      case (alumno, alumnos, niveles, grados, nivelesGrados, familias) =>  {
        val a = alumno.alumnos.head
        val data: Map[String, String] = Map("legajo" -> a.legajo, "nombres" -> a.nombres, "apellidos" -> a.apellidos, "nivel" -> a.nivel.toString, "familia" -> alumno.id.toString, "id"->a.id.toString)
        val aaa: Form[CreateAlumnoForm] = alumnoForm.bind(data)
        Ok(views.html.alumnos(aaa, familiaForm, alumnos, niveles, grados, nivelesGrados, familias))
      }
    }
  }

  def borrar(id:Int) = Action.async { implicit request =>
    alumnoDao.findById(ec, id).flatMap { alumno =>
      val a = alumno.alumnos.head
      alumnoDao.update(a.id, a.legajo, a.nombres, a.apellidos, alumno.id, a.nivel, false).map { r =>
        Redirect(routes.AlumnoController.getAlumnos()).flashing("success" -> "Alumno dado de baja")
      }
    }

  }
}

/**
  * The create alumno form.
  *
  * Generally for forms, you should define separate objects to your models, since forms very often need to present data
  * in a different way to your models.  In this case, it doesn't make sense to have an id parameter in the form, since
  * that is generated once it's created.
  */
case class CreateAlumnoForm( legajo: String, nombres: String, apellidos: String, nivel: Int, familia: Int, id: Int)

case class CreateFamiliaForm( descripcion: String, observaciones: String,  contactos:Seq[ContactoForm])
case class ContactoForm(label: String, email: Option[String], phones: String)

