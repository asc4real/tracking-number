# Use a lightweight JDK base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy built jar from target folder into the container
COPY target/*.jar app.jar

# Set environment variable for port
ENV PORT 8080

# Expose port
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "app.jar"]
