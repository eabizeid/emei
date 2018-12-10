package controllers

import dao._
import javax.inject.Inject
import play.api.mvc.{MessagesAbstractController, MessagesControllerComponents}

import scala.concurrent.ExecutionContext

class ReportesController @Inject()(familiaDao: FamiliaDao, pagosDao: PagoDao, cuotaDao: CuotaDao, alumnosDao: AlumnoDao, inscripcionDao: InscripcionDao, cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {



  }
