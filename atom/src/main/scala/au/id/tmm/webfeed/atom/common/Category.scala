package au.id.tmm.webfeed.atom.common

import java.net.URI

import au.id.tmm.webfeed.atom.primatives.Text

final case class Category(
  term: Text,
  scheme: URI,
  label: Text,
)
