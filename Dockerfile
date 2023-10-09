FROM maven:3.6.3-jdk-8 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:18-ea-8-jdk-slim
COPY --from=build /target/juzzIt_education_project-0.0.1-SNAPSHOT.jar juzzIt_education_project.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","juzzIt_education_project.jar"]   