package au.id.tmm.webfeed.atom

import java.net.URI
import java.time.Instant

import au.id.tmm.webfeed.atom.WriteTest.assertXmlEquals
import au.id.tmm.webfeed.atom.common._
import au.id.tmm.webfeed.atom.primatives._
import munit.{Assertions, FunSuite}

import scala.xml.{Node, PrettyPrinter}

class WriteTest extends FunSuite {

  test("simple atom feed") {
    val feed: Feed = Feed(
      language = None,
      authors = NonEmptyList.of(
        Person(
          name = Text.Raw("John Doe"),
          uri = None,
          email = None,
        ),
      ),
      categories = List(
        Category(
          term = "Test post",
          scheme = Some(URI.create("http://example.org/")),
          label = Some("Some label"),
        )
      ),
      content = None,
      generator = None,
      icon = None,
      logo = None,
      id = Id("urn:uuid:60a76c80-d399-11d9-b93C-0003939e0af6"),
      links = List(
        Link(
          href = URI.create("http://example.org/"),
          rel = None,
          `type` = None,
          hreflang = None,
          title = None,
          length = None,
        ),
      ),
      rights = None,
      subtitle = None,
      title = Text.Raw("Example Feed"),
      updated = Date(Instant.parse("2003-12-13T18:30:02Z")),
      entries = List(
        Entry(
          language = None,
          authors = List.empty,
          categories = List.empty,
          content = None,
          contributors = List.empty,
          id = Id("urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a"),
          links = List(
            Link(
              href = URI.create("http://example.org/2003/12/13/atom03"),
              rel = None,
              `type` = None,
              hreflang = None,
              title = None,
              length = None,
            ),
          ),
          published = None,
          rights = None,
          summary = Some(Text.Raw("Some text.")),
          title = Text.Raw("Atom-Powered Robots Run Amok"),
          updated = Date(Instant.parse("2003-12-13T18:30:02Z")),
        ),
      ),
    )

    val expectedXml =
      <feed xmlns="http://www.w3.org/2005/Atom">
        <title type="text">Example Feed</title>
        <id>urn:uuid:60a76c80-d399-11d9-b93C-0003939e0af6</id>
        <updated>2003-12-13T18:30:02Z</updated>
        <link href="http://example.org/"/>
        <category
          term="Test post"
          scheme="http://example.org/"
          label="Some label"
        />
        <author>
          <name type="text">John Doe</name>
        </author>
        <entry>
          <title type="text">Atom-Powered Robots Run Amok</title>
          <id>urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a</id>
          <link href="http://example.org/2003/12/13/atom03"/>
          <updated>2003-12-13T18:30:02Z</updated>
          <summary type="text">Some text.</summary>
        </entry>
      </feed>

    val obtainedXml = write(feed)

    assertXmlEquals(obtainedXml, expectedXml)
  }

}

object WriteTest {

  private val prettyPrinter: PrettyPrinter = new PrettyPrinter(width = Int.MaxValue, step = 2)

  private def assertXmlEquals(obtained: Node, expected: Node): Unit = {
    Assertions.assertEquals(prettyPrinter.format(obtained), prettyPrinter.format(expected))
  }

}