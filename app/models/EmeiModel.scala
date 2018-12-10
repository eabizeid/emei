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

case class Familia(id: Int, descripcion: String, observaciones: String, deuda: Double)

case class Alumno(id:Int, legajo: String, nombres: String, apellidos: String, nivel: Int, familia: Int, activo: Boolean, descuentoEspecial: String)

case class CuotaBase (id: Int, valor: Double, anio: Int, mes: Int)

case class Pago (id: Int, recibo: String, cuota: Int, totalSinDescuento: Double, tipoPago: Int, familia: Int, descuentoAplicado: Double, interes: Double, pagoParcial: Double, resuelto: Boolean )

case class Inscripcion(id: Int, anio: Int, valor: Double )

case class PagoInscripcion(id:Int, inscripcion: Int,  totalSinDescuento: Double, tipoPago: Int, familia: Int, descuentoAplicado: Double, interes: Double, pagoParcial: Double, resuelto: Boolean, recibo:String)


