.PHONY: build clean run

component_name := rabbitmq-5.7.3
component_jar := rabbitmq-5.7.3-1.0-SNAPSHOT.jar
plugin_name := rabbitmq-client

build_command_module = -am -pl rabbitmq/rabbitmq-5.7.3

include ../../Makefile

run_command = -cp target/dependency/*:target/${component_jar} rabbitmq.App
