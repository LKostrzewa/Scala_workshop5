package options.task

class Service() {

  val p1: Player = new Player("Messi", Some("Barcelona"), 33)
  private val o1: Option[Player] = Some(p1)
  private val o2: Option[Player] = None
  private val o3: Option[Player] = Some(new Player("Ronaldo", Some("Juventus"), 36))

  // Wypisz zawodnika o1 na ekran
  def task0(): Unit = {
  }

  // Pobierz zawodnika o wieku 22 z o1 jezeli takiego nie ma to
  // zwróć nowego zawodnika o dowolnych wartosciach

  def task1(): Player = {
    null
  }

  // Jezeli zawodnik o imieniu Neymar nie znajduje sie w o1 zwroc wartosc o2 jezeli ono jest puste zwroc null

  def task2(): Player = {
    null
  }

  // Jezeli w o3 znajduje sie zawodnik posiadajacy klub zwróć informacje "Zatrudniony"
  // w przeciwnym wypadku zwróć "Bezrobotny"

  def task3(): String = {
    ""
  }

  // Pobierz wiek zawodnika z o1 pomnożony przez 2
  def task4(): Int = {
    0
  }

  // Ile bramek strzelil zawodnik z najwieksza iloscia goli z klubu zawodnika p1
  // zwroc rezultat jako option danego zawodnika i ilosci bramek
  def task5(): Option[Any] = {
    null
  }

}
