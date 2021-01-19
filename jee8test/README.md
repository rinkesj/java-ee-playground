# Build
mvn clean package && docker build -t com.dere/jee8test .

# RUN

docker rm -f jee8test || true && docker run -d -p 8080:8080 -p 4848:4848 --name jee8test com.dere/jee8test 