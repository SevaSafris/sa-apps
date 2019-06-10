.PHONY: build clean run

specialagent_jar := opentracing-specialagent-1.2.1-SNAPSHOT.jar
specialagent_jar_path := /Users/malafes/Downloads/${specialagent_jar}
component_name := asynchttpclient
component_jar := asynchttpclient-1.0-SNAPSHOT.jar

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
		-cp target/${component_jar} -javaagent:$(specialagent_jar_path) asynchttpclient.App

run-travis-test:
	java \
		-Dls.componentName=${component_name} \
		-Dsa.tracer=mock \
		-cp target/${component_jar} -javaagent:${CURDIR}/../$(specialagent_jar) asynchttpclient.App

run-solo:
	java \
		-Dsa.tracer=mock \
		-Dsa.log.level=FINER \
		-Dsa.tracer.plugins.enable=false \
		-Dsa.instrumentation.plugins.enable=false \
		-Dsa.instrumentation.plugin.asynchttpclient.enable=true \
		-cp target/${component_jar} -javaagent:$(specialagent_jar_path) asynchttpclient.App

run-no-agent:
	java -cp target/${component_jar} asynchttpclient.App