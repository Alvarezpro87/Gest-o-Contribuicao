FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/gestao-contribuicao-0.0.1-SNAPSHOT.jar
VOLUME /tmp
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]