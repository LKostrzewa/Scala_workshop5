package interpolations

object CustomObj {
  implicit class Custom(val sc: StringContext) extends AnyVal {
    def digits(args: Any*): String = {
      val stringContextIterator = sc.parts.iterator
      val argsIterator = args.iterator
      val sb = new StringBuilder(stringContextIterator.next())
      while(argsIterator.hasNext) {
        sb.append(argsIterator.next().toString)
        sb.append(stringContextIterator.next())
      }
      sb.toString().replaceAll("[0-9]", "CYFRA")
    }
  }

  def replaceDigits(a: Int, b: Int): String = {
    digits"Podano $a oraz $b"
  }
}
