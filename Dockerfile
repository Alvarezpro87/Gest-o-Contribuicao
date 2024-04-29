# imagem base do Java 17
FROM openjdk:17-jdk-alpine

# Definir argumento com o caminho do arquivo .jar
ARG JAR_FILE=target/gestao-contribuicao-0.0.1-SNAPSHOT.jar

# Copiar o arquivo .jar para o container
COPY ${JAR_FILE} app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java","-jar","/app.jar"]
