#!/usr/bin/env bash

cd spring-web
echo "spring-web"
make build run-travis-test || exit $?
