
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object pagarCuota extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[BuscarCuotasPorFamiliaForm],List[FamiliaApi],MessagesRequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(buscarForm: Form[BuscarCuotasPorFamiliaForm], familiasWithoutAlumnos: List[FamiliaApi])(implicit request: MessagesRequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
/*3.6*/import helper._


Seq[Any](format.raw/*1.131*/("""

    """),format.raw/*4.1*/("""

    """),_display_(/*6.6*/request/*6.13*/.flash.get("success").map/*6.38*/ { key =>_display_(Seq[Any](format.raw/*6.47*/("""
        """),_display_(/*7.10*/request/*7.17*/.messages(key)),format.raw/*7.31*/("""
    """)))}),format.raw/*8.6*/("""


    """),_display_(/*11.6*/main("Cuotas")/*11.20*/ {_display_(Seq[Any](format.raw/*11.22*/("""

    """),format.raw/*13.5*/("""<div id="teaser">
        <div class="wrap">
            <div class="box">
                <h2><em >Cuotas</em></h2>
                <p>En esta sección podrá Generar la obligación de pago Mensual, consultar las cuotas adeudadas por familia y generar el pago</p>
            </div>
        </div>
    </div>
        <div class="wrap">
            <h3>Generar Obligacion de pagos Mensual</h3>
            """),_display_(/*23.14*/form(routes.PagoController.generarObligacionDePagoMensual())/*23.74*/ {_display_(Seq[Any](format.raw/*23.76*/("""
                """),_display_(/*24.18*/inputText(buscarForm("valorCuotaBase"))),format.raw/*24.57*/("""
                """),format.raw/*25.17*/("""<button class="btn btn-large btn-primary" type="submit" value="buscar">Obligacion de pago mensual</button>
            """)))}),format.raw/*26.14*/("""
            """),format.raw/*27.13*/("""<h3>Consultar pagos pendientes por familia</h3>
            """),_display_(/*28.14*/form(routes.PagoController.buscar())/*28.50*/ {_display_(Seq[Any](format.raw/*28.52*/("""
                """),format.raw/*29.17*/("""<div class="form-row">
                """),_display_(/*30.18*/select(buscarForm("familia"), familiasWithoutAlumnos.sortBy(_.descripcion).map(f => f.id.toString -> f.descripcion), '_label -> "Familia", 'class -> "form-control", '_class -> "form-group col-md-6")),format.raw/*30.216*/("""
                """),format.raw/*31.17*/("""</div>
                <button class="btn btn-large btn-primary" type="submit" value="buscar">Buscar</button>
            """)))}),format.raw/*33.14*/("""

        """),format.raw/*35.9*/("""</div>




    """)))}),format.raw/*40.6*/("""
"""))
      }
    }
  }

  def render(buscarForm:Form[BuscarCuotasPorFamiliaForm],familiasWithoutAlumnos:List[FamiliaApi],request:MessagesRequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(buscarForm,familiasWithoutAlumnos)(request)

  def f:((Form[BuscarCuotasPorFamiliaForm],List[FamiliaApi]) => (MessagesRequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (buscarForm,familiasWithoutAlumnos) => (request) => apply(buscarForm,familiasWithoutAlumnos)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sun Jun 10 19:19:59 ART 2018
                  SOURCE: D:/emei/emei/app/views/pagarCuota.scala.html
                  HASH: 89ee2bd220d2a988fb082ccca4054068f2d42379
                  MATRIX: 799->1|1001->139|1047->130|1081->156|1115->165|1130->172|1163->197|1209->206|1246->217|1261->224|1295->238|1331->245|1368->256|1391->270|1431->272|1466->280|1907->694|1976->754|2016->756|2062->775|2122->814|2168->832|2320->953|2362->967|2451->1029|2496->1065|2536->1067|2582->1085|2650->1126|2870->1324|2916->1342|3072->1467|3111->1479|3162->1500
                  LINES: 21->1|24->3|27->1|29->4|31->6|31->6|31->6|31->6|32->7|32->7|32->7|33->8|36->11|36->11|36->11|38->13|48->23|48->23|48->23|49->24|49->24|50->25|51->26|52->27|53->28|53->28|53->28|54->29|55->30|55->30|56->31|58->33|60->35|65->40
                  -- GENERATED --
              */
          