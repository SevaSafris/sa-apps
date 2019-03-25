.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.0.0.jar

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
	    -Dls.componentName=rxjava \
	    -Dls.collectorHost=collector.lightstep.com \
	    -Dls.collectorProtocol=https \
	    -Dls.collectorPort=443 \
	    -Dls.accessToken=$(LS_TOKEN) \
	    -Dspecialagent.tracer=lightstep \
		-cp target/rxjava-2-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) rxjava.App

run-no-agent:
	java -cp target/rxjava-2-1.0-SNAPSHOT.jar rxjava.App

