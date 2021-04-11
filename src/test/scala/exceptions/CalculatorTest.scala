package exceptions

import exceptions.CalculatorExceptions._
import org.scalatest.FunSuite

import scala.util.{Failure, Success}

class CalculatorTest extends FunSuite  {

  test("sum test") {
    assertThrows[NegativeNumberException] {
      Calculator.sum(-2, 4)
    }

    assertThrows[LargeNumbersException] {
      Calculator.sum(60, 2)
    }

    assert(Calculator.sum(10,2) == 12)
  }

  test("mul test") {
    assertThrows[NegativeNumberException] {
      Calculator.mul(-2, 4)
    }

    assert(Calculator.mul(10,2) == 20)
  }

  test("try catch test") {
    assert(Calculator.tryCatch(-2, 4) == -1)
    assert(Calculator.tryCatch(100, 4) == -2)
    assert(Calculator.tryCatch(2, 4) == 6)
  }

  test("expression test") {
    val msg = try {
      Calculator.sum(-2, 4)
    }
    catch {
      case e: LargeNumbersException => e.getMessage
      case e: NegativeNumberException => e.getMessage
    }

    assert(msg.equals("Negative numbers not allowed"))
  }

  test("try test") {
    assert(Calculator.trySuccessFailure(10, 10).isSuccess)
    assert(Calculator.trySuccessFailure(-10, 10).isFailure)

    Calculator.trySuccessFailure(-10, 10) match {
      case Failure(e) => assert(e.isInstanceOf[NegativeNumberException])
      case Success(e) => e
    }
  }
}
