ThisBuild / sonatypeProfile := "au.id.tmm"
ThisBuild / baseProjectName := "scala-web-feed-models"
ThisBuild / githubProjectName := "scala-web-feed-models"

lazy val root = project
  .in(file("."))
  .settings(settingsForRootProject)
  .settings(console := (atom / Compile / console).value)
  .aggregate(
    atom,
//    rss,
  )

lazy val atom = project
  .in(file("atom"))
  .settings(settingsForSubprojectCalled("atom"))
  .settings(
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
  )

//lazy val rss = project
//  .in(file("rss"))
//  .settings(settingsForSubprojectCalled("rss"))
