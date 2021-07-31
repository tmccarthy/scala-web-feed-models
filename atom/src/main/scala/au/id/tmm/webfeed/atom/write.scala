package au.id.tmm.webfeed.atom

import java.net.URI

import au.id.tmm.webfeed.atom.Feed.Generator
import au.id.tmm.webfeed.atom.common.{Category, Content, Id}
import au.id.tmm.webfeed.atom.primatives.{Date, Link, NonEmptyList, Person, Text}

import scala.xml.Node

object write {

  def apply(feed: Feed): Node = {
    <feed xmlns="http://www.w3.org/2005/Atom">
      {toNode(feed.title)("title")}
      {maybeNode(feed.subtitle)(toNode(_)("subtitle"))}
      {toNode(feed.id)}
      {toNode(feed.updated)("updated")}
      {nodes(feed.links)(toNode)}
      {nodes(feed.categories)(toNode)}
      {maybeNode(feed.content)(toNode)}
      {maybeNode(feed.generator)(toNode)}
      {maybeNode(feed.icon)(toNode(_)("icon"))}
      {maybeNode(feed.logo)(toNode(_)("logo"))}
      {maybeNode(feed.rights)(toNode(_)("rights"))}
      {nodes(feed.authors)(toNode(_)("author"))}
      {nodes(feed.entries)(toNode)}
    </feed>
  }

  private def toNode(person: Person)(label: String): Node = {
    <person>
      {toNode(person.name)("name")}
      {maybeNode(person.uri)(u => <uri>{u.toString}</uri>)}
      {maybeNode(person.email)(e => <email>{e.asString}</email>)}
    </person>.copy(label = label)
  }

  private def toNode(text: Text)(label: String): Node = {
    val nodeChild: Node = text match {
      case Text.Raw(asString) => scala.xml.Text(asString)
      case Text.Html(asString) => scala.xml.Text(asString)
      case Text.XHtml(asXml) => asXml
    }

    val typeAttributeValue = text match {
      case Text.Raw(_) => "text"
      case Text.Html(_) => "html"
      case Text.XHtml(_) => "xhtml"
    }

    <text type={typeAttributeValue}>{nodeChild}</text>.copy(label = label)
  }

  private def toNode(id: Id): Node = <id>{id.asString}</id>

  private def toNode(date: Date)(label: String): Node = <date>{date.asInstant.toString}</date>.copy(label = label)

  private def toNode(link: Link): Node =
    <link
      href={link.href.toString}
      rel={maybeAttribute(link.rel.map(_.asString))}
      type={maybeAttribute(link.`type`.map(_.asString))}
      hreflang={maybeAttribute(link.hreflang.map(_.asString))}
      title={maybeAttribute(link.title)}
      length={maybeAttribute(link.length)}
    />

  private def toNode(category: Category): Node =
    <category
      term={category.term}
      scheme={maybeAttribute(category.scheme.map(_.toString))}
      label={maybeAttribute(category.label)}
    />

  private def toNode(content: Content): Node = ???

  private def toNode(generator: Generator): Node =
    <generator uri={maybeAttribute(generator.uri.map(_.toString))} version={maybeAttribute(generator.version)}>
      {generator.description}
    </generator>

  private def toNode(uri: URI)(label: String): Node = <uri>{uri.toString}</uri>.copy(label = label)

  private def toNode(entry: Entry): Node =
    <entry>
      {toNode(entry.title)("title")}
      {toNode(entry.id)}
      {nodes(entry.links)(toNode)}
      {maybeNode(entry.published)(toNode(_)("published"))}
      {toNode(entry.updated)("updated")}
      {nodes(entry.authors)(toNode(_)("author"))}
      {nodes(entry.contributors)(toNode(_)("contributor"))}
      {nodes(entry.categories)(toNode)}
      {maybeNode(entry.rights)(toNode(_)("rights"))}
      {maybeNode(entry.summary)(toNode(_)("summary"))}
      {maybeNode(entry.content)(toNode)}
    </entry>

  private def maybeNode[A](option: Option[A])(f: A => Node): Seq[Node] = option.map(f).toList

  private def nodes[A](list: NonEmptyList[A])(f: A => Node): Seq[Node] = nodes(list.toList)(f)

  private def nodes[A](list: List[A])(f: A => Node): Seq[Node] = list.map(f)

  private def maybeAttribute(option: Option[String]): String = option.orNull

}
