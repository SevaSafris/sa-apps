.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.1.1-SNAPSHOT.jar

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
		-cp target/spring-web-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) org.springframework.boot.loader.JarLauncher

run-no-agent:
	java -cp target/spring-web-1.0-SNAPSHOT.jar org.springframework.boot.loader.JarLauncher
