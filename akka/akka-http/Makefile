.PHONY: build clean run

component_name := akka-http
component_jar := akka-http-1.0-SNAPSHOT.jar
plugin_name := akka

build_command_module = -am -pl akka/akka-http

include ../../Makefile

run_command = -cp target/dependency/*:target/${component_jar} akka.App

