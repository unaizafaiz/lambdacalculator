
name := "lambdacalculator"

version := "1.0"

scalaVersion := "2.12.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

libraryDependencies ++= Seq(
   "com.amazonaws" % "aws-lambda-java-core" % "1.0.0",
   "com.amazonaws" % "aws-lambda-java-events" % "1.0.0",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.8",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.8",
  "junit" % "junit" % "4.12" % Test
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}