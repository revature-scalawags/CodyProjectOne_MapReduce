scalaVersion := "2.13.3"

name := "word-count"
organization := "com.revature"
version := "1.0"

libraryDependencies += "org.apache.hadoop" % "hadoop-mapreduce-client-core" % "3.2.1"
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "3.2.1"