FROM openjdk:17-slim

WORKDIR /usr/app

COPY target/school-management-0.0.1-SNAPSHOT.jar school-management-0.0.1-SNAPSHOT.jar

COPY init-db.sh /docker-entrypoint-initdb.d/init-db.sh

COPY src/main/resources/data.sql /docker-entrypoint-initdb.d/data.sql

RUN chmod +x /docker-entrypoint-initdb.d/init-db.sh

ENTRYPOINT ["java","-jar","school-management-0.0.1-SNAPSHOT.jar"]


#    ADD target/school-management-0.0.1-SNAPSHOT.jar /schoolManagement/school-management-0.0.1-SNAPSHOT.jar
#    RUN apt-get install curl
#    CMD echo "Hello World"
#    ENV VARIABLENAME=VALUE
#    VOLUME /var/lib/docker
