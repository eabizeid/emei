package dao

import models._



trait Tables {

  // We want the JdbcProfile for this provider
  protected val driver: slick.jdbc.JdbcProfile

  import driver.api._
  import slick.jdbc.{ GetResult => GR }

  /**FAMILIA **/

  class FamiliaTable(tag: Tag) extends Table[Familia](tag, "familia") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    /** The age column */
    def descripcion = column[String]("descripcion")


    /** The age column */
    def observaciones = column[String]("observaciones")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Alumno object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Alumno case classes
      * apply and unapply methods.
      */
    def * = (id, descripcion, observaciones) <> ((Familia.apply _).tupled, Familia.unapply)
  }

  /**
    * The starting point for all queries on the anios table.
    */

  lazy val familias = new TableQuery(tag => new FamiliaTable(tag))



  //--------------------------
  // ALUMNOS //


  class AlumnosTable(tag: Tag) extends Table[Alumno](tag, "alumnos") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    /** The age column */
    def legajo = column[String]("legajo")


    /** The name column */
    def nombres = column[String]("nombres")


    /** The name column */
    def apellidos = column[String]("apellidos")

    /** The age column */
    def nivel = column[Int]("nivel_grado")

    /** The age column */
    def familia = column[Int]("familia")

    def activo = column[Boolean] ("activo")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Alumno object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Alumno case classes
      * apply and unapply methods.
      */
    def * = (id, legajo, nombres, apellidos, nivel, familia, activo) <> ((Alumno.apply _).tupled, Alumno.unapply)

    /*   def nivelGradoFk = foreignKey("nivel_grado_ibfk_1",
         nivel, TableQuery[NivelGrado])(n => n.id, onDelete = ForeignKeyAction.Cascade)

       def familiaFk = foreignKey("toFamily_ibfk_1",
         familia, TableQuery[Familia])(n => n.id, onDelete = ForeignKeyAction.Cascade)*/
  }


  /**
    * The starting point for all queries on the alumnos table.
    */
  lazy val alumnos = new TableQuery(tag => new AlumnosTable(tag))


  //--------------------------------

  //ANIOS //

  class AniosTable(tag: Tag) extends Table[Anio](tag, "anios") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    /** The age column */
    def anio = column[Int]("anio")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Alumno object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Alumno case classes
      * apply and unapply methods.
      */
    def * = (id, anio) <> ((Anio.apply _).tupled, Anio.unapply)
  }

  /**
    * The starting point for all queries on the anios table.
    */
  private val anios = TableQuery[AniosTable]

  //-------------------------

  //MESES //

  class MesesTable(tag: Tag) extends Table[Mes](tag, "mes") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    /** The age column */
    def mes = column[Int]("mes")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Alumno object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Alumno case classes
      * apply and unapply methods.
      */
    def * = (id, mes) <> ((Mes.apply _).tupled, Mes.unapply)
  }

  /**
    * The starting point for all queries on the anios table.
    */
  private val meses = TableQuery[MesesTable]
  

  //----------------------------------------------------------

  //NIVEL

  class NivelTable(tag: Tag) extends Table[Nivel](tag, "nivel") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    /** The age column */
    def nivel = column[String]("nivel")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Alumno object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Alumno case classes
      * apply and unapply methods.
      */
    def * = (id, nivel) <> ((Nivel.apply _).tupled, Nivel.unapply)
  }

  /**
    * The starting point for all queries on the anios table.
    */
  val niveles = TableQuery[NivelTable]

  //-----------------

  //GRADO
  class GradoTable(tag: Tag) extends Table[Grado](tag, "grado") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    /** The age column */
    def grado = column[String]("grado")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Alumno object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Alumno case classes
      * apply and unapply methods.
      */
    def * = (id, grado) <> ((Grado.apply _).tupled, Grado.unapply)
  }

  /**
    * The starting point for all queries on the anios table.
    */
  val grados = TableQuery[GradoTable]

  //---------------------------

  //NIVEK GRADO


  class NivelGradoTable(tag: Tag) extends Table[NivelGrado](tag, "nivel_grado") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def nivel = column[Int]("nivel")
    def grado = column[Int]("grado")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Alumno object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Alumno case classes
      * apply and unapply methods.
      */
    def * = (id, nivel, grado) <> ((NivelGrado.apply _).tupled, NivelGrado.unapply)

    /* def nivelFk = foreignKey("nivel_grado_ibfk_1",
       nivel, TableQuery[Nivel])(n => n.id, onDelete = ForeignKeyAction.Cascade)

     def gradoFk = foreignKey("nivel_grado_ibfk_2",
       grado, TableQuery[Grado])(n => n.id, onDelete = ForeignKeyAction.Cascade)*/
  }

  /**
    * The starting point for all queries on the anios table.
    */
  val nivelGrados =  new TableQuery(tag => new NivelGradoTable(tag))

  //-------------------------------------


  //CONTACTO

  class ContactoTable(tag: Tag) extends Table[Contacto](tag, "contacto") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    /** The age column */
    def descripcion = column[String]("descripcion")

    def email = column[String] ("email")

    def phone = column[String] ("phone")

    def familiaId = column[Int]("familia_id")
    

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Alumno object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Alumno case classes
      * apply and unapply methods.
      */
    def * = (id, descripcion, email, phone, familiaId) <> ((Contacto.apply _).tupled, Contacto.unapply)

    /* def tipoContactoFK = foreignKey("contacto_ibfk_1",
       tipoContacto, TableQuery[TipoContacto])(tc => tc.id, onDelete = ForeignKeyAction.Cascade)*/
  }

  /**
    * The starting point for all queries on the anios table.
    */
  val contactos = TableQuery[ContactoTable]

  //--------------------------------------------------------------------------


  //TIPO PAGO

  class TipoPagoTable(tag: Tag) extends Table[TipoPago](tag, "tipo_pago") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    /** The age column */
    def descripcion = column[String]("descripcion")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Alumno object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Alumno case classes
      * apply and unapply methods.
      */
    def * = (id, descripcion) <> ((TipoPago.apply _).tupled, TipoPago.unapply)
  }

  /**
    * The starting point for all queries on the anios table.
    */
  private val tipoPagos = TableQuery[TipoPagoTable]

  //------------------------------------------------------------------------------
  //PAGO

  class PagosTable(tag: Tag) extends Table[Pago](tag, "pagos") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    /** The age column */
    def recibo = column[String]("recibo")


    /** The name column */
    def cuota = column[Int]("cuota")


    /** The name column */
    def tipoPago = column[Int]("tipo_pago")

    /** The age column */
    def familia = column[Int]("familia")

    def descuentoAplicado = column[Double]("descuento_aplicado")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Pago object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Pago case classes
      * apply and unapply methods.
      */
    def * = (id, recibo, cuota, tipoPago, familia, descuentoAplicado) <> ((Pago.apply _).tupled, Pago.unapply)

    /*def tipoPagoFk = foreignKey("to_tipo_pago_ibfk_1",
      tipoPago, TableQuery[TipoPago])(n => n.id, onDelete = ForeignKeyAction.Cascade)

    def familiaFk = foreignKey("toFamily_ibfk_1",
      familia, TableQuery[Familia])(n => n.id, onDelete = ForeignKeyAction.Cascade)

    def cuotaFk = foreignKey("to_cuota_ibfk_1",
      cuota, TableQuery[Cuota])(n => n.id, onDelete = ForeignKeyAction.Cascade) */
  }


  /**
    * The starting point for all queries on the pagos table.
    */
  private val pagos = TableQuery[PagosTable]


  //----------------------------------------------------
  //CUOTA BASE

  class CuotaBaseTable(tag: Tag) extends Table[CuotaBase](tag, "cuota_base") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)


    def valor = column[Double]("valor")

    def mes = column[Int]("mes")

    def anio = column[Int]("anio")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Alumno object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Alumno case classes
      * apply and unapply methods.
      */
    def * = (id, valor, mes, anio) <> ((CuotaBase.apply _).tupled, CuotaBase.unapply)
  }

  /**
    * The starting point for all queries on the anios table.
    */
  private val cuotasbase = TableQuery[CuotaBaseTable]

  //--------------------------------------------


  //CUOTA

  class CuotaTable(tag: Tag) extends Table[Cuota](tag, "cuota") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)


    def cuotaBase = column[Int]("cuota_base")

    def mes = column[Int]("mes")

    def anio = column[Int]("anio")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Alumno object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Alumno case classes
      * apply and unapply methods.
      */
    def * = (id, cuotaBase, mes, anio) <> ((Cuota.apply _).tupled, Cuota.unapply)
  }

  /**
    * The starting point for all queries on the anios table.
    */
  private val cuotas = TableQuery[CuotaTable]


  //-----------------------------------------------------------------



  //-----------------------------------------------------------------

}
