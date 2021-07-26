package au.id.tmm.webfeed.atom.common

import au.id.tmm.webfeed.atom.primatives.Link

final case class Links(
  self: Link,
  alternate: Option[Link],
  others: List[Link],
)
