# Stage 1: Build
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the project files to the container
COPY pom.xml ./
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Expose the port Spring Boot runs on
EXPOSE 8080

# Copiar el archivo JAR al contenedor
COPY --from=build /app/target/klv-pu-back-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

