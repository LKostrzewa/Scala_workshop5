package interpolations

import org.scalatest.funsuite.AnyFunSuite

class InterpolationsTest extends AnyFunSuite {

  test("basic test") {
    val result = Interpolations.doSInterpolation(22, "Lukasz")

    assert(result.equals("Jestem Lukasz i mam 22 lat(a)"))
  }
}
