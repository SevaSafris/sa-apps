.PHONY: build clean run

component_name := elasticsearch-7.3.1
component_jar := elasticsearch-7.3.1-1.0-SNAPSHOT.jar
plugin_name := elasticsearch-rest-client

build_command_module = -am -pl elasticsearch/elasticsearch-7.3.1

include ../../Makefile

run_command = -cp target/dependency/*:target/${component_jar} es7.App
