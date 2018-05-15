package models

import play.api.libs.json._


case class TipoContacto(id: Int, descripcion: String)

case class Nivel(id: Int, nivel: String)

case class Grado(id: Int, grado: String)

case class TipoPago (id: Int, descripcion: String)

case class Anio (id: Int, anio: Int)

case class Mes (id: Int, mes: Int)

case class Contacto(id: Int, descripcion: String, email: String, phone: String, familiaId: Int)

case class Phone(id: Int, number: String, contactoId: Int)

case class NivelGrado(id: Int, nivel: Int, grado: Int )

case class Familia(id: Int, descripcion: String, observaciones: String)

case class Alumno(id:Int, legajo: String, nombres: String, apellidos: String, nivel: Int, familia: Int, activo: Boolean)

case class CuotaBase (id: Int, valor: Double, anio: Int, mes: Int)

case class Cuota (id: Int, cuotaBase: Int, anio: Int, mes: Int)

case class Pago (id: Int, recibo: String, cuota: Int, tipoPago: Int, familia: Int, descuentoAplicado: Double)
