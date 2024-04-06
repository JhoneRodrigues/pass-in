# Stage de construção
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn compile -DskipTests=true

# Estágio de execução
FROM openjdk:17-jdk-alpine
ARG JAR_FILE=/app/target/*.jar
COPY --from=build ${JAR_FILE} /app/pass-in.jar
CMD ["java", "-jar", "/app/pass-in.jar"]