package models

import play.api.libs.json._


case class NivelApi(id: Int, nivel: String)

case class GradoApi(id: Int, grado: String)

case class TipoPagoApi (id: Int, descripcion: String)

case class AnioApi (id: Int, anio: Int)

case class MesApi (id: Int, mes: Int)

case class ContactoApi(id: Int, descripcion: String, familiaId: Int)

case class PhoneApi(id: Int, number: String, contactoId: Int)

case class NivelGradoApi(id: Int, nivel: NivelApi, grado: GradoApi )

case class AlumnoApi(id:Int, legajo: String, nombres: String, apellidos: String, nivel: Int)

case class FamiliaApi(id: Int, descripcion: String, observaciones: String, alumnos: Seq[AlumnoApi])

case class CuotaBaseApi (id: Int, valor: Double, anio: AnioApi, mes: MesApi)

case class CuotaApi (id: Int, cuotaBase: CuotaBaseApi, anio: AnioApi, mes: MesApi)

case class PagoApi (id: Int, recibo: String, cuota: CuotaApi, tipoPago: TipoPagoApi, familia: FamiliaApi, descuentoAplicado: Double)

object NivelApi {
  implicit val alumnoFormat = Json.format[NivelApi]
}

object GradoApi {
  implicit val alumnoFormat = Json.format[GradoApi]
}

object TipoPagoApi {
  implicit val alumnoFormat = Json.format[TipoPagoApi]
}

object AnioApi {
  implicit val alumnoFormat = Json.format[AnioApi]
}

object MesApi {
  implicit val alumnoFormat = Json.format[MesApi]
}

object ContactoApi {
  implicit val alumnoFormat = Json.format[ContactoApi]
}

object NivelGradoApi {
  implicit val alumnoFormat = Json.format[NivelGradoApi]
}

object AlumnoApi {
  implicit val alumnoFormat = Json.format[AlumnoApi]
}

object FamiliaApi{
  implicit val alumnoFormat = Json.format[FamiliaApi]
}

object CuotaBaseApi {
  implicit val alumnoFormat = Json.format[CuotaBaseApi]
}

object CuotaApi {
  implicit val alumnoFormat = Json.format[CuotaApi]
}

object PagoApi {
  implicit val alumnoFormat = Json.format[PagoApi]
}

