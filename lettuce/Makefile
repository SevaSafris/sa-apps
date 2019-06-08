.PHONY: build clean run

specialagent_jar := opentracing-specialagent-1.2.1-SNAPSHOT.jar
specialagent_jar_path := /Users/malafes/Downloads/${specialagent_jar}
component_name := lettuce
component_jar := lettuce-1.0-SNAPSHOT.jar

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
		-Dsa.tracer=lightstep \
		-cp target/${component_jar} -javaagent:$(specialagent_jar_path) lettuce.App

run-local:
	java \
        -Dls.componentName=${component_name} \
        -Dls.collectorHost=localhost \
        -Dls.collectorProtocol=http \
        -Dls.collectorPort=8360 \
        -Dls.accessToken=${LS_LOCAL_TOKEN} \
        -Dsa.tracer=lightstep \
        -cp target/${component_jar} -javaagent:$(specialagent_jar_path) lettuce.App

run-mock-tracer:
	java \
		-Dsa.tracer=mock \
		-cp target/${component_jar} -javaagent:$(specialagent_jar_path) lettuce.App

run-solo:
	java \
		-Dsa.tracer=mock \
		-Dsa.log.level=FINER \
		-Dsa.tracer.plugins.enable=false \
		-Dsa.instrumentation.plugins.enable=false \
		-Dsa.instrumentation.plugin.lettuce.enable=true \
		-cp target/lettuce-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar_path) lettuce.App

run-no-agent:
	java -cp target/${component_jar} lettuce.App