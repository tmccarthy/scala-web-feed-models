package au.id.tmm.webfeed.atom.primatives

import scala.xml.Node

sealed trait Text

object Text {

  final case class Raw(asString: String)  extends Text
  final case class Html(asString: String) extends Text
  final case class XHtml(asXml: Node)     extends Text

}
