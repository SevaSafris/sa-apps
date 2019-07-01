
# Modify 'specialagent_jar' variable to point to correct specialagent jar:
specialagent_jar := opentracing-specialagent-1.3.2.jar

specialagent_jar_path := ${CURDIR}/../$(specialagent_jar)

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run-travis-test:
	java \
		-Dsa.tracer=mock \
		-javaagent:$(specialagent_jar_path) -jar target/${component_jar}

run-local:
	java \
		-Dls.componentName=${component_name} \
		-Dls.collectorHost=localhost \
		-Dls.collectorProtocol=http \
		-Dls.collectorPort=8360 \
		-Dls.accessToken=${LS_LOCAL_TOKEN} \
		-Dsa.tracer=lightstep \
		-javaagent:$(specialagent_jar_path) -jar target/${component_jar}

run:
	java \
		-Dls.componentName=${component_name} \
		-Dls.collectorHost=collector.lightstep.com \
		-Dls.collectorProtocol=https \
		-Dls.collectorPort=443 \
		-Dls.accessToken=$(LS_TOKEN) \
		-Dsa.tracer=lightstep \
		-javaagent:$(specialagent_jar_path) -jar target/${component_jar}

run-solo:
	java \
		-Dsa.tracer=mock \
		-Dsa.log.level=FINER \
		-Dsa.tracer.plugins.enable=false \
		-Dsa.instrumentation.plugins.enable=false \
		-Dsa.instrumentation.plugin.$(plugin_name).enable=true \
		-javaagent:$(specialagent_jar_path) -jar target/${component_jar}

run-no-agent:
	java -jar target/${component_jar}