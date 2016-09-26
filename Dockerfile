FROM openjdk:8
COPY . /var/opt/projecttwo
WORKDIR /var/opt/projecttwo
RUN ./bin/go bootRepackage
ENTRYPOINT ["java","-jar","./build/libs/projecttwomicroservice.jar"]
