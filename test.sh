#!/usr/bin/env bash

spring_clouds=( spring-cloud-greenwich spring-cloud-finchley spring-cloud-edgware )

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

cd spring-boot-rabbitmq
for spring_cloud in "${spring_clouds[@]}"
do
  echo "##############################################################################################"
  echo "spring-boot-rabbitmq-${spring_cloud}"
	make build-${spring_cloud} run-mocktracer-test || exit $?
done
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

cd spring-boot-kafka
for spring_cloud in "${spring_clouds[@]}"
do
  echo "##############################################################################################"
  echo "spring-boot-kafka-${spring_cloud}"
	make build-${spring_cloud} run-mocktracer-test || exit $?
done
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

cd spring-boot-jms
for spring_cloud in "${spring_clouds[@]}"
do
  echo "##############################################################################################"
  echo "spring-boot-jms-${spring_cloud}"
	make build-${spring_cloud} run-mocktracer-test || exit $?
done
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

cd hystrix
echo "##############################################################################################"
echo "hystrix"
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
docker pull docker.elastic.co/elasticsearch/elasticsearch:6.6.1
docker run --name es6 -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:6.6.1
sleep 10
make build run-mocktracer-test || exit $?
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


cd spring-data-redis
for spring_cloud in "${spring_clouds[@]}"
do
  echo "##############################################################################################"
  echo "spring-data-redis-${spring_cloud}"
	make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd asynchttpclient
echo "##############################################################################################"
echo "asynchttpclient"
make build run-mocktracer-test || exit $?
cd ..

cd feign
echo "##############################################################################################"
echo "feign"
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

cd spring-web
for spring_cloud in "${spring_clouds[@]}"
do
  echo "##############################################################################################"
  echo "spring-web-${spring_cloud}"
	make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd spring-webmvc
for spring_cloud in "${spring_clouds[@]}"
do
  echo "##############################################################################################"
  echo "spring-webmvc-${spring_cloud}"
	make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd spring-webflux
for spring_cloud in "${spring_clouds[@]}"
do
  echo "##############################################################################################"
  echo "spring-webflux-${spring_cloud}"
	make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd spring-scheduling
for spring_cloud in "${spring_clouds[@]}"
do
  echo "##############################################################################################"
  echo "spring-scheduling-${spring_cloud}"
	make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd spring-websocket
for spring_cloud in "${spring_clouds[@]}"
do
  echo "##############################################################################################"
  echo "spring-websocket-${spring_cloud}"
	make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd zuul
for spring_cloud in "${spring_clouds[@]}"
do
  echo "##############################################################################################"
  echo "zuul-${spring_cloud}"
	make build-${spring_cloud} run-mocktracer-test || exit $?
done
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

cd spring-messaging
for spring_cloud in "${spring_clouds[@]}"
do
  echo "##############################################################################################"
  echo "spring-messaging-${spring_cloud}"
	make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..
