FROM openjdk:17-jdk-slim AS base

ARG JAR_FILE=target/coreproject-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
