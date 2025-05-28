# ---- First stage: Build the app ----
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY trackingnumber/app
RUN mvn clean package -DskipTests

# ---- Second stage: Run the app ----
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
