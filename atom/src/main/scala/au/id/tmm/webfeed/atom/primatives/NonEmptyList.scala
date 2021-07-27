package au.id.tmm.webfeed.atom.primatives

final case class NonEmptyList[+A](head: A, tail: List[A]) {
  def toList: List[A] = head :: tail
}

object NonEmptyList {
  def of[A](head: A, tail: A*): NonEmptyList[A] = NonEmptyList(head, tail.toList)
}
