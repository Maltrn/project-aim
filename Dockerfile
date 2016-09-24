FROM openjdk:8
RUN ./bin/go bootRepackage
COPY ./build/libs/projecttwomicroservice.jar /var/opt/projecttwo
WORKDIR /var/opt/projecttwo
ENTRYPOINT ["java","-jar","./projecttwomicroservice.jar"]