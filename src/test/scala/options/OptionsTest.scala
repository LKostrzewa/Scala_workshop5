package options

import org.scalatest.FunSuite

class OptionsTest extends FunSuite {

  val o1: Option[Int] = None
  val o2: Option[Int] = Some(1)

  test("null test") {
    val nullRef: String = null

    assertThrows[NullPointerException] {
      nullRef.charAt(0)
    }

    val typeExample: Null = null

    Options.useNullAsParam(typeExample)

    // Won't compile
    // assert(typeExample.isInstanceOf[Null])
    assert(typeExample == Options.useNullAsReturn())
  }

  test("basic behaviour") {
    assert(o2.isDefined)
    assert(o1.isEmpty)
    assert(o2.nonEmpty)
    assert(o2.contains(1))

    val o3 = Option(2)

    assert(o3.contains(2))

    val o4 = Option(null)

    assert(o4.isEmpty)
  }

  test("none test") {
    assert(None.toList.equals(List()))
    assert(None.toString.equals("None"))
  }

  test("match test") {
    assert(Options.matchOption(None) == 0)
    assert(Options.matchOption(Some(3)) == 3)
  }

  test("options in map") {
    val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

    assert(capitals.get("France").contains("Paris"))
    assert(capitals.get("Poland").isEmpty)
  }

  test("get test") {
    assert(o2.get == 1)

    assertThrows[NoSuchElementException] {
      o1.get
    }

    assert(o1.getOrElse(0) == 0)
  }


}
