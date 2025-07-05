# Step 1: Use an official OpenJDK base image
FROM openjdk:17-jdk-slim as base

# Step 2: Add the JAR file to the container
ARG JAR_FILE=target/coreproject-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Step 3: Expose the application port (Spring Boot default is 8080)
EXPOSE 8080

# Step 4: Define the command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]
