ThisBuild / organization := "com.streese"
ThisBuild / scalaVersion := "2.13.5"
ThisBuild / version      := "0.1.0-SNAPSHOT"

lazy val proto = (project in file("."))
  .settings(
    name                  := "lab-scala-playground-proto",
    libraryDependencies  ++= Seq(libScalaPb, libScalaTest),
    Compile / PB.targets  := Seq(
      scalapb.gen(flatPackage=true) -> (Compile / sourceManaged).value / "scalapb"
    )
  )

lazy val libScalaPb   = "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"
lazy val libScalaTest = "org.scalatest"        %% "scalatest"       % "3.2.6"                                 % "test"

ThisBuild / scapegoatVersion := "1.4.8"
