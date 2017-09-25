import sbt._


object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.3"
  lazy val googleApi = "com.google.cloud" % "google-cloud-language" % "0.24.0-beta"
  lazy val akka = "com.typesafe.akka" %% "akka-actor" % "2.4.16"
  lazy val akkaHttp = "com.typesafe.akka" %% "akka-http-core" % "10.0.1"
  lazy val akkaHttpSprayJson =  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.1"
  lazy val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % "10.0.1"
}
