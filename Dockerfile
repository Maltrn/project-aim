FROM openjdk:8
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN ./bin/go uberJar
CMD ["./bin/go" "bootRun"]