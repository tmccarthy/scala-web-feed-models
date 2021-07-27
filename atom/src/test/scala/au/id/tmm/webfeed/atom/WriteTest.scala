package au.id.tmm.webfeed.atom

import java.net.URI
import java.time.Instant

import au.id.tmm.webfeed.atom.common._
import au.id.tmm.webfeed.atom.primatives._
import munit.FunSuite

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
      categories = List.empty,
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
        <link href="http://example.org/"/>
        <updated>2003-12-13T18:30:02Z</updated>
        <author>
          <name>John Doe</name>
        </author>
        <id>urn:uuid:60a76c80-d399-11d9-b93C-0003939e0af6</id>
        <entry>
          <title type="text">Atom-Powered Robots Run Amok</title>
          <link href="http://example.org/2003/12/13/atom03"/>
          <id>urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a</id>
          <updated>2003-12-13T18:30:02Z</updated>
          <summary type="text">Some text.</summary>
        </entry>
      </feed>

    assertEquals(write(feed), expectedXml)
  }

}
