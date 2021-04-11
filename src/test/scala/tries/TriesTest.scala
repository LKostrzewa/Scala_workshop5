package tries

import org.scalatest.FunSuite

import scala.util.{Failure, Success, Try}

class TriesTest extends FunSuite {

  private val success: Try[Int] = Tries.toInt("13")
  private val failure: Try[Int] = Tries.toInt("abc")

  test("basic try test") {
    assert(success.isSuccess)
    assert(success == Success(13))
    assert(failure.isFailure)
  }

  test("other methods") {
    assert(success.failed.isFailure)
    assert(failure.failed.isSuccess)

    val fold: String = success.fold(
      ex => "Operation failed with " + ex,
       v => "Operation produced value: " + v
    )

    val transform: Try[Int] = success.transform(
      v => Success(v*2),
      ex => Failure(ex.getCause)
    )

    assert(fold.endsWith("13"))
    assert(transform.get == 26)
  }

  test("to option test") {
    assert(success.toOption.isDefined)
    assert(success.toOption.contains(13))
    assert(failure.toOption.isEmpty)
  }

  test("match test") {
    assert(Tries.matchToInt("15").equals("15"))
    assert(Tries.matchToInt("abc").equals("Failed. Reason: java.lang.NumberFormatException: For input string: \"abc\""))
  }

  test("yield test") {
    val result = Tries.yieldToInt("4", "5", "9")
    assert(result.isSuccess)
    assert(result.get == 18)

    assert(Tries.yieldToInt("4", "5", "abc").isFailure)
  }

  test("to either test") {
    assert(success.toEither.isRight)
    assert(success.toEither.contains(13))
    assert(failure.toEither.isLeft)
  }

}
