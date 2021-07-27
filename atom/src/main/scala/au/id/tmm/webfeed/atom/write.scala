package au.id.tmm.webfeed.atom

import au.id.tmm.webfeed.atom.primatives.{Person, Text}

import scala.xml.Node

object write {

  def apply(feed: Feed): Node = {
    <feed xmlns="http://www.w3.org/2005/Atom">
      {toNode(feed.title)("title")}
      {feed.authors.toList.map(toNode(_)("author"))}
    </feed>
  }

  private def toNode(person: Person)(label: String): Node = {
    <person>
      {toNode(person.name)("name")}
      {maybeNode(person.uri)(u => <uri>{u.toString}</uri>)}
      {maybeNode(person.email)(e => <email>{e.asString}</email>)}
    </person>
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

  private def maybeNode[A](option: Option[A])(f: A => Node): Seq[Node] = option.map(f).toList

}
