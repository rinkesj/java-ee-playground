# Description
Simple Java EE Rest Ping project deployed on Websphere Traditional via their official docker image

There is implemented REST API Loopback.
/ping call via JAX RS Client /testPost and /testGet methods and ClientResponseFilter do logging.

# Build
mvn clean package && docker build -t com.dere/jee8test .

# RUN
docker run -d -v $(pwd)/PASSWORD:/tmp/PASSWORD -p 9043:9043 -p 9443:9443 -p 9080:9080 --name jee8test com.dere/jee8test

# LOG TAIL
docker logs -f --tail=all jee8test

# URL
http://localhost:9080/resources/ping
http://localhost:9080/resources/testPost
http://localhost:9080/resources/testGet

# RESOURCES
WAS image info: https://github.com/WASdev/ci.docker.websphere-traditional

