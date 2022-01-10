#!/usr/bin/env bash
cd beStrong_server

mvn clean
mvn install

docker-compose down && docker-compose build && docker-compose up