import play.Project._

name := "Registry"

version := "1.0"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.18"
)

playJavaSettings
