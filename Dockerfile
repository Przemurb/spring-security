FROM openjdk:jdk-slim-buster
EXPOSE 8080
COPY target/spring-security-0.0.1-SNAPSHOT.jar /app.jar
CMD java -jar /app.jar
