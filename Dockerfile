# Use the official Maven image as the build environment
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy your Maven POM and source code
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package

# Second stage: Create a lightweight image for running the application
FROM openjdk:17.0.1-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file from the build stage
COPY --from=build /app/target/juzzIt.jar ./juzzIt.jar

# Define the command to run the Spring Boot application
CMD ["java", "-jar", "juzzIt.jar"]
