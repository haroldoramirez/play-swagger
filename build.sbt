import play.PlayJava

name := """play-swagger"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.reflections" % "reflections" % "0.10-SNAPSHOT",
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.34",
  "com.wordnik" %% "swagger-play2" % "1.3.11"
)

resolvers += Resolver.mavenLocal

lazy val root = (project in file(".")).enablePlugins(PlayJava)
