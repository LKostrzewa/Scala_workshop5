package exceptions

import scala.util.Try

object CalculatorExceptions {
  class LargeNumbersException(private val message: String = "") extends RuntimeException(message)
  class NegativeNumberException(private val message: String = "") extends RuntimeException(message)
}

object Calculator {

  import CalculatorExceptions._

  def sum(a: Int, b: Int): Int = {
    if (a < 0 || b < 0) throw new NegativeNumberException("Negative numbers not allowed")
    val result = a + b
    if (result > 50) throw new LargeNumbersException("To large numbers given")
    result
  }

  @throws(classOf[NegativeNumberException])
  def mul(a: Int, b: Int): Int = {
    if (a < 0 || b < 0) throw new NegativeNumberException("Negative numbers not allowed")
    a * b
  }

  def tryCatch(a: Int, b: Int): Int = {
    try {
      sum(a,b)
    } catch {
      case _: NegativeNumberException => -1
      case _: LargeNumbersException => -2
    } finally {
      println("Calculation done!")
    }
  }

  def trySuccessFailure(a: Int, b: Int): Try[Int] = Try {
    sum(a,b)
  }
}
