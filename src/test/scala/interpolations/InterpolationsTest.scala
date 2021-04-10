package interpolations

import org.scalatest.FunSuite

class InterpolationsTest extends FunSuite {

  test("basic test") {
    val result = Interpolations.doSInterpolation(22, "Lukasz")

    assert(result.equals("Jestem Lukasz i mam 22 lat(a)"))
  }
}
