name := "scala-test"

version := "1.0"

scalaVersion := "2.11.7"

//testing
libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

//logging
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
//slf4j
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.18"
//bind log4j with slf4j
libraryDependencies += "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.5"
//log4j2 implementation
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.5"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.5"


