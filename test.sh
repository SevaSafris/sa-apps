#!/usr/bin/env bash

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

