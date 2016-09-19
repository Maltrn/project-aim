FROM openjdk:8
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN ./bin/go uberJar
ENTRYPOINT ["./bin/go","bootRun"]