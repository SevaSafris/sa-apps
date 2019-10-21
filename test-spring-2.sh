#!/usr/bin/env bash

cd spring-webflux/spring-webflux-2.1.0
echo "##############################################################################################"
echo "spring-webflux-2.1.0"
make build run-mock || exit $?
cd ../..

cd spring-webflux/spring-webflux-2.1.8
echo "##############################################################################################"
echo "spring-webflux-2.1.8"
make build run-mock || exit $?
cd ../..

cd spring-webmvc/spring-webmvc-3.0.2-servlet-2.5
echo "##############################################################################################"
echo "spring-webmvc-3.0.2-servlet-2.5"
make build run-mock || exit $?
cd ../..

cd spring-webmvc/spring-webmvc-3.0.2
echo "##############################################################################################"
echo "spring-web-3.0.2"
make build run-mock || exit $?
cd ../..

cd spring-webmvc/spring-webmvc-3.2.18
echo "##############################################################################################"
echo "spring-web-3.2.18"
make build run-mock || exit $?
cd ../..

cd spring-webmvc/spring-webmvc-4.0.0
echo "##############################################################################################"
echo "spring-webmvc-4.0.0"
make build run-mock || exit $?
cd ../..

cd spring-webmvc/spring-webmvc-4.3.24
echo "##############################################################################################"
echo "spring-webmvc-4.3.24"
make build run-mock || exit $?
cd ../..

cd spring-webmvc/spring-webmvc-5.0.0
echo "##############################################################################################"
echo "spring-webmvc-5.0.0"
make build run-mock || exit $?
cd ../..

cd spring-webmvc/spring-webmvc-5.1.9
echo "##############################################################################################"
echo "spring-webmvc-5.1.9"
make build run-mock || exit $?
cd ../..

cd spring-scheduling/spring-scheduling-1.5.22
echo "##############################################################################################"
echo "spring-scheduling-1.5.22"
make build run-mock || exit $?
cd ../..

cd spring-scheduling/spring-scheduling-2.1.8
echo "##############################################################################################"
echo "spring-scheduling-2.1.8"
make build run-mock || exit $?
cd ../..

cd zuul/zuul-2.1.0
echo "##############################################################################################"
echo "zuul-2.1.0"
make build run-mock || exit $?
cd ../..

cd zuul/zuul-2.1.8
echo "##############################################################################################"
echo "zuul-2.1.8"
make build run-mock || exit $?
cd ../..

cd spring-messaging/spring-messaging-2.1.0
echo "##############################################################################################"
echo "spring-messaging-2.1.0"
make build run-mock || exit $?
cd ../..

cd spring-messaging/spring-messaging-2.1.8
echo "##############################################################################################"
echo "spring-messaging-2.1.8"
make build run-mock || exit $?
cd ../..

cd spring-messaging-rabbit/spring-messaging-rabbit-2.1.0
echo "##############################################################################################"
echo "spring-messaging-rabbit-2.1.0"
make build run-mock || exit $?
cd ../..

cd spring-messaging-rabbit/spring-messaging-rabbit-2.1.8
echo "##############################################################################################"
echo "spring-messaging-rabbit-2.1.8"
make build run-mock || exit $?
cd ../..
