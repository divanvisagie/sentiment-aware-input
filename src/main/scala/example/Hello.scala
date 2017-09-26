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


  //set up the server
  val staticResources =
    pathPrefix("") {
      pathEndOrSingleSlash {
        getFromDirectory("public/index.html")
      } ~
      getFromDirectory("public")
    }

  val sentimentAnalyzer = SentimentAnalyzer()

  val route: Route =
    staticResources ~
    path("ping") {
      get {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "pong"))
      }
    } ~
    path("google") {
      post {
        entity(as[String]) { text =>
          val answer = sentimentAnalyzer.analyzeWithGoogle(text).toString
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, answer))
        }
      }
    } ~
    path("stanford") {
      post {
        entity(as[String]) { text =>
          val answer = sentimentAnalyzer.analyzeWithCoreNLP(text).toString
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, answer))
        }
      }
    }

  val port = 5000
  val ip = "localhost"
  println(s"serving at http://$ip:$port")
  val bindingFuture = Http().bindAndHandle(route, ip, port)


}

