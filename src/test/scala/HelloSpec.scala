import com.example.Hello
import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {

  import Hello._

  "Hello" should "have tests" in {
    true should be(true)
  }

  it should "add numbers" in {
    1 + 1 should be(2)
  }

  "empty space" should "stays empty" in {
    val emptySpace = (x: Int, y: Int) => false
    val resultSpace = evolve(emptySpace)
    resultSpace(0, 0) should be(false)
  }

  "1 cell in 1-cell space" should "die" in {
    val oneCellSpaceX1Y1 = (x: Int, y: Int) => x == 1 && y == 1
    val resultSpace = evolve(oneCellSpaceX1Y1)
    resultSpace(0, 0) should be(false)
  }

  "alive cell with 2 neighbours" should "stay alive" in {
    val initial = (x: Int, y: Int) => (x, y) match {
      case (0, 1) => true
      case (1, 1) => true
      case (1, 2) => true
      case _ => false
    }
    val result = evolve(initial)
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
    val result = evolve(initial)
    result(1, 1) should be(true)
  }

  "alive cell surrounded by crowd" should "die" in {
    val initial = (x: Int, y: Int) => (x, y) match {
      case _ => true
    }
    val result = evolve(initial)
    result(1, 1) should be(false)
  }

  "dead cell surrounded by 3 neighbours" should "become alive" in {
    val initial = (x: Int, y: Int) => (x, y) match {
      case (0, 1) => true
      case (1, 2) => true
      case (2, 2) => true
      case _ => false
    }
    val result = evolve(initial)
    result(1, 1) should be(true)
  }

  "dead cell with 2 neighbours" should "stay dead" in {
    val initial = (x: Int, y: Int) => (x, y) match {
      case (0, 1) => true
      case (1, 2) => true
      case _ => false
    }
    val result = evolve(initial)
    result(1, 1) should be(false)
  }

  "`block`" should "stay `block`" in {
    val gen0 =
      """....
        |.xx.
        |.xx.
        |....
      """.stripMargin.gen()
    val gen1 = evolve(gen0)
    gen1.mat(0, 0, 3, 3) should be(gen0.mat(0, 0, 3, 3))
  }

  "`beehive`" should "stay `beehive`" in {
    val gen0 =
      """......
        |..xx..
        |.x..x.
        |..xx..
        |......
      """.stripMargin.gen()
    val gen1 = evolve(gen0)
    gen1.mat(0, 0, 5, 4) should be(gen0.mat(0, 0, 5, 4))
  }

  "vertical `blinker`" should "becomes horizontal `blinker`" in {
    val gen0 =
      """.....
        |..x..
        |..x..
        |..x..
        |.....
      """.stripMargin.gen()
    val gen1 = evolve(gen0)
    gen1.mat(0, 0, 4, 4) should be(
      """.....
        |.....
        |.xxx.
        |.....
        |.....
      """.stripMargin.gen().mat(0, 0, 4, 4))
  }

}
