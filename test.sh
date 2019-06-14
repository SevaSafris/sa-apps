#!/usr/bin/env bash

cd rxjava-2
echo "##############################################################################################"
echo "rxjava-2"
make build run-travis-test || exit $?
cd ..

cd rabbitmq
echo "##############################################################################################"
echo "rabbitmq"
make build run-travis-test || exit $?
cd ..

cd spring-boot-rabbitmq
echo "##############################################################################################"
echo "spring-boot-rabbitmq"
make build run-travis-test || exit $?
cd ..

cd mongo
echo "##############################################################################################"
echo "mongo"
make build run-travis-test || exit $?
cd ..

cd mongo-async
echo "##############################################################################################"
echo "mongo-async"
make build run-travis-test || exit $?
cd ..

cd mongo-reactive
echo "##############################################################################################"
echo "mongo-reactive"
make build run-travis-test || exit $?
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
make build run-travis-test || exit $?
cd ..

cd spring-boot-kafka
echo "##############################################################################################"
echo "spring-boot-kafka"
make build run-travis-test || exit $?
cd ..

cd jms-1
echo "##############################################################################################"
echo "jms-1"
docker run --name artemis -p 61616:61616 -d vromero/activemq-artemis
make build run-travis-test || exit $?
cd ..

cd jms-2
echo "##############################################################################################"
echo "jms-2"
make build run-travis-test || exit $?
cd ..

cd spring-boot-jms
echo "##############################################################################################"
echo "spring-boot-jms"
make build run-travis-test || exit $?
docker stop artemis
cd ..

cd jdbi
echo "##############################################################################################"
echo "jdbi"
make build run-travis-test || exit $?
cd ..

cd jdbc
echo "##############################################################################################"
echo "jdbc"
make build run-travis-test || exit $?
cd ..

cd hystrix
echo "##############################################################################################"
echo "hystrix"
make build run-travis-test || exit $?
cd ..

cd hazelcast
echo "##############################################################################################"
echo "hazelcast"
make build run-travis-test || exit $?
cd ..

cd grpc
echo "##############################################################################################"
echo "grpc"
make build run-travis-test || exit $?
cd ..

cd elasticsearch-6
echo "##############################################################################################"
echo "elasticsearch-6"
docker pull docker.elastic.co/elasticsearch/elasticsearch:6.6.1
docker run --name es6 -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:6.6.1
sleep 10
make build run-travis-test || exit $?
cd ..

cd spring-boot-elasticsearch
echo "##############################################################################################"
echo "spring-boot-elasticsearch"
make build run-travis-test || exit $?
docker stop es6
cd ..

cd cassandra
echo "##############################################################################################"
echo "cassandra"
make build run-travis-test || exit $?
cd ..

cd aws
echo "##############################################################################################"
echo "aws"
make build run-travis-test || exit $?
cd ..

cd jedis-2
echo "##############################################################################################"
echo "jedis-2"
make build run-travis-test || exit $?
cd ..

cd jedis-3
echo "##############################################################################################"
echo "jedis-3"
make build run-travis-test || exit $?
cd ..

cd lettuce
echo "##############################################################################################"
echo "lettuce"
make build run-travis-test || exit $?
cd ..


cd spring-data-redis
echo "##############################################################################################"
echo "spring-data-redis"
make build run-travis-test || exit $?
cd ..

cd spring-data-redis-2
echo "##############################################################################################"
echo "spring-data-redis-2"
make build run-travis-test || exit $?
cd ..

cd redisson
echo "##############################################################################################"
echo "redisson"
make build run-travis-test || exit $?
cd ..

cd asynchttpclient
echo "##############################################################################################"
echo "asynchttpclient"
make build run-travis-test || exit $?
cd ..

cd feign
echo "##############################################################################################"
echo "feign"
make build run-travis-test || exit $?
cd ..

cd okhttp
echo "##############################################################################################"
echo "okhttp"
make build run-travis-test || exit $?
cd ..

cd httpclient
echo "##############################################################################################"
echo "httpclient"
make build run-travis-test || exit $?
cd ..

cd spring-web
echo "##############################################################################################"
echo "spring-web"
make build run-travis-test || exit $?
cd ..

cd spring-webmvc
echo "##############################################################################################"
echo "spring-webmvc"
make build run-travis-test || exit $?
cd ..

cd spring-webflux
echo "##############################################################################################"
echo "spring-webflux"
make build run-travis-test || exit $?
cd ..

cd spring-scheduling
echo "##############################################################################################"
echo "spring-scheduling"
make build run-travis-test || exit $?
cd ..

cd spring-messaging
echo "##############################################################################################"
echo "spring-messaging"
make build run-travis-test || exit $?
cd ..

cd spring--websocket
echo "##############################################################################################"
echo "spring--websocket"
make build run-travis-test || exit $?
cd ..

cd zuul
echo "##############################################################################################"
echo "zuul"
make build run-travis-test || exit $?
cd ..

cd spymemcached/
echo "##############################################################################################"
echo "spymemcached/"
make build run-travis-test || exit $?
cd ..

cd thrift
echo "##############################################################################################"
echo "thrift"
make build run-travis-test || exit $?
cd ..
