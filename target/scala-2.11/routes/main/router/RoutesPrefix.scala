// @GENERATOR:play-routes-compiler
// @SOURCE:D:/emei/emei/conf/routes
// @DATE:Tue May 15 16:21:48 ART 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
