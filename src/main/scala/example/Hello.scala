package example

import com.google.cloud.language.v1.{Document, LanguageServiceClient, Sentiment}
import com.google.cloud.language.v1.Document.Type

object Hello extends Greeting with App {

  def analyze(text: String): Sentiment = {
      val client = LanguageServiceClient.create()
      val doc = Document.newBuilder()
          .setContent(text)
          .setType(Type.PLAIN_TEXT)
          .build()
      val sentiment = client.analyzeSentiment(doc).getDocumentSentiment
      return sentiment
  }

  val textToAnalyze = "Hello World"

  val result = analyze(textToAnalyze)
  println(s"Text $textToAnalyze")
  println(s"Sentiment: ${result.getScore}")

  println(greeting)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
