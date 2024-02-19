ARG SERVICE_NAME=users-service
FROM openjdk:21-ea-slim AS JDK

ARG SERVICE_NAME

WORKDIR /app/${SERVICE_NAME}

COPY ./build.gradle.kts /app
COPY ./${SERVICE_NAME} .

COPY ./${SERVICE_NAME}/gradle ./gradle
COPY ./${SERVICE_NAME}/gradlew .
COPY ./${SERVICE_NAME}/build.gradle.kts .

RUN ./gradlew clean && ./gradlew build -x test -build-cache --parallel

COPY ./${SERVICE_NAME}/src ./src

RUN ./gradlew clean && ./gradlew build -x test

FROM openjdk:21-ea-slim

ARG SERVICE_NAME

WORKDIR /app

RUN mkdir ./logs

COPY --from=JDK /app/${SERVICE_NAME}/build/libs/users-service-0.1.0-alpha-1.jar .

ENV PORT=8001

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "users-service-0.1.0-alpha-1.jar"]
