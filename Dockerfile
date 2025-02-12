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
COPY . /app
RUN ./gradlew build -x test

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/students
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=123456
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=create-drop
ENV SPRING_JPA_SHOWSQL=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true
ENTRYPOINT ["java", "-jar", "app.jar"]
