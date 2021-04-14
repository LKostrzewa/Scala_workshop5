package options.task

class Player(val name: String, val club: Option[String], val age: Int) {

  private def canEqual(a: Any): Boolean = a.isInstanceOf[Player]

  override def equals(that: Any): Boolean =
    that match {
      case that: Player =>
        that.canEqual(this) &&
          this.name == that.name &&
          this.age == that.age
          this.club == that.club
      case _ => false
    }

}
