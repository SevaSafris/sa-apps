.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.1.1-SNAPSHOT.jar

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
	    -Dls.componentName=lettuce \
	    -Dls.collectorHost=collector.lightstep.com \
	    -Dls.collectorProtocol=https \
	    -Dls.collectorPort=443 \
	    -Dls.accessToken=$(LS_TOKEN) \
	    -Dsa.tracer=lightstep \
		-cp target/lettuce-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) lettuce.App

run-mock-tracer:
	java \
	    -Dsa.tracer=mock \
		-cp target/lettuce-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) lettuce.App


run-no-agent:
	java -cp target/lettuce-1.0-SNAPSHOT.jar lettuce.App

