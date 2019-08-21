.PHONY: build clean run

component_name := rabbitmq
component_jar := rabbitmq-1.0-SNAPSHOT.jar
plugin_name := rabbitmq-client

include ../Makefile

run_command = -cp target/dependency/*:target/${component_jar} rabbitmq.App
