.PHONY: build clean run

component_name := spring-messaging-rabbit-2.1.8
component_jar := spring-messaging-rabbit-2.1.8-1.0-SNAPSHOT.jar
plugin_name := spring-messaging

build_command_module = -am -pl spring-messaging-rabbit/spring-messaging-rabbit-2.1.8

include ../../Makefile

run_command = -cp target/dependency/*:target/${component_jar} boot.App
