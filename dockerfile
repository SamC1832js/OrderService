# Stage 1: Build the JAR
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
# Copy only the POM first to leverage layer caching
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copy the JAR from the build stage
ARG JAR_FILE=/app/target/OrderService-*.jar
COPY --from=build ${JAR_FILE} app.jar
EXPOSE 8080
# Use the default Spring Boot entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]