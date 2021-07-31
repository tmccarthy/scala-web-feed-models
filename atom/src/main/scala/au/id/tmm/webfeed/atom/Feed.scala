package au.id.tmm.webfeed.atom

import java.net.URI

import au.id.tmm.webfeed.atom.common._
import au.id.tmm.webfeed.atom.primatives._

final case class Feed(
  language: Option[LanguageTag],
  authors: NonEmptyList[Person],
  categories: List[Category],
  content: Option[Content],
  generator: Option[Feed.Generator],
  icon: Option[URI],
  logo: Option[URI],
  id: Id,
  links: List[Link],
  rights: Option[Text],
  subtitle: Option[Text],
  title: Text,
  updated: Date,
  entries: List[Entry],
)

object Feed {
  final case class Generator(
    description: String,
    uri: Option[URI],
    version: Option[String],
  )
}
