#!/bin/sh
if [ $(docker ps -a -f name=test | grep -w test | wc -l) -eq 1 ]; then
  docker rm -f test
fi
mvn clean package && docker build -t com.dere.playground.ee/test .
docker run -d -p 9080:9080 -p 9443:9443 --name test com.dere.playground.ee/test
