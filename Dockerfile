# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Add a volume for logs
VOLUME /tmp

# Copy the jar file into the container
ARG JAR_FILE=target/insurance-tracker-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
