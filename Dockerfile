FROM java:8
LABEL maintainer="toufik1003@gmail.com"
VOLUME /tmp
EXPOSE 9091
ADD target/taskmanager-service-0.0.1-SNAPSHOT.jar taskmanager-service.jar
ENTRYPOINT ["java","-jar","taskmanager-service.jar"]