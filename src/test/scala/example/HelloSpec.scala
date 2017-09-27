package example

import org.scalatest._



class HelloSpec extends FlatSpec with Matchers {
  "The Hello object" should "say hello" in {
    true should be(true)
  }
}


