package eithers

object Eithers {
  def divideXByY(x: Int, y: Int): Either[String, Int] = {
    if (y == 0) Left("Dude, can't divide by 0")
    else Right(x / y)
  }

  def matchDivide(x: Int, y: Int): Int = {
    divideXByY(x, y) match {
      case Right(i) => i*i
      case Left(_) => 0
    }
  }

  def yieldEither(e1: Either[Int, Int], e2: Either[Int, Int], e3: Either[Int, Int]): Either[Int, Int] = {
    for {
      x <- e1
      y <- e2
      z <- e3
    } yield x + y + z
  }
}
