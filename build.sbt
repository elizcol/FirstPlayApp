name := "MyApp"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.json4s" 	%% "json4s-native" % "3.2.4",
  "org.mongodb" %% "casbah" % "2.7.4"
)     

play.Project.playScalaSettings
