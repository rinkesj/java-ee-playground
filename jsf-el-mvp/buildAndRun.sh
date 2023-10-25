#!/bin/sh
if [ $(docker ps -a -f name=jsf-el-mvp | grep -w jsf-el-mvp | wc -l) -eq 1 ]; then
  docker rm -f jsf-el-mvp
fi
mvn clean package && docker build -t com.dere.playground.ee/jsf-el-mvp .
docker run -d -p 9080:9080 -p 9443:9443 --name jsf-el-mvp com.dere.playground.ee/jsf-el-mvp
