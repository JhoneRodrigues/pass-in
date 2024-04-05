FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/pass-in.jar

CMD ["java", "-jar", "/app/pass-in.jar"]