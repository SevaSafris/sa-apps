#!/usr/bin/env bash


java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
if [[ ${java_version} == 11* ]]; then
  export ADD_JAVA_MOD="--add-reads java.base=java.logging"
fi

cd httpclient-4.2.5
echo "##############################################################################################"
echo "httpclient-4.2.5"
make build run-mocktracer-test || exit $?
cd ..

cd concurrent
echo "##############################################################################################"
echo "concurrent"
make build run-mocktracer-test || exit $?
cd ..

cd rxjava-2
echo "##############################################################################################"
echo "rxjava-2"
make build run-mocktracer-test || exit $?
cd ..

cd rabbitmq
echo "##############################################################################################"
echo "rabbitmq"
make build run-mocktracer-test || exit $?
cd ..

cd mongo
echo "##############################################################################################"
echo "mongo"
make build run-mocktracer-test || exit $?
cd ..

cd mongo-async
echo "##############################################################################################"
echo "mongo-async"
make build run-mocktracer-test || exit $?
cd ..

cd mongo-reactive
echo "##############################################################################################"
echo "mongo-reactive"
make build run-mocktracer-test || exit $?
cd ..

cd kafka
echo "##############################################################################################"
echo "kafka"
wget http://www-us.apache.org/dist/kafka/2.2.1/kafka_2.12-2.2.1.tgz
tar xzf kafka_2.12-2.2.1.tgz
kafka_2.12-2.2.1/bin/zookeeper-server-start.sh -daemon kafka_2.12-2.2.1/config/zookeeper.properties
sleep 5
kafka_2.12-2.2.1/bin/kafka-server-start.sh -daemon kafka_2.12-2.2.1/config/server.properties
sleep 5
make build run-mocktracer-test || exit $?
cd ..

cd jms-1
echo "##############################################################################################"
echo "jms-1"
docker run --name artemis -p 61616:61616 -d vromero/activemq-artemis
make build run-mocktracer-test || exit $?
cd ..

cd jms-2
echo "##############################################################################################"
echo "jms-2"
make build run-mocktracer-test || exit $?
cd ..

cd jdbi
echo "##############################################################################################"
echo "jdbi"
make build run-mocktracer-test || exit $?
cd ..

cd jdbc
echo "##############################################################################################"
echo "jdbc"
make build run-mocktracer-test || exit $?
cd ..

cd hazelcast
echo "##############################################################################################"
echo "hazelcast"
make build run-mocktracer-test || exit $?
cd ..

cd grpc
echo "##############################################################################################"
echo "grpc"
make build run-mocktracer-test || exit $?
cd ..

cd elasticsearch-6
echo "##############################################################################################"
echo "elasticsearch-6"
docker pull docker.elastic.co/elasticsearch/elasticsearch:6.8.1
docker run --name es6 -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:6.8.1
sleep 10
make build run-mocktracer-test || exit $?
docker stop es6
cd ..

cd elasticsearch-7
echo "##############################################################################################"
echo "elasticsearch-7"
docker pull docker.elastic.co/elasticsearch/elasticsearch:7.2.0
docker run --name es7 -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.2.0
make build run-mocktracer-test || exit $?
docker stop es7
cd ..

cd cassandra
echo "##############################################################################################"
echo "cassandra"
make build run-mocktracer-test || exit $?
cd ..

cd aws
echo "##############################################################################################"
echo "aws"
make build run-mocktracer-test || exit $?
cd ..

cd aws-2
echo "##############################################################################################"
echo "aws-2"
make build run-mocktracer-test || exit $?
cd ..

cd jedis-2
echo "##############################################################################################"
echo "jedis-2"
make build run-mocktracer-test || exit $?
cd ..

cd jedis-3
echo "##############################################################################################"
echo "jedis-3"
make build run-mocktracer-test || exit $?
cd ..

cd lettuce
echo "##############################################################################################"
echo "lettuce"
make build run-mocktracer-test || exit $?
cd ..

cd asynchttpclient
echo "##############################################################################################"
echo "asynchttpclient"
make build run-mocktracer-test || exit $?
cd ..

cd okhttp
echo "##############################################################################################"
echo "okhttp"
make build run-mocktracer-test || exit $?
cd ..

cd httpclient
echo "##############################################################################################"
echo "httpclient"
make build run-mocktracer-test || exit $?
cd ..


cd spymemcached/
echo "##############################################################################################"
echo "spymemcached/"
make build run-mocktracer-test || exit $?
cd ..

cd thrift
echo "##############################################################################################"
echo "thrift"
make build run-mocktracer-test || exit $?
cd ..


cd redisson
echo "##############################################################################################"
echo "redisson"
make build run-mocktracer-test || exit $?
cd ..

cd feign
echo "##############################################################################################"
echo "feign"
make build run-mocktracer-test || exit $?
cd ..

cd hystrix
echo "##############################################################################################"
echo "hystrix"
make build run-mocktracer-test || exit $?
cd ..