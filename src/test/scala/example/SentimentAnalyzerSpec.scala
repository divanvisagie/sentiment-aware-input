package example

import org.scalatest.{FlatSpec, Matchers}

class SentimentAnalyzerSpec extends FlatSpec with Matchers {
  "indexOfHighest when given List(1,2,3) it " should "return 2" in {
    val sentimentAnalyzer = SentimentAnalyzer()
    sentimentAnalyzer.indexOfHighest(List(1,2,3)) should be (2)
  }

  "indexOfHighest when given List(1,3,2) it" should  "return 1" in {
    val sentimentAnalyzer = SentimentAnalyzer()
    sentimentAnalyzer.indexOfHighest(List(1,3,2)) should be (1)
  }

  "indexOfHighest when given List(3,2,1) it" should "return 0" in {
    val sentimentAnalyzer = SentimentAnalyzer()
    sentimentAnalyzer.indexOfHighest(List(3,2,1)) should be (0)
  }

  "sentimentCounter(List(Positive,Neutral,Neutral))" should "return List(1,0,2)" in {
    val sentimentAnalyzer = SentimentAnalyzer()
    val countList = List("Positive","Neutral","Neutral")
    val count = sentimentAnalyzer.sentimentCounter(countList)
    println("--")
    count should be (List(1,0,2))
  }

  "sentimentCounter(List(Positive,Positive,Neutral))" should "return List(2,0,1)" in {
    val sentimentAnalyzer = SentimentAnalyzer()
    val countList = List("Positive","Positive","Neutral")
    val count = sentimentAnalyzer.sentimentCounter(countList)
    count should be (List(2,0,1))
  }
}
