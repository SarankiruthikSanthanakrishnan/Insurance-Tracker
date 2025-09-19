# Use official OpenJDK runtime
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built jar from target folder (case-insensitive using wildcard)
ARG JAR_FILE=target/*SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose Render’s default port (not strictly required but good practice)
EXPOSE 10000

# Run the jar, binding Spring Boot to Render's $PORT
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-10000} -jar /app/app.jar"]
