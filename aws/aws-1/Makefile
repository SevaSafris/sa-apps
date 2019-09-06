.PHONY: build clean run

component_name := aws-1
component_jar := aws-1-1.0-SNAPSHOT.jar
plugin_name := aws-sdk

build_command_module = -am -pl aws/aws-1

include ../../Makefile

run_command = -cp target/dependency/*:target/${component_jar} aws.App
