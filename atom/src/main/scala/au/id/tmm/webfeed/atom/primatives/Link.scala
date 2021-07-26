package au.id.tmm.webfeed.atom.primatives

import java.net.URI

final case class Link(
  href: URI,
  rel: Link.Rel,
  `type`: Option[MediaType],
  hreflang: Option[LanguageTag],
  title: Option[String],
  length: Option[String],
)

object Link {
  sealed trait Rel {
    def asString: String = this match {
      case Rel.Self            => "self"
      case Rel.Alternate       => "alternate"
      case Rel.Related         => "related"
      case Rel.Enclosure       => "enclosure"
      case Rel.Via             => "via"
      case Rel.Other(asString) => asString
    }
  }

  object Rel {
    case object Self                                      extends Rel
    case object Alternate                                 extends Rel
    case object Related                                   extends Rel
    case object Enclosure                                 extends Rel
    case object Via                                       extends Rel
    final case class Other(override val asString: String) extends Rel
  }
}
