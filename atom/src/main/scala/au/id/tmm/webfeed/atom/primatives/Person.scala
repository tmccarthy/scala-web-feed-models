package au.id.tmm.webfeed.atom.primatives

import java.net.URI

final case class Person(
  name: Text,
  uri: URI,
  email: Email,
)
