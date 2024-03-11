FROM maven:3.9-eclipse-temurin-21-alpine AS build
COPY /src /src
COPY pom.xml /
RUN mvn -f /pom.xml clean package

FROM eclipse-temurin:21-jdk-alpine
COPY --from=build /target/*.jar application.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "application.jar"]