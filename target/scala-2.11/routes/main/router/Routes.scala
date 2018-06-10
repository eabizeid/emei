// @GENERATOR:play-routes-compiler
// @SOURCE:D:/emei/emei/conf/routes
// @DATE:Tue May 15 16:21:48 ART 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  HomeController_0: controllers.HomeController,
  // @LINE:9
  AsyncController_2: controllers.AsyncController,
  // @LINE:12
  Assets_1: controllers.Assets,
  // @LINE:16
  AlumnoController_3: controllers.AlumnoController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    HomeController_0: controllers.HomeController,
    // @LINE:9
    AsyncController_2: controllers.AsyncController,
    // @LINE:12
    Assets_1: controllers.Assets,
    // @LINE:16
    AlumnoController_3: controllers.AlumnoController
  ) = this(errorHandler, HomeController_0, AsyncController_2, Assets_1, AlumnoController_3, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, AsyncController_2, Assets_1, AlumnoController_3, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """message""", """controllers.AsyncController.message"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """alumno""", """controllers.AlumnoController.addAlumno"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """familia""", """controllers.AlumnoController.addFamilia"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """alumnos""", """controllers.AlumnoController.getAlumnos"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """alumnos/""" + "$" + """id<[^/]+>""", """controllers.AlumnoController.modificar(id:Int)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """alumnos/deactivate/""" + "$" + """id<[^/]+>""", """controllers.AlumnoController.borrar(id:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """buscar""", """controllers.AlumnoController.buscar"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing how to use dependency injection""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_AsyncController_message1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("message")))
  )
  private[this] lazy val controllers_AsyncController_message1_invoker = createInvoker(
    AsyncController_2.message,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AsyncController",
      "message",
      Nil,
      "GET",
      this.prefix + """message""",
      """ An example controller showing how to write asynchronous code""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_Assets_versioned2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned2_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_AlumnoController_addAlumno3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("alumno")))
  )
  private[this] lazy val controllers_AlumnoController_addAlumno3_invoker = createInvoker(
    AlumnoController_3.addAlumno,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AlumnoController",
      "addAlumno",
      Nil,
      "POST",
      this.prefix + """alumno""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:17
  private[this] lazy val controllers_AlumnoController_addFamilia4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("familia")))
  )
  private[this] lazy val controllers_AlumnoController_addFamilia4_invoker = createInvoker(
    AlumnoController_3.addFamilia,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AlumnoController",
      "addFamilia",
      Nil,
      "POST",
      this.prefix + """familia""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_AlumnoController_getAlumnos5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("alumnos")))
  )
  private[this] lazy val controllers_AlumnoController_getAlumnos5_invoker = createInvoker(
    AlumnoController_3.getAlumnos,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AlumnoController",
      "getAlumnos",
      Nil,
      "GET",
      this.prefix + """alumnos""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_AlumnoController_modificar6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("alumnos/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AlumnoController_modificar6_invoker = createInvoker(
    AlumnoController_3.modificar(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AlumnoController",
      "modificar",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """alumnos/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_AlumnoController_borrar7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("alumnos/deactivate/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AlumnoController_borrar7_invoker = createInvoker(
    AlumnoController_3.borrar(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AlumnoController",
      "borrar",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """alumnos/deactivate/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private[this] lazy val controllers_AlumnoController_buscar8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("buscar")))
  )
  private[this] lazy val controllers_AlumnoController_buscar8_invoker = createInvoker(
    AlumnoController_3.buscar,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AlumnoController",
      "buscar",
      Nil,
      "POST",
      this.prefix + """buscar""",
      """""",
      Seq("""nocsrf""")
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index)
      }
  
    // @LINE:9
    case controllers_AsyncController_message1_route(params@_) =>
      call { 
        controllers_AsyncController_message1_invoker.call(AsyncController_2.message)
      }
  
    // @LINE:12
    case controllers_Assets_versioned2_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_versioned2_invoker.call(Assets_1.versioned(path, file))
      }
  
    // @LINE:16
    case controllers_AlumnoController_addAlumno3_route(params@_) =>
      call { 
        controllers_AlumnoController_addAlumno3_invoker.call(AlumnoController_3.addAlumno)
      }
  
    // @LINE:17
    case controllers_AlumnoController_addFamilia4_route(params@_) =>
      call { 
        controllers_AlumnoController_addFamilia4_invoker.call(AlumnoController_3.addFamilia)
      }
  
    // @LINE:18
    case controllers_AlumnoController_getAlumnos5_route(params@_) =>
      call { 
        controllers_AlumnoController_getAlumnos5_invoker.call(AlumnoController_3.getAlumnos)
      }
  
    // @LINE:19
    case controllers_AlumnoController_modificar6_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_AlumnoController_modificar6_invoker.call(AlumnoController_3.modificar(id))
      }
  
    // @LINE:20
    case controllers_AlumnoController_borrar7_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_AlumnoController_borrar7_invoker.call(AlumnoController_3.borrar(id))
      }
  
    // @LINE:22
    case controllers_AlumnoController_buscar8_route(params@_) =>
      call { 
        controllers_AlumnoController_buscar8_invoker.call(AlumnoController_3.buscar)
      }
  }
}
