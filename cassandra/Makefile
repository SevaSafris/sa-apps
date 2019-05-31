.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.2.1-SNAPSHOT.jar

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
		-Dls.componentName=cassandra \
		-Dls.collectorHost=collector.lightstep.com \
		-Dls.collectorProtocol=https \
		-Dls.collectorPort=443 \
		-Dls.accessToken=$(LS_TOKEN) \
		-Dsa.tracer=lightstep \
		-cp target/cassandra-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) cassandra.App

run-solo:
	java \
		-Dsa.tracer=mock \
		-Dsa.log.level=FINER \
		-Dsa.tracer.plugins.enable=false \
		-Dsa.instrumentation.plugins.enable=false \
		-Dsa.instrumentation.plugin.cassandra.enable=true \
		-cp target/cassandra-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) cassandra.App

run-no-agent:
	java -cp target/cassandra-1.0-SNAPSHOT.jar aws.App