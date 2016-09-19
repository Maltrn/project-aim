FROM openjdk:8
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN ./bin/go uberJar
CMD ["java", "-jar", "/usr/src/myapp/build/libs/projecttwomicroservice.jar"]