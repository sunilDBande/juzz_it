# Use the official OpenJDK 8 image as the base image
FROM openjdk:8-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy your Maven POM and source code
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN ./mvnw clean package

# Copy the compiled JAR file into the container
COPY target/juzzIt_education_project.jar .

# Define the command to run the Spring Boot application
CMD ["java", "-jar", "juzzIt_education_project.jar"]
