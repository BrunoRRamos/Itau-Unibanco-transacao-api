FROM openjdk:21-jdk AS builder

WORKDIR /app

COPY gradle gradlew gradlew.bat gradle build.gradle.kts settings.gradle.kts ./

COPY src ./src

RUN chmod +x gradlew

RUN ./gradlew clean build

FROM openjdk:21-jdk-slim

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
