package interpolations

import org.scalatest.FunSuite

class InterpolationsTest extends FunSuite {

  test("basic test") {
    val result = Interpolations.doSInterpolation(22, "Lukasz")

    assert(result.equals("Jestem Lukasz i mam 22 lat(a)"))
  }

  test("advanced test") {
    val result = Interpolations.doAdvanceSInterpolation(2, 2)

    assert(result.equals("2 + 2 = 4"))
  }

  test("f test") {
    val result = Interpolations.doFInterpolation("Lukasz", 1.79)
    assert(result.equals("Jestem Lukasz.\nMam 1,79 metrów wzrostu"))
  }

  test("dollar sign test") {
    val result = Interpolations.doFInterpolationWith$Sign(15.40)

    assert(result.equals("Na koncie masz: $15,40"))
  }

  test("compare interpolation test") {
    val fResult = Interpolations.doFInterpolation("Lukasz", 1.79)
    val rawResult = Interpolations.doRawInterpolation("Lukasz", 1.79)
    val sResult = Interpolations.doSInterpolations("Lukasz", 1.79)

    println(fResult)
    println(rawResult)
    println(sResult)

    assert(!fResult.equals(rawResult))
    assert(!fResult.equals(sResult))
    assert(!sResult.equals(rawResult))
  }

  test("run custom interpolation") {
    val replaced = CustomObj.replaceDigits(12, 34)

    println(replaced)

    assert("[0-9]".r.findFirstMatchIn(replaced).isEmpty)
  }

}
