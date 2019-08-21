
# Modify 'specialagent_jar' variable to point to correct specialagent jar:
# specialagent_jar := opentracing-specialagent-1.3.4.jar
specialagent_jar := opentracing-specialagent-1.3.5-SNAPSHOT.jar


specialagent_jar_path := ${CURDIR}/../$(specialagent_jar)

build_command = mvn clean package -DskipTests -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn

run_command = -jar target/${component_jar}

build:
	${build_command}

build-spring-cloud-greenwich:
	${build_command} -Dspring.boot.version=2.1.7.RELEASE -Dspring.cloud.version=Greenwich.SR2

build-spring-cloud-finchley:
	${build_command} -Dspring.boot.version=2.0.9.RELEASE -Dspring.cloud.version=Finchley.SR4

build-spring-cloud-edgware:
	${build_command} -Dspring.boot.version=1.5.21.RELEASE -Dspring.cloud.version=Edgware.SR6

clean:
	mvn clean

run-mocktracer-test:
	java \
		-Dsa.tracer=mock \
		-javaagent:$(specialagent_jar_path) ${run_command}

run-local:
	java \
		-Dls.componentName=${component_name} \
		-Dls.collectorHost=localhost \
		-Dls.collectorProtocol=http \
		-Dls.collectorPort=8360 \
		-Dls.accessToken=${LS_LOCAL_TOKEN} \
		-Dsa.tracer=lightstep \
		-javaagent:$(specialagent_jar_path) ${run_command}

run:
	java \
		-Dls.componentName=${component_name} \
		-Dls.collectorHost=collector.lightstep.com \
		-Dls.collectorProtocol=https \
		-Dls.collectorPort=443 \
		-Dls.accessToken=$(LS_TOKEN) \
		-Dsa.tracer=lightstep \
		-javaagent:$(specialagent_jar_path) ${run_command}

run-solo:
	java \
		-Dsa.tracer=mock \
		-Dsa.log.level=FINER \
		-Dsa.tracer.plugins.enable=false \
		-Dsa.instrumentation.plugins.enable=false \
		-Dsa.instrumentation.plugin.$(plugin_name).enable=true \
		-javaagent:$(specialagent_jar_path) ${run_command}

run-no-agent:
	java ${run_command}