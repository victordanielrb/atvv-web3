# syntax=docker/dockerfile:1.7
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -B -q -Dmaven.wagon.http.retryHandler.count=3 dependency:go-offline
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -B -q -Dmaven.test.skip=true package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
