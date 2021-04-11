package eithers

import org.scalatest.FunSuite

class EithersTest extends FunSuite {

  private val right: Either[String, Int] = Eithers.divideXByY(4,2)
  private val left: Either[String, Int] = Eithers.divideXByY(4,0)


  test("basic either test") {
    assert(right.isRight)
    assert(right == Right(2))

    assert(left.isLeft)
    assert(left == Left("Dude, can't divide by 0"))
  }

  test("other methods") {
    assert(right.swap.isLeft)
    assert(right.swap == Left(2))

    assert(left.swap.isRight)
    assert(left.swap == Right("Dude, can't divide by 0"))

    assert(right.contains(2))
    assert(!left.contains("Dude, can't divide by 0"))
  }

  test("match test") {
    assert(Eithers.matchDivide(4,2) == 4)
    assert(Eithers.matchDivide(4,0) == 0)
  }

  test("conversion test") {
    assert(right.toOption.isDefined)
    assert(right.toOption.contains(2))
    assert(left.toOption.isEmpty)

    assert(right.toSeq.length == 1)
    assert(left.toSeq.isEmpty)
  }

  test("yield test") {
    assert(Eithers.yieldEither(Right(2), Right(3), Right(1)) == Right(6))
    assert(Eithers.yieldEither(Right(2), Left(15), Right(1)) == Left(15))
    assert(Eithers.yieldEither(Left(2), Left(3), Left(1)) == Left(2))
  }
}
