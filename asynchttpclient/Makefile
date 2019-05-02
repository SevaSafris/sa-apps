.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.1.1-SNAPSHOT.jar

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
	    -Dls.componentName=asynchttpclient \
	    -Dls.collectorHost=collector.lightstep.com \
	    -Dls.collectorProtocol=https \
	    -Dls.collectorPort=443 \
	    -Dls.accessToken=$(LS_TOKEN) \
	    -Dsa.tracer=lightstep \
		-cp target/asynchttpclient-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) asynchttpclient.App

run-no-agent:
	java -cp target/asynchttpclient-1.0-SNAPSHOT.jar asynchttpclient.App


