.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.2.1-SNAPSHOT.jar

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
		-Dls.componentName=feign \
		-Dls.collectorHost=collector.lightstep.com \
		-Dls.collectorProtocol=https \
		-Dls.collectorPort=443 \
		-Dls.accessToken=$(LS_TOKEN) \
		-Dsa.tracer=lightstep \
		-cp target/feign-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) feign.App

run-local:
	java \
		-Dls.componentName=feign \
		-Dls.collectorHost=localhost \
		-Dls.collectorProtocol=http \
		-Dls.collectorPort=8360 \
		-Dls.accessToken=${LS_LOCAL_TOKEN} \
		-Dsa.tracer=lightstep \
		-cp target/feign-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) feign.App

run-solo:
	java \
		-Dsa.tracer=mock \
		-Dsa.log.level=FINER \
		-Dsa.tracer.plugins.enable=false \
		-Dsa.instrumentation.plugins.enable=false \
		-Dsa.instrumentation.plugin.feign.enable=true \
		-cp target/feign-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) feign.App

run-no-agent:
	java -cp target/feign-1.0-SNAPSHOT.jar feign.App