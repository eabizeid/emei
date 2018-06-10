// @GENERATOR:play-routes-compiler
// @SOURCE:D:/emei/emei/conf/routes
// @DATE:Tue May 15 16:21:48 ART 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers {

  // @LINE:9
  class ReverseAsyncController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def message(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "message")
    }
  
  }

  // @LINE:16
  class ReverseAlumnoController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def getAlumnos(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "alumnos")
    }
  
    // @LINE:19
    def modificar(id:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "alumnos/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
    // @LINE:16
    def addAlumno(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "alumno")
    }
  
    // @LINE:20
    def borrar(id:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "alumnos/deactivate/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
    // @LINE:17
    def addFamilia(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "familia")
    }
  
    // @LINE:22
    def buscar(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "buscar")
    }
  
  }

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:12
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def versioned(file:String): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[String]].unbind("file", file))
    }
  
  }


}
