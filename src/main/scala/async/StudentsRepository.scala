package async

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object StudentsRepository {

  private val students: List[Student] = List(
    new Student(0, "Jan Kowalski", 6),
    new Student(1, "Mateusz Nowak", 4),
    new Student(2, "Anna Mierzejewska", 3),
    new Student(3, "Julia Madejczyk", 5),
    new Student(4, "Michał Wójcik", 1)
  )

  def getAllStudents: Future[List[Student]] = Future {
    students
  }

  def getStudent(id: Int): Future[Student] = Future {
    students(id)
  }

  def synchronizedGetStudent(id: Int): Student = synchronized {
    println("Executing...")
    Thread.sleep(5000)
    students(id)
  }
}
