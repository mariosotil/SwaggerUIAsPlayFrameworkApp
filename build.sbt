import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}

val projectMaintainer = "Mario Sotil <mario.sotil@gmail.com>"
val projectName = "SwaggerUI"
val projectEntryPoint = projectName.toLowerCase

name := """%s""".format(projectName)

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  filters,

  "org.webjars" % "swagger-ui" % "2.1.4",
  "io.swagger" % "swagger-play2_2.11" % "1.5.1"
)

routesGenerator := InjectedRoutesGenerator

dockerCommands := Seq(
  Cmd("FROM", "java:openjdk-8-jre"),
  Cmd("MAINTAINER", "%s".format(projectMaintainer)),
  Cmd("EXPOSE", "9000"),
  Cmd("ADD", "stage /"),
  Cmd("WORKDIR", "/opt/docker"),
  Cmd("RUN", "[\"chown\", \"-R\", \"daemon\", \".\"]"),
  Cmd("RUN", "[\"chmod\", \"+x\", \"bin/%s\"]".format(projectEntryPoint)),
  Cmd("USER", "daemon"),
  Cmd("ENTRYPOINT", "[\"bin/%s\", \"-J-Xms128m\", \"-J-Xmx512m\", \"-J-server\"]".format(projectEntryPoint)),
  ExecCmd("CMD")
)

