import com.example.Hello
import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  "Hello" should "have tests" in {
    true should be(true)
  }

  it should "add numbers" in {
    1 + 1 should be(2)
  }

  "empty space" should "stays empty" in {
    val emptySpace = (x: Int, y: Int) => false
    val resultSpace = Hello.evolve(emptySpace)
    resultSpace(0, 0) should be(false)
  }

  "1 cell in 1-cell space" should "die" in {
    val oneCellSpaceX1Y1 = (x: Int, y: Int) => x == 1 && y == 1
    val resultSpace = Hello.evolve(oneCellSpaceX1Y1)
    resultSpace(0, 0) should be(false)
  }

  "alive cell with 2 neighbours" should "stay alive" in {
    val initial = (x: Int, y: Int) => (x, y) match {
      case (0, 1) => true
      case (1, 1) => true
      case (1, 2) => true
      case _ => false
    }
    val result = Hello.evolve(initial)
    result(1, 1) should be(true)
  }

  "alive cell with 3 neighbours" should "stay alive" in {
    val initial = (x: Int, y: Int) => (x, y) match {
      case (0, 1) => true
      case (1, 1) => true
      case (1, 0) => true
      case (1, 2) => true
      case _ => false
    }
    val result = Hello.evolve(initial)
    result(1, 1) should be(true)
  }

  "alive cell surrounded by crowd" should "die" in {
    val initial = (x: Int, y: Int) => (x, y) match {
      case _ => true
    }
    val result = Hello.evolve(initial)
    result(1, 1) should be(false)
  }

  "dead cell surrounded by 3 neighbours" should "become alive" in {
    val initial = (x: Int, y: Int) => (x, y) match {
      case (0, 1) => true
      case (1, 2) => true
      case (2, 2) => true
      case _ => false
    }
    val result = Hello.evolve(initial)
    result(1, 1) should be(true)
  }
}
