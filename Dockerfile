FROM openjdk:8
EXPOSE 8080
USER nobody
COPY ./build/libs/project-aim.jar project-aim.jar
CMD ["java", "-jar", "project-aim.jar"]
