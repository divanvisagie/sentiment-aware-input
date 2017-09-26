package example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.io.StdIn

object Hello extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()


  val textToAnalyze = "Hello World"

  val result = SentimentAnalyzer().analyze(textToAnalyze)
  println(s"Text $textToAnalyze")
  println(s"Sentiment: ${result.getScore}")



  //set up the server
  val staticResources =
    pathPrefix("") {
      pathEndOrSingleSlash {
        getFromDirectory("public/index.html")
      } ~
      getFromDirectory("public")
    }

  val route: Route =
    staticResources ~
    path("ping") {
      get {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "pong"))
      }
    }

  val port = 5000
  val ip = "localhost"
  println(s"serving at http://$ip:$port")
  val bindingFuture = Http().bindAndHandle(route, ip, port)


}

