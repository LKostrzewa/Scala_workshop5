package options.task

class Club() {
  private val scores = Map("Barcelona" -> 23, "Juventus" -> 21, "Bayern" -> 25)
  def getTopGoals(team: String): Option[Int] = scores.get(team)
}
