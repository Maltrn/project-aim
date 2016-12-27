FROM openjdk:8
EXPOSE 8080
COPY ./build/libs/project-aim.jar project-aim.jar
RUN mkdir -p ./src/main/resources
COPY ./src/main/resources ./src/main/resources
RUN chmod -R 777 ./src/main/resources
RUN chmod 777 project-aim.jar
USER nobody
CMD ["java", "-jar", "project-aim.jar"]
