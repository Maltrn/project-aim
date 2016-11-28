FROM meandor/ubuntu:xenial
COPY . /var/opt/project-aim
WORKDIR /var/opt/project-aim
RUN ./bin/go bootRepackage
CMD ["./run.sh"]
EXPOSE 27017
EXPOSE 8080
EXPOSE 3000