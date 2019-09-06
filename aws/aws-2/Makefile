.PHONY: build clean run

component_name := aws-2
component_jar := aws-2-1.0-SNAPSHOT.jar
plugin_name := aws-sdk

include ../../Makefile

run_command = -cp target/dependency/*:target/${component_jar} aws.App

specialagent_jar_path := ${CURDIR}/../../$(specialagent_jar)

build_command_module = -am -pl aws/aws-2

build_command_start = cd ../.. &&
