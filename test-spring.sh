#!/usr/bin/env bash

cd spring-websocket/spring-websocket-2.1.0
echo "##############################################################################################"
echo "spring-websocket-2.1.0"
make build run-mock || exit $?
cd ../..

cd spring-websocket/spring-websocket-2.1.8
echo "##############################################################################################"
echo "spring-websocket-2.1.8"
make build run-mock || exit $?
cd ../..

cd spring-boot-rabbitmq/spring-boot-rabbitmq-2.0.0
echo "##############################################################################################"
echo "spring-boot-rabbitmq-2.0.0"
make build run-mock || exit $?
cd ../..

cd spring-boot-rabbitmq/spring-boot-rabbitmq-2.1.8
echo "##############################################################################################"
echo "spring-boot-rabbitmq-2.1.8"
make build run-mock || exit $?
cd ../..

cd spring-boot-kafka/spring-boot-kafka-2.1.0
echo "##############################################################################################"
echo "spring-boot-kafka-2.1.0"
make build run-mock || exit $?
cd ../..

cd spring-boot-kafka/spring-boot-kafka-2.1.8
echo "##############################################################################################"
echo "spring-boot-kafka-2.1.8"
make build run-mock || exit $?
cd ../..

cd spring-boot-jms/spring-boot-jms-1.5.22
echo "##############################################################################################"
echo "spring-boot-jms-1.5.22"
make build run-mock || exit $?
cd ../..

cd spring-boot-jms/spring-boot-jms-2.1.8
echo "##############################################################################################"
echo "spring-boot-jms-2.1.8"
make build run-mock || exit $?
cd ../..

cd spring-boot-data-redis/spring-boot-data-redis-1.5.22
echo "##############################################################################################"
echo "spring-boot-data-redis-1.5.22"
make build run-mock || exit $?
cd ../..

cd spring-boot-data-redis/spring-boot-data-redis-2.1.8
echo "##############################################################################################"
echo "spring-boot-data-redis-2.1.8"
make build run-mock || exit $?
cd ../..

cd spring-web/spring-web-3.0.3
echo "##############################################################################################"
echo "spring-web-3.0.3"
make build run-mock || exit $?
cd ../..

cd spring-web/spring-web-3.2.18
echo "##############################################################################################"
echo "spring-web-3.2.18"
make build run-mock || exit $?
cd ../..

cd spring-web/spring-web-4.0.0
echo "##############################################################################################"
echo "spring-web-4.0.0"
make build run-mock || exit $?
cd ../..

cd spring-web/spring-web-4.3.0
echo "##############################################################################################"
echo "spring-web-4.3.0"
make build run-mock || exit $?
cd ../..

cd spring-web/spring-web-4.3.24
echo "##############################################################################################"
echo "spring-web-4.3.24"
make build run-mock || exit $?
cd ../..

cd spring-web/spring-web-5.0.0
echo "##############################################################################################"
echo "spring-web-5.0.0"
make build run-mock || exit $?
cd ../..

cd spring-web/spring-web-5.1.9
echo "##############################################################################################"
echo "spring-web-5.1.9"
make build run-mock || exit $?
cd ../..
