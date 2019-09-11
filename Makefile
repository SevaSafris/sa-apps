
# Modify 'specialagent_jar' variable to point to correct specialagent jar:
# specialagent_jar := opentracing-specialagent-1.3.4.jar
specialagent_jar := opentracing-specialagent-1.3.7-SNAPSHOT.jar

specialagent_jar_path := ${CURDIR}/../$(specialagent_jar)

ifdef build_command_module
build_command_start = cd ../.. &&
specialagent_jar_path := ${CURDIR}/../../$(specialagent_jar)
endif

build_command = ${build_command_start} mvn clean package ${build_command_module} -DskipTests -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn

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
	cd asynchttpclient//asynchttpclient-2.7.0 && make build run-mock
	cd asynchttpclient//asynchttpclient-2.10.1 && make build run-mock
	cd aws/aws-1 && make build run-mock
	cd aws/aws-2 && make build run-mock
	cd cassandra/cassandra-3.0.0 && make build run-mock
	cd cassandra/cassandra-3.7.2 && make build run-mock
	cd concurrent && make build run-mock
	cd elasticsearch/elasticsearch-6.4.0 && make build run-mock
	cd elasticsearch/elasticsearch-7.3.1 && make build run-mock
	cd feign/feign-9.0.0 && make build run-mock
	cd feign/feign-10.4.0 && make build run-mock
	cd grpc/grpc-1.6.0 && make build run-mock
	cd grpc/grpc-1.23.0 && make build run-mock
	cd hazelcast/hazelcast-3.7 && make build run-mock
	cd hazelcast/hazelcast-3.12.2 && make build run-mock
	cd httpclient/httpclient-4.4 && make build run-mock
	cd httpclient/httpclient-4.5.9 && make build run-mock
	cd hystrix/hystrix-1.5.18 && make build run-mock
	cd jax-rs && make build run-mock
	cd jdbc && make build run-mock
	cd jdbi && make build run-mock
	cd jedis/jedis-2.7.0 && make build run-mock
	cd jedis/jedis-3.1.0 && make build run-mock
	cd jms/jms-1 && make build run-mock
	cd jms/jms-2 && make build run-mock
	cd kafka/kafka-1.1.0 && make build run-mock
	cd kafka/kafka-2.3.0 && make build run-mock
	cd lettuce/lettuce-5.0.0 && make build run-mock
	cd lettuce/lettuce-5.1.8 && make build run-mock
	cd mongo/mongo-3.9.0 && make build run-mock
	cd mongo/mongo-3.11.0 && make build run-mock
	cd mongo/mongo-async-3.9.0 && make build run-mock
	cd mongo/mongo-async-3.11.0 && make build run-mock
	cd mongo/mongo-reactive-1.8.0 && make build run-mock
	cd mongo/mongo-reactive-1.12.0 && make build run-mock
	cd okhttp/okhttp-3.5.0 && make build run-mock
	cd okhttp/okhttp-3.14.2 && make build run-mock
	cd rabbitmq/rabbitmq-5.0.0 && make build run-mock
	cd rabbitmq/rabbitmq-5.7.3 && make build run-mock
	cd redisson/redisson-3.6.0 && make build run-mock
	cd redisson/redisson-3.11.3 && make build run-mock
	cd rxjava/rxjava-2.1.0 && make build run-mock
	cd rxjava/rxjava-2.2.12 && make build run-mock
	cd spring-3 && make build run-mock
	cd spring-3-servlet-2.5 && make build run-mock
	cd spring-4 && make build run-mock
	cd spring-boot-jms/spring-boot-jms-1.5.22 && make build run-mock
	cd spring-boot-jms/spring-boot-jms-2.1.8 && make build run-mock
	cd spring-boot-kafka/spring-boot-kafka-2.1.0 && make build run-mock
	cd spring-boot-kafka/spring-boot-kafka-2.1.8 && make build run-mock
	cd spring-boot-rabbitmq/spring-boot-rabbitmq-2.0.0 && make build run-mock
	cd spring-boot-rabbitmq/spring-boot-rabbitmq-2.1.8 && make build run-mock
	cd spring-boot-data-redis/spring-boot-data-redis-1.5.22 && make build run-mock
	cd spring-boot-data-redis/spring-boot-data-redis-2.1.8 && make build run-mock
	cd spring-messaging && make build run-mock
	cd spring-messaging-rabbit && make build run-mock
	cd spring-scheduling/spring-scheduling-1.5.22 && make build run-mock
	cd spring-scheduling/spring-scheduling-2.1.8 && make build run-mock
	cd spring-web/spring-web-1.5.22 && make build run-mock
	cd spring-web/spring-web-2.1.8 && make build run-mock
	cd spring-webflux && make build run-mock
	cd spring-webmvc && make build run-mock
	cd spring-websocket && make build run-mock
	cd spymemcached && make build run-mock
	cd thrift && make build run-mock
	cd tomcat && make build run-mock
	cd zuul && make build run-mock

