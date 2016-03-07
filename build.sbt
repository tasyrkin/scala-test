name := "scala-test"

version := "1.0"

scalaVersion := "2.11.7"

//testing
libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

//logging
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.18"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.18"
