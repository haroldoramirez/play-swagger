import play.PlayJava

name := """play-swagger"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  //"org.webjars" % "webjars-play_2.10" % "2.4.0-M2",
  //"org.webjars" % "jquery" % "2.1.3",
  //"org.webjars" % "bootstrap" % "3.3.2",
  //"mysql" % "mysql-connector-java" % "5.1.34",
  "com.wordnik" %% "swagger-play2" % "1.3.12"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)
