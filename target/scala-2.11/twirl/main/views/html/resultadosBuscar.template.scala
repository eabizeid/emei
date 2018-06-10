
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

object resultadosBuscar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[Seq[FamiliaApi],MessagesRequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(familias: Seq[FamiliaApi])(implicit request: MessagesRequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
/*3.2*/import helper._


Seq[Any](format.raw/*1.70*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/request/*5.9*/.flash.get("success").map/*5.34*/ { key =>_display_(Seq[Any](format.raw/*5.43*/("""
    """),_display_(/*6.6*/request/*6.13*/.messages(key)),format.raw/*6.27*/("""
""")))}),format.raw/*7.2*/("""


"""),_display_(/*10.2*/main("Alumnos")/*10.17*/ {_display_(Seq[Any](format.raw/*10.19*/("""
    """),format.raw/*11.5*/("""<div id="teaser">
        <div class="wrap">
            <div class="box">
                <h2><em >Alumnos</em></h2>
            </div>
        </div>
    </div>
    <div  class="wrap">
        <div class="search">

        """),_display_(/*21.10*/for(familia <- familias) yield /*21.34*/ {_display_(Seq[Any](format.raw/*21.36*/("""

            """),format.raw/*23.13*/("""<h3>Familia: """),_display_(/*23.27*/familia/*23.34*/.descripcion.toUpperCase),format.raw/*23.58*/("""</h3>
            <h3>Observaciones: """),_display_(/*24.33*/familia/*24.40*/.observaciones),format.raw/*24.54*/("""</h3>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Legajo</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Nivel</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                """),_display_(/*37.18*/for(a <- familia.alumnos) yield /*37.43*/ {_display_(Seq[Any](format.raw/*37.45*/("""
                    """),format.raw/*38.21*/("""<tr>
                        <td scope="row">"""),_display_(/*39.42*/a/*39.43*/.id),format.raw/*39.46*/("""</td>
                        <td>"""),_display_(/*40.30*/a/*40.31*/.legajo),format.raw/*40.38*/("""</td>
                        <td>"""),_display_(/*41.30*/a/*41.31*/.nombres),format.raw/*41.39*/("""</td>
                        <td>"""),_display_(/*42.30*/a/*42.31*/.apellidos),format.raw/*42.41*/("""</td>
                        <td>"""),_display_(/*43.30*/a/*43.31*/.nivel),format.raw/*43.37*/("""</td>
                        <td><button class="btn btn-outline-success btn-sm" type="button"> <a  style="color:inherit" href=""""),_display_(/*44.124*/routes/*44.130*/.AlumnoController.modificar(a.id)),format.raw/*44.163*/("""">Modificar</a></button>
                            <button type="button" class="btn btn-outline-danger btn-sm"> <a methods="DELETE" style="color:inherit" href=""""),_display_(/*45.139*/routes/*45.145*/.AlumnoController.borrar(a.id)),format.raw/*45.175*/("""">Borrar</a></button></td>
                    </tr>
                """)))}),format.raw/*47.18*/("""
                """),format.raw/*48.17*/("""</tbody>

            </table>

        """)))}),format.raw/*52.10*/("""
        """),format.raw/*53.9*/("""</div>
    </div>

""")))}))
      }
    }
  }

  def render(familias:Seq[FamiliaApi],request:MessagesRequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(familias)(request)

  def f:((Seq[FamiliaApi]) => (MessagesRequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (familias) => (request) => apply(familias)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue May 15 16:21:49 ART 2018
                  SOURCE: D:/emei/emei/app/views/resultadosBuscar.scala.html
                  HASH: e2bf6edbe701c15605deb88186efbb58fc978713
                  MATRIX: 771->1|912->74|957->69|987->91|1015->94|1029->101|1062->126|1108->135|1140->142|1155->149|1189->163|1221->166|1254->173|1278->188|1318->190|1351->196|1614->432|1654->456|1694->458|1738->474|1779->488|1795->495|1840->519|1906->558|1922->565|1957->579|2505->1100|2546->1125|2586->1127|2636->1149|2710->1196|2720->1197|2744->1200|2807->1236|2817->1237|2845->1244|2908->1280|2918->1281|2947->1289|3010->1325|3020->1326|3051->1336|3114->1372|3124->1373|3151->1379|3309->1509|3325->1515|3380->1548|3572->1712|3588->1718|3640->1748|3743->1820|3789->1838|3865->1883|3902->1893
                  LINES: 21->1|24->3|27->1|29->4|30->5|30->5|30->5|30->5|31->6|31->6|31->6|32->7|35->10|35->10|35->10|36->11|46->21|46->21|46->21|48->23|48->23|48->23|48->23|49->24|49->24|49->24|62->37|62->37|62->37|63->38|64->39|64->39|64->39|65->40|65->40|65->40|66->41|66->41|66->41|67->42|67->42|67->42|68->43|68->43|68->43|69->44|69->44|69->44|70->45|70->45|70->45|72->47|73->48|77->52|78->53
                  -- GENERATED --
              */
          