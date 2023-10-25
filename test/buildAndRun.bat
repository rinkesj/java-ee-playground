@echo off
call mvn clean package
call docker build -t com.dere.playground.ee/test .
call docker rm -f test
call docker run -d -p 9080:9080 -p 9443:9443 --name test com.dere.playground.ee/test