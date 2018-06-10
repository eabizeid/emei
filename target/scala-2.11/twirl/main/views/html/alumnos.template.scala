
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

object alumnos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Form[CreateAlumnoForm],Form[CreateFamiliaForm],Seq[FamiliaApi],List[NivelApi],List[GradoApi],List[NivelGradoApi],List[FamiliaApi],MessagesRequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(alumno: Form[CreateAlumnoForm], familia: Form[CreateFamiliaForm], familias: Seq[FamiliaApi], niveles: List[NivelApi], grados: List[GradoApi], nivelGrados: List[NivelGradoApi], familiasWithoutAlumnos: List[FamiliaApi])(implicit request: MessagesRequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
/*3.6*/import helper._


Seq[Any](format.raw/*1.261*/("""

    """),format.raw/*4.1*/("""

    """),_display_(/*6.6*/request/*6.13*/.flash.get("success").map/*6.38*/ { key =>_display_(Seq[Any](format.raw/*6.47*/("""
        """),_display_(/*7.10*/request/*7.17*/.messages(key)),format.raw/*7.31*/("""
    """)))}),format.raw/*8.6*/("""


    """),_display_(/*11.6*/main("Alumnos")/*11.21*/ {_display_(Seq[Any](format.raw/*11.23*/("""

    """),format.raw/*13.5*/("""<div id="teaser">
        <div class="wrap">
            <div class="box">
                <h2><em >Alumnos</em></h2>
                <p>En esta sección podrá Consultar familias y Alumnos, como tambien dar de alta, modificar y eliminar</p>
            </div>
        </div>
    </div>
        <div class="buttons wrap">

            <button id="buttonAlumno" class="btn btn-large btn-primary" type="button"  value="buscar">Agregar Alumno</button>
            <button id="buttonFamilia" class="btn btn-large btn-primary" type="button" value="buscar">Agregar Familia</button>
            <button id="buttonBuscar" class="btn btn-large btn-primary" type="button" value="buscar">Buscar</button>

        </div>

        <div id="agregar" class="wrap">
            <div class="search">
                <h3>Agregar <span class="red">Alumno </span></h3>
            """),_display_(/*32.14*/form(routes.AlumnoController.addAlumno())/*32.55*/{_display_(Seq[Any](format.raw/*32.56*/("""
                """),format.raw/*33.17*/("""<div class="form-row">
                    """),_display_(/*34.22*/select(alumno("familia"), familiasWithoutAlumnos.sortBy(_.descripcion).map(f => f.id.toString -> f.descripcion), '_label -> "Familia", 'class -> "form-control", '_class -> "form-group col-md-6")),format.raw/*34.216*/("""
                    """),_display_(/*35.22*/inputText(alumno("legajo"), '_label -> "Legajo", '_class -> "form-group col-md-6")),format.raw/*35.104*/("""
                """),format.raw/*36.17*/("""</div>
                <div class="form-row">
                    """),_display_(/*38.22*/inputText(alumno("nombres"), '_label -> "Nombres", '_class -> "form-group col-md-6")),format.raw/*38.106*/("""
                    """),_display_(/*39.22*/inputText(alumno("apellidos"), '_label -> "Apellidos", '_class -> "form-group col-md-6")),format.raw/*39.110*/("""
                """),format.raw/*40.17*/("""</div>
                <div class="form-row">

                        """),_display_(/*43.26*/select(alumno("nivel"),nivelGrados.sortBy(_.nivel.nivel).map(n => n.id.toString -> s"${n.nivel.nivel} - ${n.grado.grado}"), '_label -> "Nivel", 'class -> "form-control", '_class -> "form-group col-md-6")),format.raw/*43.229*/("""

                """),format.raw/*45.17*/("""</div>
                <div>
                    <input id="id" type="hidden" name="id" value=""""),_display_(/*47.68*/alumno/*47.74*/.data.get("id")),format.raw/*47.89*/("""" />
                </div>
                """),_display_(/*49.18*/CSRF/*49.22*/.formField),format.raw/*49.32*/("""

                """),format.raw/*51.17*/("""<div class="buttons">

                    <button class="btn btn-large btn-primary" type="submit" value="buscar">Agregar Alumno</button>
                    <button id="cancelAlumno" class="btn btn-large btn-danger" type="button">Cancelar</button>


                </div>
            """)))}),format.raw/*58.14*/("""
            """),format.raw/*59.13*/("""</div>

        </div>

    <div id="agregarFamilia" class="wrap">
        <div class="search">
            <h3>Agregar <span class="red">Familia </span></h3>
            """),_display_(/*66.14*/form(routes.AlumnoController.addFamilia())/*66.56*/{_display_(Seq[Any](format.raw/*66.57*/("""
                """),format.raw/*67.17*/("""<div class="form-row">
                    """),_display_(/*68.22*/inputText(familia("descripcion"), '_label -> "Descripcion", '_class -> "form-group col-md-6")),format.raw/*68.115*/("""
                """),format.raw/*69.17*/("""</div>
                <div class="form-row">
                    """),_display_(/*71.22*/textarea(familia("observaciones"), '_label -> "Observaciones", '_class -> "form-group col-md-6")),format.raw/*71.118*/("""
                """),format.raw/*72.17*/("""</div>
                <div class="form-row">
                """),_display_(/*74.18*/helper/*74.24*/.repeat(familia("contactos"), min = 2)/*74.62*/ { contactField =>_display_(Seq[Any](format.raw/*74.80*/("""
                    """),format.raw/*75.21*/("""<div class="form-row">
                        """),_display_(/*76.26*/helper/*76.32*/.inputText(familia(contactField.name.toString + ".label"),'_label -> "Nombre Contacto ",   '_class -> "form-group col-md-4")),format.raw/*76.156*/("""
                        """),_display_(/*77.26*/helper/*77.32*/.inputText(familia(contactField.name.toString + ".email"), '_label -> "Correo electronico ", '_class -> "form-group col-md-4")),format.raw/*77.158*/("""
                        """),_display_(/*78.26*/helper/*78.32*/.inputText(familia(contactField.name.toString + ".phones"), '_label -> "Telefono ", '_class -> "form-group col-md-4" )),format.raw/*78.150*/("""
                    """),format.raw/*79.21*/("""</div>
                """)))}),format.raw/*80.18*/("""

                """),format.raw/*82.17*/("""</div>
                """),_display_(/*83.18*/CSRF/*83.22*/.formField),format.raw/*83.32*/("""

                """),format.raw/*85.17*/("""<div class="buttons">

                    <button class="btn btn-large btn-primary" type="submit" value="buscar">Agregar Familia</button>
                    <button id="cancelFamilia" class="btn btn-large btn-danger" type="button">Cancelar</button>


                </div>
            """)))}),format.raw/*92.14*/("""
        """),format.raw/*93.9*/("""</div>

    </div>

    <div id="buscar" class="wrap">
        <div class="search">
            <h3>Bus<span class="red">car </span></h3>

            """),_display_(/*101.14*/form(routes.AlumnoController.buscar())/*101.52*/ {_display_(Seq[Any](format.raw/*101.54*/("""

            """),format.raw/*103.13*/("""<div class="form-group" >
                <label for="familia">Familia</label>
                <select class="form-control" name="familia" id="familia">
                 <option value="">  </option>
                """),_display_(/*107.18*/for( familia <- familias.sortBy(_.descripcion)) yield /*107.65*/ {_display_(Seq[Any](format.raw/*107.67*/("""
                    """),format.raw/*108.21*/("""<option value=""""),_display_(/*108.37*/familia/*108.44*/.id),format.raw/*108.47*/(""""> """),_display_(/*108.51*/familia/*108.58*/.descripcion),format.raw/*108.70*/(""" """),format.raw/*108.71*/("""</option>
                """)))}),format.raw/*109.18*/("""
                """),format.raw/*110.17*/("""</select>


            </div>
                <div class="form-group">
                    <label for="legajo" >Legajo</label>
                    <input  class="form-control"  type="text" name="legajo" id="legajo">
                </div>
            <div class="form-group" >
               <label for="nivel">Nivel</label>
                <select class="form-control" name="nivel" id="nivel">
                    <option value="">  </option>
                """),_display_(/*122.18*/for( nivel <- niveles) yield /*122.40*/ {_display_(Seq[Any](format.raw/*122.42*/("""
                    """),format.raw/*123.21*/("""<option value=""""),_display_(/*123.37*/nivel/*123.42*/.id),format.raw/*123.45*/(""""> """),_display_(/*123.49*/nivel/*123.54*/.nivel),format.raw/*123.60*/(""" """),format.raw/*123.61*/("""</option>
                """)))}),format.raw/*124.18*/("""
                """),format.raw/*125.17*/("""</select>
            </div>

            <div class="form-group" >
                <label for="grado">Grado</label>
                <select class="form-control" name="grado" id="grado">
                    <option value="">  </option>
                """),_display_(/*132.18*/for( grado <- grados) yield /*132.39*/ {_display_(Seq[Any](format.raw/*132.41*/("""
                    """),format.raw/*133.21*/("""<option value=""""),_display_(/*133.37*/grado/*133.42*/.id),format.raw/*133.45*/(""""> """),_display_(/*133.49*/grado/*133.54*/.grado),format.raw/*133.60*/(""" """),format.raw/*133.61*/("""</option>
                """)))}),format.raw/*134.18*/("""
                """),format.raw/*135.17*/("""</select>
            </div>

            <br /> <br />
            <br />
            <div class="buttons">
                <button class="btn btn-large btn-primary" type="submit" value="buscar">Buscar</button>
                <button id="cancelBuscar" class="btn btn-large btn-danger" type="button">Cancelar</button>

            </div>
            """)))}),format.raw/*145.14*/("""
        """),format.raw/*146.9*/("""</div>
    </div>


    """)))}),format.raw/*150.6*/("""
"""))
      }
    }
  }

  def render(alumno:Form[CreateAlumnoForm],familia:Form[CreateFamiliaForm],familias:Seq[FamiliaApi],niveles:List[NivelApi],grados:List[GradoApi],nivelGrados:List[NivelGradoApi],familiasWithoutAlumnos:List[FamiliaApi],request:MessagesRequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(alumno,familia,familias,niveles,grados,nivelGrados,familiasWithoutAlumnos)(request)

  def f:((Form[CreateAlumnoForm],Form[CreateFamiliaForm],Seq[FamiliaApi],List[NivelApi],List[GradoApi],List[NivelGradoApi],List[FamiliaApi]) => (MessagesRequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (alumno,familia,familias,niveles,grados,nivelGrados,familiasWithoutAlumnos) => (request) => apply(alumno,familia,familias,niveles,grados,nivelGrados,familiasWithoutAlumnos)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue May 15 16:21:49 ART 2018
                  SOURCE: D:/emei/emei/app/views/alumnos.scala.html
                  HASH: 5f0940a3192a63716e0222b81df04fab811d6f51
                  MATRIX: 876->1|1208->269|1254->260|1288->286|1322->295|1337->302|1370->327|1416->336|1453->347|1468->354|1502->368|1538->375|1575->386|1599->401|1639->403|1674->411|2580->1290|2630->1331|2669->1332|2715->1350|2787->1395|3003->1589|3053->1612|3157->1694|3203->1712|3299->1781|3405->1865|3455->1888|3565->1976|3611->1994|3713->2069|3938->2272|3986->2292|4111->2390|4126->2396|4162->2411|4236->2458|4249->2462|4280->2472|4328->2492|4653->2786|4695->2800|4901->2979|4952->3021|4991->3022|5037->3040|5109->3085|5224->3178|5270->3196|5366->3265|5484->3361|5530->3379|5622->3444|5637->3450|5684->3488|5740->3506|5790->3528|5866->3577|5881->3583|6027->3707|6081->3734|6096->3740|6244->3866|6298->3893|6313->3899|6453->4017|6503->4039|6559->4064|6607->4084|6659->4109|6672->4113|6703->4123|6751->4143|7078->4439|7115->4449|7303->4609|7351->4647|7392->4649|7437->4665|7685->4885|7749->4932|7790->4934|7841->4956|7885->4972|7902->4979|7927->4982|7959->4986|7976->4993|8010->5005|8040->5006|8100->5034|8147->5052|8649->5526|8688->5548|8729->5550|8780->5572|8824->5588|8839->5593|8864->5596|8896->5600|8911->5605|8939->5611|8969->5612|9029->5640|9076->5658|9364->5918|9402->5939|9443->5941|9494->5963|9538->5979|9553->5984|9578->5987|9610->5991|9625->5996|9653->6002|9683->6003|9743->6031|9790->6049|10184->6411|10222->6421|10282->6450
                  LINES: 21->1|24->3|27->1|29->4|31->6|31->6|31->6|31->6|32->7|32->7|32->7|33->8|36->11|36->11|36->11|38->13|57->32|57->32|57->32|58->33|59->34|59->34|60->35|60->35|61->36|63->38|63->38|64->39|64->39|65->40|68->43|68->43|70->45|72->47|72->47|72->47|74->49|74->49|74->49|76->51|83->58|84->59|91->66|91->66|91->66|92->67|93->68|93->68|94->69|96->71|96->71|97->72|99->74|99->74|99->74|99->74|100->75|101->76|101->76|101->76|102->77|102->77|102->77|103->78|103->78|103->78|104->79|105->80|107->82|108->83|108->83|108->83|110->85|117->92|118->93|126->101|126->101|126->101|128->103|132->107|132->107|132->107|133->108|133->108|133->108|133->108|133->108|133->108|133->108|133->108|134->109|135->110|147->122|147->122|147->122|148->123|148->123|148->123|148->123|148->123|148->123|148->123|148->123|149->124|150->125|157->132|157->132|157->132|158->133|158->133|158->133|158->133|158->133|158->133|158->133|158->133|159->134|160->135|170->145|171->146|175->150
                  -- GENERATED --
              */
          