package example


import java.util.Properties

import com.google.cloud.language.v1.Document.Type
import com.google.cloud.language.v1.{Document, LanguageServiceClient, Sentiment}
import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations
import edu.stanford.nlp.util.CoreMap

import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations
import scala.collection.mutable

case class SentimentCounter(positive: Int, negative: Int, neutral: Int)


class SentimentAnalyzer {


  def analyzeWithGoogle(text: String): Double = {
    val client = LanguageServiceClient.create()
    val doc = Document.newBuilder()
      .setContent(text)
      .setType(Type.PLAIN_TEXT)
      .build()
    client.analyzeSentiment(doc).getDocumentSentiment.getScore
  }

  def analyzeWithCoreNLP(text: String): Double = {
    val props = new Properties()
    props.setProperty("annotators","tokenize, ssplit, pos, lemma, parse, sentiment")
    val pipeline = new StanfordCoreNLP(props)

    val annotation = pipeline.process(text)
    val sentences = annotation.get(classOf[CoreAnnotations.SentencesAnnotation])

    val sentimentStringValuesBuf = scala.collection.mutable.ArrayBuffer.empty[String]
    sentences.forEach { sentence =>
      val sentiment = sentence.get(classOf[SentimentCoreAnnotations.SentimentClass])

      val tree = sentence.get(classOf[SentimentCoreAnnotations.SentimentAnnotatedTree])
      val sentimentValue = RNNCoreAnnotations.getPredictedClass(tree)


      println(s"$sentiment for ${sentence.toString} = ${sentimentValue}")
      sentimentStringValuesBuf += sentiment
    }

    val index = sentimentStringValuesBuf.toList.foldLeft(List(0,0,0))((acc, item) => {
      item match {
        case "Positive" => List(acc.head + 1, acc(1), acc(2))
        case "Very positive" => List(acc.head + 2, acc(1), acc(2))
        case "Negative" => List(acc.head, acc(1) + 1, acc(2))
        case _ => List(acc.head, acc(1), acc(2) + 1)
      }
    }).zipWithIndex.maxBy(_._1)._2

    println(s"mostly $index")

    index match {
      case 0 => 1.0
      case 1 => -1.0
      case 2 => 0.0
    }

  }

}

object SentimentAnalyzer {
  def apply(): SentimentAnalyzer = new SentimentAnalyzer()
}
