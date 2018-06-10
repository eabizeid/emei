
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

object createAlumno extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[Form[CreateAlumnoForm],MessagesRequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(alumno: Form[CreateAlumnoForm])(implicit request: MessagesRequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
/*3.6*/import helper._


Seq[Any](format.raw/*1.75*/("""

    """),format.raw/*4.1*/("""
    """),_display_(/*5.6*/request/*5.13*/.flash.get("success").map/*5.38*/ { key =>_display_(Seq[Any](format.raw/*5.47*/("""
        """),_display_(/*6.10*/request/*6.17*/.messages(key)),format.raw/*6.31*/("""
    """)))}),format.raw/*7.6*/("""

    """),_display_(/*9.6*/main("Welcome to Play")/*9.29*/ {_display_(Seq[Any](format.raw/*9.31*/("""

    """)))}),format.raw/*11.6*/("""
"""))
      }
    }
  }

  def render(alumno:Form[CreateAlumnoForm],request:MessagesRequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(alumno)(request)

  def f:((Form[CreateAlumnoForm]) => (MessagesRequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (alumno) => (request) => apply(alumno)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue May 15 16:21:49 ART 2018
                  SOURCE: D:/emei/emei/app/views/createAlumno.scala.html
                  HASH: 36e8ec894d1b347671a5528cfde2ff448b4befd4
                  MATRIX: 774->1|920->83|965->74|999->100|1031->107|1046->114|1079->139|1125->148|1162->159|1177->166|1211->180|1247->187|1281->196|1312->219|1351->221|1390->230
                  LINES: 21->1|24->3|27->1|29->4|30->5|30->5|30->5|30->5|31->6|31->6|31->6|32->7|34->9|34->9|34->9|36->11
                  -- GENERATED --
              */
          