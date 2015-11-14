import com.example.Hello
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
}
