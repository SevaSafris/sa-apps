#!/usr/bin/env bash

cd spring-web
echo "spring-web"
make build run-travis-test || exit $?
cd ..

cd spring-scheduling
echo "spring-scheduling"
make build run-travis-test || exit $?
cd ..

