package au.id.tmm.webfeed.atom.primatives

sealed trait Text

object Text {

  final case class Raw(asString: String)   extends Text
  final case class Html(asString: String)  extends Text
  final case class XHtml(asString: String) extends Text

}
