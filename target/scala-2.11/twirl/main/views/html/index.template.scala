
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
/*1.6*/import helper._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*5.6*/main("Alumnos")/*5.21*/ {_display_(Seq[Any](format.raw/*5.23*/("""

    """),format.raw/*7.5*/("""<div id="teaser">
        <div class="wrap">
            <div class="box">
                <h2><em >Sistema EMEI</em></h2>
                <p>Podrá administrar alumnos, familias, pagos, gastos y sacar reportes</p>
            </div>
        </div>
    </div>
        <<div class="buttons wrap">

            <button id="buttonAlumno" class="btn btn-large btn-primary" type="button"  value="buscar">Administrar Alumnos</button>
            <button id="buttonFamilia" class="btn btn-large btn-primary" type="button" value="buscar">Administrar Pagos</button>
            <button id="buttonBuscar" class="btn btn-large btn-primary" type="button" value="buscar">Administrar Gastos</button>
        <button id="buttonBuscar" class="btn btn-large btn-primary" type="button" value="buscar">Reportes</button>

        </div>


    </div>


    """)))}),format.raw/*28.6*/("""
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue May 15 16:21:49 ART 2018
                  SOURCE: D:/emei/emei/app/views/index.scala.html
                  HASH: aab5a24e24be3c334876ecb862d0b460321fa10a
                  MATRIX: 432->5|834->33|857->48|896->50|930->58|1817->915
                  LINES: 17->1|27->5|27->5|27->5|29->7|50->28
                  -- GENERATED --
              */
          