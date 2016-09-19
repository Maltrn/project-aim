FROM openjdk:8
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN ./bin/go jar
CMD ["java", "-jar", "./build/libs/projecttwomicroservice.jar"]