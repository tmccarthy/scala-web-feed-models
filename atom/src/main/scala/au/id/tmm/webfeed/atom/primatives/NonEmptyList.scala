package au.id.tmm.webfeed.atom.primatives

final case class NonEmptyList[+A](head: A, tail: List[A])
