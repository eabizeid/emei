
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

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*3.1*/("""<!doctype html>
<html lang="en">
    <head>
            <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Security-Policy" content="default-src * 'unsafe-inline'; style-src 'self' http://* 'unsafe-inline'; script-src 'self' http://* 'unsafe-inline' 'unsafe-eval'" />


        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href=""""),_display_(/*13.39*/routes/*13.45*/.Assets.versioned("stylesheets/bootstrap.min.css")),format.raw/*13.95*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(/*14.54*/routes/*14.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*14.101*/("""">

        <title>"""),_display_(/*16.17*/title),format.raw/*16.22*/("""</title>
    </head>
    <body>
        <div id="header">
            <h1><a href="/">EMEI - Escuela Modelo de Educacion Integral</a></h1>
            <ul id="menu">
                <li><a href="/">Inicio</a></li>
                <li><a href="/alumnos">Administración Alumnos</a></li>
                <li><a href="/pagos">Administracion de Pagos</a></li>
                <li><a href="/gastos">Administracion de Gastos</a></li>
                <li><a href="/reports">Reportes</a></li>


            </ul>
        </div>
        """),_display_(/*31.10*/content),format.raw/*31.17*/("""

            """),format.raw/*33.13*/("""<!-- Optional JavaScript -->
            <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="/assets/javascripts/jquery-3.3.1.min.js" ></script>
        <script src="/assets/javascripts/popper.min.js" ></script>
        <script src="/assets/javascripts/bootstrap.min.js" ></script>
        <script src="/assets/javascripts/alumnos.js" ></script>
    </body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue May 15 16:21:49 ART 2018
                  SOURCE: D:/emei/emei/app/views/main.scala.html
                  HASH: 43105a3419cc9182fa0cb470ffb18bb5c87e58cf
                  MATRIX: 733->1|858->31|888->35|1401->521|1416->527|1487->577|1571->634|1586->640|1649->681|1698->703|1724->708|2294->1251|2322->1258|2366->1274
                  LINES: 21->1|26->1|28->3|38->13|38->13|38->13|39->14|39->14|39->14|41->16|41->16|56->31|56->31|58->33
                  -- GENERATED --
              */
          