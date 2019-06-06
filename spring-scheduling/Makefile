.PHONY: build clean run

specialagent_jar := opentracing-specialagent-1.2.1-SNAPSHOT.jar
specialagent_jar_path := /Users/malafes/Downloads/${specialagent_jar}
component_name := spring-scheduling
component_jar := spring-scheduling-1.0-SNAPSHOT.jar


build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
		-Dls.componentName=${component_name} \
		-Dls.collectorHost=collector.lightstep.com \
		-Dls.collectorProtocol=https \
		-Dls.collectorPort=443 \
		-Dls.accessToken=$(LS_TOKEN) \
		-Dsa.tracer=mock \
		-Dsa.log.level=FINEST \
		-Dsa.tracer.plugins.enable=false \
		-Dsa.instrumentation.plugins.enable=false \
		-Dsa.instrumentation.plugin.spring-messaging.enable=true \
		-cp target/${component_jar} -javaagent:$(specialagent_jar_path) org.springframework.boot.loader.JarLauncher

run-travis-test:
	java \
		-Dls.componentName=${component_name} \
		-Dsa.tracer=mock \
		-cp target/${component_jar} -javaagent:${CURDIR}/../$(specialagent_jar) org.springframework.boot.loader.JarLauncher

run-local:
	java \
        -Dls.componentName=${component_name} \
        -Dls.collectorHost=localhost \
        -Dls.collectorProtocol=http \
        -Dls.collectorPort=8360 \
        -Dls.accessToken=${LS_LOCAL_TOKEN} \
        -Dsa.tracer=lightstep \
        -cp target/${component_jar} -javaagent:$(specialagent_jar_path) org.springframework.boot.loader.JarLauncher

run-solo:
	java \
		-Dsa.tracer=mock \
		-Dsa.log.level=FINER \
		-Dsa.tracer.plugins.enable=false \
		-Dsa.instrumentation.plugins.enable=false \
		-Dsa.instrumentation.plugin.spring-scheduling.enable=true \
		-cp target/${component_jar} -javaagent:$(specialagent_jar_path) org.springframework.boot.loader.JarLauncher

run-no-agent:
	java -cp target/${component_jar} org.springframework.boot.loader.JarLauncher