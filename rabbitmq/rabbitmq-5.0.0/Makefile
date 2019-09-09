.PHONY: build clean run

component_name := rabbitmq-5.0.0
component_jar := rabbitmq-5.0.0-1.0-SNAPSHOT.jar
plugin_name := rabbitmq-client

build_command_module = -am -pl rabbitmq/rabbitmq-5.0.0

include ../../Makefile

run_command = -cp target/dependency/*:target/${component_jar} rabbitmq.App
