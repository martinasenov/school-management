FROM openjdk:17-slim

WORKDIR usr/app

COPY target/school-management-0.0.1-SNAPSHOT.jar school-management-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","school-management-0.0.1-SNAPSHOT.jar"]