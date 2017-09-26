
val versions = new {
  val akka       = "2.4.16"
  val akkaHttp   = "10.0.1"
  val scalaTest  = "3.0.1"
  val mockito    = "1.10.19"
  val circe      = "0.7.0"
  val casbah     = "3.1.1"
}


lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies ++= Seq(
      "com.google.cloud" % "google-cloud-language" % "0.24.0-beta",
      "org.scalatest"     %% "scalatest"            % versions.scalaTest % "test",
      "com.typesafe.akka" %% "akka-actor"           % versions.akka,
      "com.typesafe.akka" %% "akka-testkit"         % versions.akka,
      "com.typesafe.akka" %% "akka-http"            % versions.akkaHttp,
      "com.typesafe.akka" %% "akka-http-spray-json" % versions.akkaHttp,
      "com.typesafe.akka" %% "akka-http-testkit"    % versions.akkaHttp
    ),
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % versions.circe)
  )
