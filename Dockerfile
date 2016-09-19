FROM openjdk:8
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN ./bin/go jar
CMD ["java", "-jar", "/usr/src/myapp/build/libs/projecttwomicroservice.jar"]