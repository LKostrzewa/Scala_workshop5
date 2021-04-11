package options

object Options {

  def useNullAsParam(thing: Null): Unit = {
    println("Scala")
  }

  def useNullAsReturn(): Null = {
    null
  }

  def matchOption(opt: Option[Int]): Int = {
    opt match {
      case Some(n) =>
        n
      case None =>
        0
    }
  }

}
