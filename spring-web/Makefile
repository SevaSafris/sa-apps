.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.1.2-SNAPSHOT.jar

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
	    -Dls.componentName=spring-web \
	    -Dls.collectorHost=collector.lightstep.com \
	    -Dls.collectorProtocol=https \
	    -Dls.collectorPort=443 \
	    -Dls.accessToken=$(LS_TOKEN) \
	    -Dsa.tracer=mock \
		-cp target/spring-web-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) boot.App

run-no-agent:
	java -cp target/spring-web-1.0-SNAPSHOT.jar boot.App
