import async.{Student, StudentsRepository}
import org.scalatest.FunSuite

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

class Futures extends FunSuite {

  test("Should read student") {
    val student: Future[Student] = StudentsRepository.getStudent(0)
    val studentValue: Student = Await.result(student, Duration.Inf)

    assert(student.value.get.isSuccess)
    assert(studentValue.id === 0)
    assert(studentValue.name === "Jan Kowalski")
    assert(studentValue.semester === 6)
  }

  test("Should not read non-existing student") {
    val student: Future[Student] = StudentsRepository.getStudent(-1)
    Await.ready(student, Duration.Inf)

    assert(student.value.get.isFailure)
  }

  test("Should execute success case in onComplete callback") {
    val student: Future[Student] = StudentsRepository.getStudent(0)

    var isSuccess = false
    var isFailure = false

    student onComplete {
      case Success(_) => isSuccess = true
      case Failure(_) => isFailure = true
    }

    Await.ready(student, Duration.Inf)
    assert(isSuccess)
    assert(!isFailure)
  }

  test("Should execute failure case in onComplete callback") {
    val student: Future[Student] = StudentsRepository.getStudent(-1)

    var isSuccess = false
    var isFailure = false

    student onComplete {
      case Success(_) => isSuccess = true
      case Failure(_) => isFailure = true
    }

    Await.ready(student, Duration.Inf)
    assert(!isSuccess)
    assert(isFailure)
  }

  test("Should satisfy filter predicate") {
    val student: Future[Student] = StudentsRepository.getStudent(0)
    val filteredStudent = student filter { student => {
      student.semester < 10
    }}

    Await.ready(student, Duration.Inf)

    assert(filteredStudent.value.get.isSuccess)
  }

  test("Should not satisfy filter predicate") {
    val student: Future[Student] = StudentsRepository.getStudent(0)
    val filteredStudent = student filter { student => {
      student.semester > 10
    }}

    Await.ready(student, Duration.Inf)

    assert(filteredStudent.value.get.isFailure)
  }

  test("Should extract first name") {
    val student: Future[Student] = StudentsRepository.getStudent(0)
    val firstName: Future[String] = student map { student =>
      student.name.split(" ")(0)
    }

    val firstNameResult: String = Await.result(firstName, Duration.Inf)

    assert(firstNameResult === "Jan")
  }

  test("Should read three students") {
    val students: Future[List[Student]] = for {
      firstStudent: Student <- StudentsRepository.getStudent(0)
      secondStudent: Student <- StudentsRepository.getStudent(1)
      thirdStudent: Student <- StudentsRepository.getStudent(2)
    } yield List(firstStudent, secondStudent, thirdStudent)

    val studentsResult = Await.result(students, Duration.Inf)

    assert(students.value.get.isSuccess)
    assert(studentsResult.length === 3)
  }

  test("Should recover on error") {
    val student: Future[Object] = StudentsRepository.getStudent(-1) recover {
      case e: java.lang.IndexOutOfBoundsException => None
    }

    val studentResult = Await.result(student, Duration.Inf)

    assert(student.value.get.isSuccess)
    assert(studentResult === None)
  }

  test("Should recover with another student") {
    val student: Future[Student] = StudentsRepository.getStudent(-1) recoverWith {
      case e: java.lang.IndexOutOfBoundsException => StudentsRepository.getStudent(1)
    }

    val studentResult = Await.result(student, Duration.Inf)

    assert(student.value.get.isSuccess)
    assert(studentResult.id === 1)
  }
}