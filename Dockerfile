# Use lightweight OpenJDK image (Java 25 compatible)
FROM eclipse-temurin:25-jdk-jammy

# Set working directory
WORKDIR /app

# make container lisit at a port 9090
EXPOSE 9090

# Copy jar from target folder
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]