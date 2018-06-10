
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

object showPagar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Form[PagoForm],Double,List[AlumnoApi],String,MessagesRequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(pagoForm: Form[PagoForm],totalSinDescuento: Double, alumnos: List[AlumnoApi], cuotasAPagar: String)(implicit request: MessagesRequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
/*3.6*/import helper._


Seq[Any](format.raw/*1.143*/("""

    """),format.raw/*4.1*/("""

    """),_display_(/*6.6*/request/*6.13*/.flash.get("success").map/*6.38*/ { key =>_display_(Seq[Any](format.raw/*6.47*/("""
        """),_display_(/*7.10*/request/*7.17*/.messages(key)),format.raw/*7.31*/("""
    """)))}),format.raw/*8.6*/("""


    """),_display_(/*11.6*/main("Cuotas")/*11.20*/ {_display_(Seq[Any](format.raw/*11.22*/("""

    """),format.raw/*13.5*/("""<div id="teaser">
        <div class="wrap">
            <div class="box">
                <h2><em >Cuotas</em></h2>
                <p>Informacionsobre el pago a realizar</p>
            </div>
        </div>
    </div>
    """),_display_(/*21.6*/form(routes.PagoController.realizarPago())/*21.48*/ {_display_(Seq[Any](format.raw/*21.50*/("""
        """),format.raw/*22.9*/("""<div class="wrap">

            <div class="form-row">
                <h3>Cantidad de alumnos: """),_display_(/*25.43*/alumnos/*25.50*/.size),format.raw/*25.55*/("""</h3>
            </div>
            <div class="form-row">
                <h3>Alumnos</h3>
                <table class="table table-bordered">
                    <thead>
                        <th>Apellido</th>
                        <th>Nombre</th>
                        <th>Descuento Especial</th>
                    </thead>
                    <tbody>
                    """),_display_(/*36.22*/for(alumno <- alumnos) yield /*36.44*/ {_display_(Seq[Any](format.raw/*36.46*/("""
                        """),format.raw/*37.25*/("""<tr>
                            <td>"""),_display_(/*38.34*/alumno/*38.40*/.apellidos),format.raw/*38.50*/("""</td>
                            <td>"""),_display_(/*39.34*/alumno/*39.40*/.nombres),format.raw/*39.48*/("""</td>
                            <td>"""),_display_(/*40.34*/alumno/*40.40*/.descuentoEspecial),format.raw/*40.58*/("""</td>
                        </tr>
                    """)))}),format.raw/*42.22*/("""
                    """),format.raw/*43.21*/("""</tbody>
                </table>
            </div>
        </div>
        <div class="wrap">
            <div class="form-group">
                <label>Valor Total Sin Descuentos: """),_display_(/*49.53*/totalSinDescuento),format.raw/*49.70*/("""</label>
            </div>
        </div>
        <div class="wrap">
            <div class="form-row">
                """),_display_(/*54.18*/inputText(pagoForm("totalAPagar"), '_label -> "Total a pagar", '_class -> "form-group col-md-3")),format.raw/*54.114*/("""

            """),format.raw/*56.13*/("""</div>
            <div class="form-row">
                """),_display_(/*58.18*/checkbox(pagoForm("pagoParcial"), '_label -> "Pago parcial?", '_class -> "form-group col-md-3")),format.raw/*58.113*/("""

            """),format.raw/*60.13*/("""</div>
            <button class="btn btn-large btn-primary" type="submit" value="buscar">Pagar</button>
           <input type="hidden" name="cuotasAPagar" value=""""),_display_(/*62.61*/cuotasAPagar),format.raw/*62.73*/("""" />




        </div>
    """)))}),format.raw/*68.6*/("""



    """)))}),format.raw/*72.6*/("""
"""))
      }
    }
  }

  def render(pagoForm:Form[PagoForm],totalSinDescuento:Double,alumnos:List[AlumnoApi],cuotasAPagar:String,request:MessagesRequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(pagoForm,totalSinDescuento,alumnos,cuotasAPagar)(request)

  def f:((Form[PagoForm],Double,List[AlumnoApi],String) => (MessagesRequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (pagoForm,totalSinDescuento,alumnos,cuotasAPagar) => (request) => apply(pagoForm,totalSinDescuento,alumnos,cuotasAPagar)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sun Jun 10 19:19:59 ART 2018
                  SOURCE: D:/emei/emei/app/views/showPagar.scala.html
                  HASH: f7e5df237815c1a6dc20883215b7857dd5f3d499
                  MATRIX: 793->1|1007->151|1053->142|1087->168|1121->177|1136->184|1169->209|1215->218|1252->229|1267->236|1301->250|1337->257|1374->268|1397->282|1437->284|1472->292|1732->526|1783->568|1823->570|1860->580|1987->680|2003->687|2029->692|2453->1089|2491->1111|2531->1113|2585->1139|2651->1178|2666->1184|2697->1194|2764->1234|2779->1240|2808->1248|2875->1288|2890->1294|2929->1312|3019->1371|3069->1393|3286->1583|3324->1600|3478->1727|3596->1823|3640->1839|3728->1900|3845->1995|3889->2011|4083->2178|4116->2190|4181->2225|4224->2238
                  LINES: 21->1|24->3|27->1|29->4|31->6|31->6|31->6|31->6|32->7|32->7|32->7|33->8|36->11|36->11|36->11|38->13|46->21|46->21|46->21|47->22|50->25|50->25|50->25|61->36|61->36|61->36|62->37|63->38|63->38|63->38|64->39|64->39|64->39|65->40|65->40|65->40|67->42|68->43|74->49|74->49|79->54|79->54|81->56|83->58|83->58|85->60|87->62|87->62|93->68|97->72
                  -- GENERATED --
              */
          