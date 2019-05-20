.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.1.3-SNAPSHOT.jar

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
	    -Dls.componentName=spring-webmvc \
	    -Dls.collectorHost=collector.lightstep.com \
        -Dls.collectorProtocol=https \
        -Dls.collectorPort=443 \
        -Dls.accessToken=$(LS_TOKEN) \
        -Dsa.tracer=lightstep \
		-cp target/spring-webmvc-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) org.springframework.boot.loader.JarLauncher

run-no-agent:
	java -cp target/spring-webmvc-1.0-SNAPSHOT.jar org.springframework.boot.loader.JarLauncher
