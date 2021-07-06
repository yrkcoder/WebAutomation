import sbt.Keys.libraryDependencies

ThisBuild / scalaVersion     := "2.12.12"
ThisBuild / version          := "0.1"
ThisBuild / organization     := "com.test"
ThisBuild / organizationName := "automation"

lazy val root = (project in file("."))
  .settings(
    name := "WebAutomation",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-library" % "2.12.12",
      "org.typelevel" %% "discipline-scalatest" % "2.1.5" % Test,
      "org.seleniumhq.selenium" % "selenium-java" % "3.141.59"
    ),
    resolvers += "org.seleniumhq.selenium" at "https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
publishArtifact in (Test, packageBin) := true