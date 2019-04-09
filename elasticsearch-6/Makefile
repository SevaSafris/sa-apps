.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.0.2.jar

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
	    -Dls.componentName=elasticsearch \
	    -Dls.collectorHost=collector.lightstep.com \
	    -Dls.collectorProtocol=https \
	    -Dls.collectorPort=443 \
	    -Dls.accessToken=$(LS_TOKEN) \
	    -Dsa.tracer=lightstep \
		-cp target/elasticsearch-6-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) es6.App

run-no-agent:
	java -cp target/elasticsearch-6-1.0-SNAPSHOT.jar es6.App

