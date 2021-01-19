#!/bin/sh
mvn clean package && docker build -t com.dere/jee8test .
docker rm -f jee8test || true && docker run -d -v $(pwd)/PASSWORD:/tmp/PASSWORD -p 9043:9043 -p 9443:9443 -p 9080:9080 --name jee8test com.dere/jee8test 
