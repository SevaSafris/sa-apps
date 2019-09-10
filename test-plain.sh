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

cd mongo/mongo-3.9.0
echo "##############################################################################################"
echo "mongo-3.9.0"
make build run-mock || exit $?
cd ../..

cd mongo/mongo-3.11.0
echo "##############################################################################################"
echo "mongo-3.11.0"
make build run-mock || exit $?
cd ../..

cd mongo/mongo-async-3.9.0
echo "##############################################################################################"
echo "mongo-async-3.9.0"
make build run-mock || exit $?
cd ../..

cd mongo/mongo-async-3.11.0
echo "##############################################################################################"
echo "mongo-async-3.11.0"
make build run-mock || exit $?
cd ../..

cd mongo/mongo-reactive-1.8.0
echo "##############################################################################################"
echo "mongo-reactive-1.8.0"
make build run-mock || exit $?
cd ../..

cd mongo/mongo-reactive-1.12.0
echo "##############################################################################################"
echo "mongo-reactive-1.12.0"
make build run-mock || exit $?
cd ../..

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
