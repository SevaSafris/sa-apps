#!/usr/bin/env bash

cd jedis-2
echo "##############################################################################################"
echo "jedis-2"
make build run-travis-test || exit $?
cd ..

cd asynchttpclient
echo "##############################################################################################"
echo "asynchttpclient"
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

cd feign
echo "##############################################################################################"
echo "feign"
make build run-travis-test || exit $?
cd ..