package interpolations

object Interpolations {
  def doSInterpolation(age: Int, name: String): String = {
    s"Jestem $name i mam $age lat(a)"
  }

  def doAdvanceSInterpolation(a: Int, b: Int): String = {
    s"$a + $b = ${a + b}"
  }

  def doSInterpolationWith$Sign(amount: Double): String = {
    s"Na koncie masz: $$amount"
  }
}
