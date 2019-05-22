.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.2.0-SNAPSHOT.jar

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
		-Dls.componentName=jedis-3 \
		-Dls.collectorHost=collector.lightstep.com \
		-Dls.collectorProtocol=https \
		-Dls.collectorPort=443 \
		-Dls.accessToken=$(LS_TOKEN) \
		-Dsa.tracer=lightstep \
		-cp target/jedis-3-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) jedis3.App

run-solo:
	java \
		-Dsa.tracer=mock \
		-Dsa.log.level=FINER \
		-Dsa.tracer.plugins.enable=false \
		-Dsa.instrumentation.plugins.enable=false \
		-Dsa.instrumentation.plugin.jedis.enable=true \
		-cp target/jedis-3-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) jedis3.App

run-no-agent:
	java -cp target/jedis-3-1.0-SNAPSHOT.jar jedis3.App