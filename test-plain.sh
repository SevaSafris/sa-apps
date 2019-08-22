#!/usr/bin/env bash

cd cassandra
echo "##############################################################################################"
echo "cassandra"
make build run-mock || exit $?
cd ..

cd httpclient-4.2.5
echo "##############################################################################################"
echo "httpclient-4.2.5"
make build run-mock || exit $?
cd ..

cd concurrent
echo "##############################################################################################"
echo "concurrent"
make build run-mock || exit $?
cd ..

cd rxjava-2
echo "##############################################################################################"
echo "rxjava-2"
make build run-mock || exit $?
cd ..

cd rabbitmq
echo "##############################################################################################"
echo "rabbitmq"
make build run-mock || exit $?
cd ..

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

cd kafka
echo "##############################################################################################"
echo "kafka"
make build run-mock || exit $?
cd ..

cd jms-1
echo "##############################################################################################"
echo "jms-1"
make build run-mock || exit $?
cd ..

cd jms-2
echo "##############################################################################################"
echo "jms-2"
make build run-mock || exit $?
cd ..

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

cd hazelcast
echo "##############################################################################################"
echo "hazelcast"
make build run-mock || exit $?
cd ..

cd grpc
echo "##############################################################################################"
echo "grpc"
make build run-mock || exit $?
cd ..

cd elasticsearch-6
echo "##############################################################################################"
echo "elasticsearch-6"
make build run-mock || exit $?
cd ..

cd elasticsearch-7
echo "##############################################################################################"
echo "elasticsearch-7"
make build run-mock || exit $?
cd ..

cd aws
echo "##############################################################################################"
echo "aws"
make build run-mock || exit $?
cd ..

cd aws-2
echo "##############################################################################################"
echo "aws-2"
make build run-mock || exit $?
cd ..

cd jedis-2
echo "##############################################################################################"
echo "jedis-2"
make build run-mock || exit $?
cd ..

cd jedis-3
echo "##############################################################################################"
echo "jedis-3"
make build run-mock || exit $?
cd ..

cd lettuce
echo "##############################################################################################"
echo "lettuce"
make build run-mock || exit $?
cd ..

cd asynchttpclient
echo "##############################################################################################"
echo "asynchttpclient"
make build run-mock || exit $?
cd ..

cd okhttp
echo "##############################################################################################"
echo "okhttp"
make build run-mock || exit $?
cd ..

cd httpclient
echo "##############################################################################################"
echo "httpclient"
make build run-mock || exit $?
cd ..

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

cd redisson
echo "##############################################################################################"
echo "redisson"
make build run-mock || exit $?
cd ..

cd feign
echo "##############################################################################################"
echo "feign"
make build run-mock || exit $?
cd ..

cd hystrix
echo "##############################################################################################"
echo "hystrix"
make build run-mock || exit $?
cd ..
