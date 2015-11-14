import com.example.Hello
import com.example.Hello.Cell
import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  "Game of Life" should "have tests" in {
    true should be(true)
  }
  it should "add numbers" in {
    1 + 1 should be(2)
  }
  it should "no living cell if set is empty" in {
    Hello.evolve(Set.empty) should be(Set.empty)
  }

  it should "return empty set if only one living cell" in {
    Hello.evolve(Set {
      Cell(1, 1)
    }) should be(Set.empty)
  }
  it should "stay alive with 2 active neighbours" in {
    val nextGen = Hello.evolve(Set(Cell(1, 1), Cell(0, 1), Cell(1, 2), Cell(2, 2)))
    nextGen.contains(Cell(1, 1)) should be(true)
  }

  it should "born with exact 3 active neighbours" in {
    val nextGen = Hello.evolve(Set(Cell(0, 1), Cell(1, 2), Cell(2, 2)))
    nextGen.contains(Cell(1, 1)) should be(true)
  }

  it should "die when cell has more than 3 neighbours" in {

    val nextGen = Hello.evolve(Set(
      Cell(0, 0),
      Cell(1, 0),
      Cell(2, 0),
      Cell(0, 1),
      Cell(1, 1),
      Cell(2, 1),
      Cell(0, 2),
      Cell(1, 2),
      Cell(2, 2)
    ))
      nextGen.contains(Cell(1, 1)) should be(false)
  }

}
