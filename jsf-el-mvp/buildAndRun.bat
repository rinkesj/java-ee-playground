@echo off
call mvn clean package
call docker build -t com.dere.playground.ee/jsf-el-mvp .
call docker rm -f jsf-el-mvp
call docker run -d -p 9080:9080 -p 9443:9443 --name jsf-el-mvp com.dere.playground.ee/jsf-el-mvp