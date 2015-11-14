import com.example.Hello
import com.example.Hello.Cell
import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  "Hello" should "have tests" in {
    true should be(true)
  }

  it should "add numbers" in {
    1 + 1 should be(2)
  }

  it should "return empty space for empty space" in {
    Hello.evolve(Set.empty) should be (Set.empty)
  }

  it should "return empty space for one cell" in {
    Hello.evolve(Set { Cell(1,2) }) should be (Set.empty)
  }

  "neigbours" should "return 8 cells" in {
    Hello.neighbours(Cell(1,1)).size should be (8)
  }

  "neighbours living cell" should "return cell empty" in {
    Hello.getLivingNeighbours(Set.empty,Cell(1,1) ).size should be (0)
  }


  "neighbours living cell" should "return 8 cells neig" in {
    Hello.getLivingNeighbours(Hello.neighbours(Cell(1,1)),Cell(1,1) ).size should be (8)
  }

  it should "return empty for 1 living cell space" in {
    Hello.getLivingNeighbours(Set(Cell(1,1)), Cell(1,1) ).size should be (0)
  }
}
