.PHONY: build clean run

component_name := akka-streams
component_jar := akka-streams-1.0-SNAPSHOT.jar
plugin_name := akka

build_command_module = -am -pl akka/akka-streams

include ../../Makefile

run_command = -cp target/dependency/*:target/${component_jar} akka.App

