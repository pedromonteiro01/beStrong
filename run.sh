#!/usr/bin/env bash
cd beStrong_server

docker-compose down && docker-compose build && docker-compose up