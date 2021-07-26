package au.id.tmm.webfeed.atom.common

import java.net.URI

import au.id.tmm.webfeed.atom.primatives.{MediaType, Text}

sealed trait Content

object Content {

  final case class InlineText(text: Text)                              extends Content
  final case class InlineOther(mediaType: MediaType, asString: String) extends Content
  final case class OutOfLine(mediaType: MediaType, uri: URI)           extends Content

}
