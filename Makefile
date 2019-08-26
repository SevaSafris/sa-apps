
# Modify 'specialagent_jar' variable to point to correct specialagent jar:
# specialagent_jar := opentracing-specialagent-1.3.4.jar
specialagent_jar := opentracing-specialagent-1.3.6-SNAPSHOT.jar

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

run:
	cd asynchttpclient && make build run-mock
	cd aws && make build run-mock
	cd aws-2 && make build run-mock
	cd cassandra && make build run-mock
	cd concurrent && make build run-mock
	cd elasticsearch-6 && make build run-mock
	cd elasticsearch-7 && make build run-mock
	cd feign && make build run-mock
	cd grpc && make build run-mock
	cd hazelcast && make build run-mock
	cd httpclient && make build run-mock
	cd httpclient-4.2.5 && make build run-mock
	cd hystrix && make build run-mock
	cd jax-rs && make build run-mock
	cd jdbc && make build run-mock
	cd jdbi && make build run-mock
	cd jedis-2 && make build run-mock
	cd jedis-3 && make build run-mock
	cd jms-1 && make build run-mock
	cd jms-2 && make build run-mock
	cd kafka && make build run-mock
	cd lettuce && make build run-mock
	cd mongo && make build run-mock
	cd mongo-async && make build run-mock
	cd mongo-reactive && make build run-mock
	cd okhttp && make build run-mock
	cd rabbitmq && make build run-mock
	cd redisson && make build run-mock
	cd rxjava-2 && make build run-mock
	cd spring-3 && make build run-mock
	cd spring-3-servlet-2.5 && make build run-mock
	cd spring-4 && make build run-mock
	cd spring-boot-jms && make build run-mock
	cd spring-boot-kafka && make build run-mock
	cd spring-boot-rabbitmq && make build run-mock
	cd spring-data-redis && make build run-mock
	cd spring-messaging && make build run-mock
	cd spring-messaging-rabbit && make build run-mock
	cd spring-scheduling && make build run-mock
	cd spring-web && make build run-mock
	cd spring-webflux && make build run-mock
	cd spring-webmvc && make build run-mock
	cd spring-websocket && make build run-mock
	cd spymemcached && make build run-mock
	cd thrift && make build run-mock
	cd zuul && make build run-mock

run-embedded:
	cd asynchttpclient && make build run-mock
	cd aws && make build run-mock
	cd aws-2 && make build run-mock
	cd cassandra && make build run-mock
	cd concurrent && make build run-mock
	cd elasticsearch-6 && make build run-mock
	cd elasticsearch-7 && make build run-mock
	cd feign && make build run-mock
	cd grpc && make build run-mock
	cd hazelcast && make build run-mock
	cd httpclient && make build run-mock
	cd httpclient-4.2.5 && make build run-mock
	cd hystrix && make build run-mock
	cd jax-rs && make build run-mock
	cd jdbc && make build run-mock
	cd jdbi && make build run-mock
	cd jedis-2 && make build run-mock
	cd jedis-3 && make build run-mock
	cd jms-1 && make build run-mock
	cd jms-2 && make build run-mock
	cd kafka && make build run-mock
	cd lettuce && make build run-mock
	cd mongo && make build run-mock
	cd mongo-async && make build run-mock
	cd mongo-reactive && make build run-mock
	cd okhttp && make build run-mock
	cd rabbitmq && make build run-mock
	cd redisson && make build run-mock
	cd rxjava-2 && make build run-mock
	cd spring-3 && make build run-mock
	cd spring-3-servlet-2.5 && make build run-mock
	cd spring-4 && make build run-mock
	cd spring-boot-jms && make build run-mock
	cd spring-boot-kafka && make build run-mock
	cd spring-boot-rabbitmq && make build run-mock
	cd spring-data-redis && make build run-mock
	cd spring-messaging && make build run-mock
	cd spring-messaging-rabbit && make build run-mock
	cd spring-scheduling && make build run-mock
	cd spring-web && make build run-mock
	cd spring-webflux && make build run-mock
	cd spring-webmvc && make build run-mock
	cd spring-websocket && make build run-mock
	cd thrift && make build run-mock
	cd zuul && make build run-mock

run-integrated:
	cd spymemcached && make build run-mock

run-mock:
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

run-ls:
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