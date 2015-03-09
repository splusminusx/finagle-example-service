name := "finagle-example-service"

version := "1.0"

scalaVersion := "2.11.6"

organization := "livetex"

com.twitter.scrooge.ScroogeSBT.newSettings

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-http" % "6.24.0",
  "org.apache.thrift" % "libthrift" % "0.8.0",
  "com.twitter" %% "scrooge-core" % "3.17.0",
  "com.twitter" %% "finagle-thrift" % "6.24.0"
)