.PHONY: build clean run

component_name := elasticsearch-6
component_jar := elasticsearch-6-1.0-SNAPSHOT.jar
plugin_name := elasticsearch-rest-client

include ../Makefile

run_command = -cp target/dependency/*:target/${component_jar} es6.App
