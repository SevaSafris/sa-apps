build:
	mvn clean package -DskipTests

clean:
	mvn clean

run-travis-test:
	java \
		-Dls.componentName=${component_name} \
		-Dsa.tracer=mock \
		-cp target/${component_jar} -javaagent:${CURDIR}/../$(specialagent_jar) $(main_class)

run-local:
	java \
		-Dls.componentName=${component_name} \
		-Dls.collectorHost=localhost \
		-Dls.collectorProtocol=http \
		-Dls.collectorPort=8360 \
		-Dls.accessToken=${LS_LOCAL_TOKEN} \
		-Dsa.tracer=lightstep \
		-cp target/${component_jar} -javaagent:$(specialagent_jar_path) $(main_class)

run:
	java \
		-Dls.componentName=${component_name} \
		-Dls.collectorHost=collector.lightstep.com \
		-Dls.collectorProtocol=https \
		-Dls.collectorPort=443 \
		-Dls.accessToken=$(LS_TOKEN) \
		-Dsa.tracer=lightstep \
		-cp target/${component_jar} -javaagent:$(specialagent_jar_path) $(main_class)

run-solo:
	java \
		-Dsa.tracer=mock \
		-Dsa.log.level=FINER \
		-Dsa.tracer.plugins.enable=false \
		-Dsa.instrumentation.plugins.enable=false \
		-Dsa.instrumentation.plugin.feign.enable=true \
		-cp target/${component_jar} -javaagent:$(specialagent_jar_path) $(main_class)

run-no-agent:
	java -cp target/${component_jar} $(main_class)