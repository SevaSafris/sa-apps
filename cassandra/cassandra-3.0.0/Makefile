.PHONY: build clean run

component_name := cassandra-3.0.0
component_jar := cassandra-3.0.0-1.0-SNAPSHOT.jar
plugin_name := cassandra-driver

include ../../Makefile

specialagent_jar_path := ${CURDIR}/../../$(specialagent_jar)

build_command_module = -am -pl cassandra/cassandra-3.0.0

build_command_start = cd ../.. &&
