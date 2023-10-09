# First stage: Build the application
FROM openjdk:8-jdk-alpine AS build

# Set the working directory in the container
WORKDIR /app

# Copy your Maven POM and source code
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN ./mvnw clean package

# Second stage: Create a lightweight image for running the application
FROM openjdk:8-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file from the build stage
COPY --from=build /app/target/juzzIt_education_project.jar /app/juzzIt_education_project.jar

# Define the command to run the Spring Boot application
CMD ["java", "-jar", "juzzIt_education_project.jar"]
