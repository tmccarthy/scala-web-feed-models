package au.id.tmm.webfeed.atom

import au.id.tmm.webfeed.atom.primatives._
import au.id.tmm.webfeed.atom.common._

final case class Entry(
  language: LanguageTag,
  authors: NonEmptyList[Person],
  categories: List[Person],
  content: Option[Content],
  contributors: List[Person],
  id: Id,
  links: Links,
  published: Option[Date],
  rights: Option[Text],
//  source: Option[Entry.Source],
  summary: Option[Text],
  title: Text,
  updated: Date,
)
