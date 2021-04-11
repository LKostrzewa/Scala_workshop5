package tries

import scala.util.{Failure, Success, Try}

object Tries {
  def toInt(s: String): Try[Int] = Try {
    Integer.parseInt(s.trim)
  }

  def matchToInt(string: String): String = {
    toInt(string) match {
      case Success(i) => i.toString
      case Failure(s) => s"Failed. Reason: $s"
    }
  }

  def yieldToInt(stringA: String, stringB: String, stringC: String): Try[Int] = {
    for {
      a <- toInt(stringA)
      b <- toInt(stringB)
      c <- toInt(stringC)
    } yield a + b + c
  }
}
