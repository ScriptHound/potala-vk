val scala3Version = "3.1.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "potala-vk",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "com.softwaremill.sttp.client3" %% "core" % "3.7.6",
    libraryDependencies += "org.json4s" %% "json4s-native" % "4.1.0-M1",
  )
