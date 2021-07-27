package au.id.tmm.webfeed.atom.primatives

import java.net.URI

final case class Person(
  name: Text,
  uri: Option[URI],
  email: Option[Email],
)
