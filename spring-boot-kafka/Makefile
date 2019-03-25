.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.0.0.jar

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
	    -Dls.componentName=spring-boot-kafka \
	    -Dls.collectorHost=collector.lightstep.com \
	    -Dls.collectorProtocol=https \
	    -Dls.collectorPort=443 \
	    -Dls.accessToken=$(LS_TOKEN) \
	    -Dspecialagent.tracer=lightstep \
		-cp target/spring-boot-kafka-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) org.springframework.boot.loader.JarLauncher

run-no-agent:
	java -cp target/spring-boot-kafka-1.0-SNAPSHOT.jar org.springframework.boot.loader.JarLauncher
