#!/usr/bin/env bash

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

cd redisson
echo "##############################################################################################"
echo "redisson"
make build run-travis-test || exit $?
cd ..

cd lettuce
echo "##############################################################################################"
echo "lettuce"
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

cd spring-scheduling
echo "##############################################################################################"
echo "spring-scheduling"
make build run-travis-test || exit $?
cd ..

cd zuul
echo "##############################################################################################"
echo "zuul"
make build run-travis-test || exit $?
cd ..

