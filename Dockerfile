# Dockerfile
#FROM eclipse-temurin:17-jdk-alpine
#WORKDIR /app
#COPY build/libs/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","app.jar"]

# Stage 1: Build JAR using Gradle
FROM gradle:8.11.1-jdk17-alpine AS build
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN gradle build -x test

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
