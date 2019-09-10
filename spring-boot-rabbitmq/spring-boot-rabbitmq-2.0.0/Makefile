.PHONY: build clean run

component_name := spring-boot-rabbitmq-2.0.0
component_jar := spring-boot-rabbitmq-2.0.0-1.0-SNAPSHOT.jar
plugin_name := spring-rabbitmq

build_command_module = -am -pl spring-boot-rabbitmq/spring-boot-rabbitmq-2.0.0

include ../../Makefile

run_command = -cp target/dependency/*:target/${component_jar} boot.App
