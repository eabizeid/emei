
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

object resultadosBuscarPagosPorFamilia extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[Seq[PagoApi],MessagesRequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(pagos: Seq[PagoApi])(implicit request: MessagesRequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
/*3.2*/import helper._


Seq[Any](format.raw/*1.64*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/request/*5.9*/.flash.get("success").map/*5.34*/ { key =>_display_(Seq[Any](format.raw/*5.43*/("""
    """),_display_(/*6.6*/request/*6.13*/.messages(key)),format.raw/*6.27*/("""
""")))}),format.raw/*7.2*/("""


"""),_display_(/*10.2*/main("Alumnos")/*10.17*/ {_display_(Seq[Any](format.raw/*10.19*/("""
    """),format.raw/*11.5*/("""<div id="teaser">
        <div class="wrap">
            <div class="box">
                <h2><em >Pagos Pendientes</em></h2>
            </div>
        </div>
    </div>
    <div  class="wrap">
        <div class="search">
        """),_display_(/*20.10*/form(routes.PagoController.generarPago())/*20.51*/ {_display_(Seq[Any](format.raw/*20.53*/("""
            """),format.raw/*21.13*/("""<table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Año</th>
                        <th scope="col">Mes</th>
                        <th scope="col">Valor Cuota Base</th>
                        <th scope="col">Pago Parcial</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                """),_display_(/*33.18*/for(pago <- pagos) yield /*33.36*/ {_display_(Seq[Any](format.raw/*33.38*/("""
                    """),format.raw/*34.21*/("""<tr>
                        <td></td>
                        <td>"""),_display_(/*36.30*/pago/*36.34*/.anioCuota),format.raw/*36.44*/("""</td>
                        <td>"""),_display_(/*37.30*/pago/*37.34*/.mesCuota),format.raw/*37.43*/("""</td>
                        <td>"""),_display_(/*38.30*/pago/*38.34*/.valorCuotaBase),format.raw/*38.49*/("""</td>
                        <td>"""),_display_(/*39.30*/pago/*39.34*/.pagoParcial),format.raw/*39.46*/("""</td>
                        <td><input type="checkbox" name="cuotasAPagar" value=""""),_display_(/*40.80*/pago/*40.84*/.id),format.raw/*40.87*/("""" /> </td>
                    </tr>
                """)))}),format.raw/*42.18*/("""
                """),format.raw/*43.17*/("""</tbody>

            </table>
            <button class="btn btn-large btn-primary" type="submit" value="generar pago">Generar Pago</button>
        """)))}),format.raw/*47.10*/("""
        """),format.raw/*48.9*/("""</div>
    </div>

""")))}))
      }
    }
  }

  def render(pagos:Seq[PagoApi],request:MessagesRequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(pagos)(request)

  def f:((Seq[PagoApi]) => (MessagesRequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (pagos) => (request) => apply(pagos)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sun Jun 10 19:19:59 ART 2018
                  SOURCE: D:/emei/emei/app/views/resultadosBuscarPagosPorFamilia.scala.html
                  HASH: ab42ad4f060a266eba23c09f1c369419ebc49edf
                  MATRIX: 783->1|918->68|963->63|993->85|1021->88|1035->95|1068->120|1114->129|1146->136|1161->143|1195->157|1227->160|1260->167|1284->182|1324->184|1357->190|1627->433|1677->474|1717->476|1759->490|2295->999|2329->1017|2369->1019|2419->1041|2516->1111|2529->1115|2560->1125|2623->1161|2636->1165|2666->1174|2729->1210|2742->1214|2778->1229|2841->1265|2854->1269|2887->1281|3000->1367|3013->1371|3037->1374|3124->1430|3170->1448|3356->1603|3393->1613
                  LINES: 21->1|24->3|27->1|29->4|30->5|30->5|30->5|30->5|31->6|31->6|31->6|32->7|35->10|35->10|35->10|36->11|45->20|45->20|45->20|46->21|58->33|58->33|58->33|59->34|61->36|61->36|61->36|62->37|62->37|62->37|63->38|63->38|63->38|64->39|64->39|64->39|65->40|65->40|65->40|67->42|68->43|72->47|73->48
                  -- GENERATED --
              */
          