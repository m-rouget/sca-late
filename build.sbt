organization  := "com.example"

version       := "0.1"

scalaVersion  := "2.11.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test"
  )
}

libraryDependencies += "io.spray" %%  "spray-json" % "1.3.1"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.2.11"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"


// Initial declaration from spray template below, but IDEA14 complains about this:
//   Revolver.settings
// ... so replaced by this thanks to
// http://stackoverflow.com/questions/19578611/idea-complains-about-revolver-settings-when-trying-to-parse-spray-templates-bui
Revolver.settings: Seq[sbt.Def.Setting[_]]
