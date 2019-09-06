.PHONY: build clean run

component_name := elasticsearch-6.4.0
component_jar := elasticsearch-6.4.0-1.0-SNAPSHOT.jar
plugin_name := elasticsearch-rest-client

build_command_module = -am -pl elasticsearch/elasticsearch-6.4.0

include ../../Makefile

run_command = -cp target/dependency/*:target/${component_jar} es6.App
