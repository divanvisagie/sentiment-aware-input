package example

import com.google.cloud.language.v1.Document.Type
import com.google.cloud.language.v1.{Document, LanguageServiceClient, Sentiment}

class SentimentAnalyzer {
  def analyze(text: String): Sentiment = {
    val client = LanguageServiceClient.create()
    val doc = Document.newBuilder()
      .setContent(text)
      .setType(Type.PLAIN_TEXT)
      .build()
    client.analyzeSentiment(doc).getDocumentSentiment
  }
}

object SentimentAnalyzer {
  def apply(): SentimentAnalyzer = new SentimentAnalyzer()
}