run-embedded:
	cd asynchttpclient//asynchttpclient-2.7.0 && make build run-mock
	cd asynchttpclient//asynchttpclient-2.10.1 && make build run-mock
	cd aws/aws-1 && make build run-mock
	cd aws/aws-2 && make build run-mock
	cd cassandra/cassandra-3.0.0 && make build run-mock
	cd cassandra/cassandra-3.7.2 && make build run-mock
	cd concurrent && make build run-mock
	cd elasticsearch/elasticsearch-6.4.0 && make build run-mock
	cd elasticsearch/elasticsearch-7.3.1 && make build run-mock
	cd feign/feign-9.0.0 && make build run-mock
	cd feign/feign-10.4.0 && make build run-mock
	cd grpc/grpc-1.6.0 && make build run-mock
	cd grpc/grpc-1.23.0 && make build run-mock
	cd hazelcast/hazelcast-3.7 && make build run-mock
	cd hazelcast/hazelcast-3.12.2 && make build run-mock
	cd httpclient/httpclient-4.4 && make build run-mock
	cd httpclient/httpclient-4.5.9 && make build run-mock
	cd hystrix/hystrix-1.5.18 && make build run-mock
	cd jax-rs && make build run-mock
	cd jdbc && make build run-mock
	cd jdbi && make build run-mock
	cd jedis/jedis-2.7.0 && make build run-mock
	cd jedis/jedis-3.1.0 && make build run-mock
	cd jms/jms-1 && make build run-mock
	cd jms/jms-2 && make build run-mock
	cd kafka/kafka-1.1.0 && make build run-mock
	cd kafka/kafka-2.3.0 && make build run-mock
	cd lettuce/lettuce-5.0.0 && make build run-mock
	cd lettuce/lettuce-5.1.8 && make build run-mock
	cd mongo/mongo-3.9.0 && make build run-mock
	cd mongo/mongo-3.11.0 && make build run-mock
	cd mongo/mongo-async-3.9.0 && make build run-mock
	cd mongo/mongo-async-3.11.0 && make build run-mock
	cd mongo/mongo-reactive-1.8.0 && make build run-mock
	cd mongo/mongo-reactive-1.12.0 && make build run-mock
	cd okhttp/okhttp-3.5.0 && make build run-mock
	cd okhttp/okhttp-3.14.2 && make build run-mock
	cd rabbitmq/rabbitmq-5.0.0 && make build run-mock
	cd rabbitmq/rabbitmq-5.7.3 && make build run-mock
	cd redisson/redisson-3.6.0 && make build run-mock
	cd redisson/redisson-3.11.3 && make build run-mock
	cd rxjava/rxjava-2.1.0 && make build run-mock
	cd rxjava/rxjava-2.2.12 && make build run-mock
	cd spring-3 && make build run-mock
	cd spring-3-servlet-2.5 && make build run-mock
	cd spring-4 && make build run-mock
	cd spring-boot-jms/spring-boot-jms-1.5.22 && make build run-mock
	cd spring-boot-jms/spring-boot-jms-2.1.8 && make build run-mock
	cd spring-boot-kafka/spring-boot-kafka-2.1.0 && make build run-mock
	cd spring-boot-kafka/spring-boot-kafka-2.1.8 && make build run-mock
	cd spring-boot-rabbitmq/spring-boot-rabbitmq-2.0.0 && make build run-mock
	cd spring-boot-rabbitmq/spring-boot-rabbitmq-2.1.8 && make build run-mock
	cd spring-boot-data-redis/spring-boot-data-redis-1.5.22 && make build run-mock
	cd spring-boot-data-redis/spring-boot-data-redis-2.1.8 && make build run-mock
	cd spring-messaging && make build run-mock
	cd spring-messaging-rabbit && make build run-mock
	cd spring-scheduling/spring-scheduling-1.5.22 && make build run-mock
	cd spring-scheduling/spring-scheduling-2.1.8 && make build run-mock
	cd spring-web/spring-web-1.5.22 && make build run-mock
	cd spring-web/spring-web-2.1.8 && make build run-mock
	cd spring-webflux && make build run-mock
	cd spring-webmvc && make build run-mock
	cd spring-websocket && make build run-mock
	cd thrift && make build run-mock
	cd tomcat && make build run-mock
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