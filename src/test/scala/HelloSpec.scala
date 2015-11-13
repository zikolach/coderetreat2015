import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  "Hello" should "have tests" in {
    true should be (true)
  }
  it should "add numbers" in {
    1 + 1 should be (2)
  }
}
