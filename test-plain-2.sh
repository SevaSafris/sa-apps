#!/usr/bin/env bash

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
