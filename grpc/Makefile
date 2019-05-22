.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.2.0-SNAPSHOT.jar

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
		-Dls.componentName=grpc \
		-Dls.collectorHost=collector.lightstep.com \
		-Dls.collectorProtocol=https \
		-Dls.collectorPort=443 \
		-Dls.accessToken=$(LS_TOKEN) \
		-Dsa.tracer=lightstep \
		-cp target/grpc-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) grpc.App

run-solo:
	java \
		-Dsa.tracer=mock \
		-Dsa.log.level=FINER \
		-Dsa.tracer.plugins.enable=false \
		-Dsa.instrumentation.plugins.enable=false \
		-Dsa.instrumentation.plugin.grpc.enable=true \
		-cp target/grpc-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) grpc.App

run-no-agent:
	java -cp target/grpc-1.0-SNAPSHOT.jar grpc.App