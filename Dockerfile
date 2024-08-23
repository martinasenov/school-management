FROM openjdk:17-slim

WORKDIR usr/app

COPY target/school-management-0.0.1-SNAPSHOT.jar school-management-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","school-management-0.0.1-SNAPSHOT.jar"]


#    ADD target/school-management-0.0.1-SNAPSHOT.jar /schoolManagement/school-management-0.0.1-SNAPSHOT.jar
#    RUN apt-get install curl
#    CMD echo "Hello World"
#    ENV VARIABLENAME=VALUE
#    VOLUME /var/lib/docker
