package interpolations

object Interpolations {
  def doSInterpolation(age: Int, name: String): String = {
    s"Jestem $name i mam $age lat(a)"
  }

  def doAdvanceSInterpolation(a: Int, b: Int): String = {
    s"$a + $b = ${a + b}"
  }

  def doFInterpolation(name: String, height: Double): String = {
    f"Jestem $name%s.\nMam $height%1.2f metrów wzrostu"
  }

  def doFInterpolationWith$Sign(amount: Double): String = {
    f"Na koncie masz: $$$amount%.2f"
  }

  def doRawInterpolation(name: String, height: Double): String = {
    raw"Jestem $name%s.\nMam $height%1.2f metrów wzrostu"
  }

  def doSInterpolations(name: String, height: Double): String = {
    s"Jestem $name%s.\nMam $height%1.2f metrów wzrostu"
  }
}
