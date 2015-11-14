import com.example.Hello
import com.example.Hello.Cell
import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  "Cell" should "have tests" in {
    true should be(true)
  }
  it should "add numbers" in {
    1 + 1 should be(2)
  }

  it should "have no cells in an empty generation" in {
    Hello.generation = Set.empty
    Hello.evolve()
    Hello.generation.size should be(0)
  }

  it should "kill a cell when under populated" in {
    Hello.generation = Set(Cell(1, 1), Cell(1, 2))
    Hello.evolve()
    Hello.generation.contains(Cell(1, 1)) should be(false)
  }

  it should "survive when has two neighbours" in {
    Hello.generation = Set(Cell(1, 1), Cell(1, 2), Cell(2, 1))
    Hello.evolve()
    Hello.generation.contains(Cell(1, 1)) should be(true)
  }

  it should "die when neighbours overcrowded" in {
    Hello.generation = Set(Cell(1, 1), Cell(1, 2), Cell(2, 1), Cell(2, 2), Cell(1, 0))
    Hello.evolve()
    Hello.generation.contains(Cell(1, 1)) should be(false)
  }

  it should "be born if has exact 3 neighbours" in {
    Hello.generation = Set(Cell(1, 2), Cell(2, 3), Cell(3, 3))
    Hello.evolve()
    Hello.generation.contains(Cell(2, 2)) should be(true)
  }

  "Space" should "be empty for no cells" in {
    Hello.generation = Set.empty
    Hello.print()
    Hello.space should be ("")
  }

  it should "print one cell" in {
    Hello.generation = Set(Cell(1,4))
    Hello.print()
    Hello.space should be ("x")
  }
}
