import async.{Student, StudentsRepository}
import org.scalatest.FunSuite

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, Promise}

class Promises extends FunSuite {

  test("Should complete promise with success") {
    val studentPromise: Promise[Student] = Promise[Student]

    val studentFuture: Future[Student] = StudentsRepository.getStudent(0)
    studentFuture map { student => {
      studentPromise success student
    }}

    Await.ready(studentFuture, Duration.Inf)

    assert(studentPromise.isCompleted)
    assert(studentPromise.future.value.get.get.id === 0)
  }

  test("Should throw exception when completing promise twice") {
    val studentPromise: Promise[Student] = Promise[Student]
    studentPromise success new Student(10, "Jacek Kowalski", 2)

    assertThrows[IllegalStateException]({
      studentPromise success new Student(11, "Andrzej Nowak", 4)
    })
  }

  test("Should not throw exception when trying to complete promise twice") {
    val studentPromise: Promise[Student] = Promise[Student]

    studentPromise trySuccess new Student(10, "Jacek Kowalski", 2)
    studentPromise trySuccess new Student(11, "Andrzej Nowak", 4)

    assert(studentPromise.future.value.get.get.id === 10)
  }

  test("Should fail promise with an exception") {
    val studentPromise: Promise[Student] = Promise[Student]

    studentPromise failure new RuntimeException

    assert(studentPromise.future.value.get.isFailure)
  }

  test("Should not allow failing promise twice") {
    val studentPromise: Promise[Student] = Promise[Student]

    studentPromise failure new RuntimeException

    assertThrows[IllegalStateException]({
      studentPromise failure new RuntimeException
    })
  }

  test("Should not throw exception when trying to fail promise twice") {
    val studentPromise: Promise[Student] = Promise[Student]

    studentPromise tryFailure new RuntimeException
    studentPromise tryFailure new NoSuchElementException

    assert(studentPromise.future.value.get.isFailure)
    assert(studentPromise.future.value.get.failed.get.isInstanceOf[(RuntimeException)])
  }

  def getStudent(id: Int): Future[Student] = Future {
    if (id === 10) {
      new Student(10, "Jacek Kowalski", 2)
    } else {
      throw new RuntimeException
    }
  }

  test("Should complete promise with completeWith (success)") {
    val studentPromise: Promise[Student] = Promise[Student]
    val studentFuture: Future[Student] = getStudent(10)

    studentPromise completeWith studentFuture

    Await.ready(studentFuture, Duration.Inf)

    assert(studentPromise.future.value.get.isSuccess)
  }

  test("Should complete promise with completeWith (failure)") {
    val studentPromise: Promise[Student] = Promise[Student]
    val studentFuture: Future[Student] = getStudent(11)

    studentPromise completeWith studentFuture

    Await.ready(studentFuture, Duration.Inf)

    assert(studentPromise.future.value.get.isFailure)
  }

  test("Should test synchronized") {
    StudentsRepository.synchronizedGetStudent(1)
    StudentsRepository.synchronizedGetStudent(2)
    StudentsRepository.synchronizedGetStudent(3)
  }
}