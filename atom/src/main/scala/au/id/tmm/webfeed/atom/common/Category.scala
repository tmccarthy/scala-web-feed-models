package au.id.tmm.webfeed.atom.common

import java.net.URI

final case class Category(
  term: String,
  scheme: Option[URI],
  label: Option[String],
)
