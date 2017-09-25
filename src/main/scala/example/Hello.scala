package example

import com.google.cloud.language.v1.{Document, LanguageServiceClient, Sentiment}
import com.google.cloud.language.v1.Document.Type




object Hello extends Greeting with App {

  val textToAnalyze = "Hello World"

  val result = new SentimentAnalyzer().analyze(textToAnalyze)
  println(s"Text $textToAnalyze")
  println(s"Sentiment: ${result.getScore}")

  println(greeting)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
