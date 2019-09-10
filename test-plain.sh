#!/usr/bin/env bash

cd cassandra/cassandra-3.0.0
echo "##############################################################################################"
echo "cassandra-3.0.0"
make build run-mock || exit $?
cd ../..

cd cassandra/cassandra-3.7.2
echo "##############################################################################################"
echo "cassandra-3.7.2"
make build run-mock || exit $?
cd ../..

cd concurrent
echo "##############################################################################################"
echo "concurrent"
make build run-mock || exit $?
cd ..

cd rxjava/rxjava-2.1.0
echo "##############################################################################################"
echo "rxjava-2.1.0"
make build run-mock || exit $?
cd ../..

cd rxjava/rxjava-2.2.12
echo "##############################################################################################"
echo "rxjava-2.2.12"
make build run-mock || exit $?
cd ../..

cd rabbitmq/rabbitmq-5.0.0
echo "##############################################################################################"
echo "rabbitmq-5.0.0"
make build run-mock || exit $?
cd ../..

cd rabbitmq/rabbitmq-5.7.3
echo "##############################################################################################"
echo "rabbitmq-5.7.3"
make build run-mock || exit $?
cd ../..

cd mongo
echo "##############################################################################################"
echo "mongo"
make build run-mock || exit $?
cd ..

cd mongo-async
echo "##############################################################################################"
echo "mongo-async"
make build run-mock || exit $?
cd ..

cd mongo-reactive
echo "##############################################################################################"
echo "mongo-reactive"
make build run-mock || exit $?
cd ..

cd kafka/kafka-1.1.0
echo "##############################################################################################"
echo "kafka-1.1.0"
make build run-mock || exit $?
cd ../..

cd kafka/kafka-2.3.0
echo "##############################################################################################"
echo "kafka-2.3.0"
make build run-mock || exit $?
cd ../..

cd jms/jms-1
echo "##############################################################################################"
echo "jms-1"
make build run-mock || exit $?
cd ../..

cd jms/jms-2
echo "##############################################################################################"
echo "jms-2"
make build run-mock || exit $?
cd ../..

cd jdbi
echo "##############################################################################################"
echo "jdbi"
make build run-mock || exit $?
cd ..

cd jdbc
echo "##############################################################################################"
echo "jdbc"
make build run-mock || exit $?
cd ..

cd hazelcast/hazelcast-3.7
echo "##############################################################################################"
echo "hazelcast-3.7"
make build run-mock || exit $?
cd ../..

cd hazelcast/hazelcast-3.12.2
echo "##############################################################################################"
echo "hazelcast-3.12.2"
make build run-mock || exit $?
cd ../..

cd grpc/grpc-1.6.0
echo "##############################################################################################"
echo "grpc-1.6.0"
make build run-mock || exit $?
cd ../..

cd grpc/grpc-1.23.0
echo "##############################################################################################"
echo "grpc-1.23.0"
make build run-mock || exit $?
cd ../..

cd elasticsearch/elasticsearch-6.4.0
echo "##############################################################################################"
echo "elasticsearch-6.4.0"
make build run-mock || exit $?
cd ../..

cd elasticsearch/elasticsearch-7.3.1
echo "##############################################################################################"
echo "elasticsearch-7.3.1"
make build run-mock || exit $?
cd ../..

cd aws/aws-1
echo "##############################################################################################"
echo "aws-1"
make build run-mock || exit $?
cd ../..

cd aws/aws-2
echo "##############################################################################################"
echo "aws-2"
make build run-mock || exit $?
cd ../..

cd jedis/jedis-2.7.0
echo "##############################################################################################"
echo "jedis-2.7.0"
make build run-mock || exit $?
cd ../..

cd jedis/jedis-3.1.0
echo "##############################################################################################"
echo "jedis-3.1.0"
make build run-mock || exit $?
cd ../..

cd lettuce/lettuce-5.0.0
echo "##############################################################################################"
echo "lettuce-5.0.0"
make build run-mock || exit $?
cd ../..

cd lettuce/lettuce-5.1.8
echo "##############################################################################################"
echo "lettuce-5.1.8"
make build run-mock || exit $?
cd ../..

cd asynchttpclient/asynchttpclient-2.7.0
echo "##############################################################################################"
echo "/asynchttpclient-2.7.0"
make build run-mock || exit $?
cd ../..

cd asynchttpclient/asynchttpclient-2.10.1
echo "##############################################################################################"
echo "/asynchttpclient-2.10.1"
make build run-mock || exit $?
cd ../..

cd okhttp/okhttp-3.5.0
echo "##############################################################################################"
echo "okhttp-3.5.0"
make build run-mock || exit $?
cd ../..

cd okhttp/okhttp-3.14.2
echo "##############################################################################################"
echo "okhttp-3.14.2"
make build run-mock || exit $?
cd ../..

cd httpclient/httpclient-4.4
echo "##############################################################################################"
echo "httpclient-4.4"
make build run-mock || exit $?
cd ../..

cd httpclient/httpclient-4.5.9
echo "##############################################################################################"
echo "httpclient-4.5.9"
make build run-mock || exit $?
cd ../..

cd spymemcached/
echo "##############################################################################################"
echo "spymemcached/"
make build run-mock || exit $?
cd ..

cd thrift
echo "##############################################################################################"
echo "thrift"
make build run-mock || exit $?
cd ..

cd tomcat
echo "##############################################################################################"
echo "tomcat"
make build run-mock || exit $?
cd ..

cd redisson/redisson-3.6.0
echo "##############################################################################################"
echo "redisson-3.6.0"
make build run-mock || exit $?
cd ../..

cd redisson/redisson-3.11.3
echo "##############################################################################################"
echo "redisson-3.11.3"
make build run-mock || exit $?
cd ../..

cd feign/feign-9.0.0
echo "##############################################################################################"
echo "feign-9.0.0"
make build run-mock || exit $?
cd ../..

cd feign/feign-10.4.0
echo "##############################################################################################"
echo "feign-10.4.0"
make build run-mock || exit $?
cd ../..

cd hystrix/hystrix-1.5.18
echo "##############################################################################################"
echo "hystrix-1.5.18"
make build run-mock || exit $?
cd ../..
