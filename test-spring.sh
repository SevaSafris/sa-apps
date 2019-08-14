#!/usr/bin/env bash

# spring_clouds=( spring-cloud-greenwich spring-cloud-finchley spring-cloud-edgware )

spring_clouds=(spring-cloud-greenwich)

cd spring-websocket
for spring_cloud in "${spring_clouds[@]}"; do
  echo "##############################################################################################"
  echo "spring-websocket-${spring_cloud}"
  make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd spring-boot-rabbitmq
for spring_cloud in "${spring_clouds[@]}"; do
  echo "##############################################################################################"
  echo "spring-boot-rabbitmq-${spring_cloud}"
  make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

echo "install and run kafka"
wget http://www-us.apache.org/dist/kafka/2.2.1/kafka_2.12-2.2.1.tgz
tar xzf kafka_2.12-2.2.1.tgz
kafka_2.12-2.2.1/bin/zookeeper-server-start.sh -daemon kafka_2.12-2.2.1/config/zookeeper.properties
sleep 5
kafka_2.12-2.2.1/bin/kafka-server-start.sh -daemon kafka_2.12-2.2.1/config/server.properties
sleep 5

cd spring-boot-kafka
for spring_cloud in "${spring_clouds[@]}"; do
  echo "##############################################################################################"
  echo "spring-boot-kafka-${spring_cloud}"
  make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

echo "run artemis"
docker run --name artemis -p 61616:61616 -d vromero/activemq-artemis

cd spring-boot-jms
for spring_cloud in "${spring_clouds[@]}"; do
  echo "##############################################################################################"
  echo "spring-boot-jms-${spring_cloud}"
  make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd spring-data-redis
for spring_cloud in "${spring_clouds[@]}"; do
  echo "##############################################################################################"
  echo "spring-data-redis-${spring_cloud}"
  make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd spring-web
for spring_cloud in "${spring_clouds[@]}"; do
  echo "##############################################################################################"
  echo "spring-web-${spring_cloud}"
  make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd spring-webmvc
for spring_cloud in "${spring_clouds[@]}"; do
  echo "##############################################################################################"
  echo "spring-webmvc-${spring_cloud}"
  make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd spring-webflux
for spring_cloud in "${spring_clouds[@]}"; do
  echo "##############################################################################################"
  echo "spring-webflux-${spring_cloud}"
  make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd spring-3
echo "##############################################################################################"
echo "spring-3"
make build run-mocktracer-test || exit $?
cd ..

cd spring-3-servlet-2.5
echo "##############################################################################################"
echo "spring-3-servlet-2.5"
make build run-mocktracer-test || exit $?
cd ..

cd spring-4
echo "##############################################################################################"
echo "spring-4"
make build run-mocktracer-test || exit $?
cd ..

cd spring-scheduling
for spring_cloud in "${spring_clouds[@]}"; do
  echo "##############################################################################################"
  echo "spring-scheduling-${spring_cloud}"
  make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd zuul
for spring_cloud in "${spring_clouds[@]}"; do
  echo "##############################################################################################"
  echo "zuul-${spring_cloud}"
  make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd spring-messaging
for spring_cloud in "${spring_clouds[@]}"; do
  echo "##############################################################################################"
  echo "spring-messaging-${spring_cloud}"
  make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..

cd spring-messaging-rabbit
for spring_cloud in "${spring_clouds[@]}"; do
  echo "##############################################################################################"
  echo "spring-messaging-rabbit-${spring_cloud}"
  make build-${spring_cloud} run-mocktracer-test || exit $?
done
cd ..
