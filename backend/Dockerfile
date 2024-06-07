# Etapa de build
FROM azul/zulu-openjdk-alpine:17 AS build
WORKDIR /app

# Copia todos os arquivos do projeto para o diretório de trabalho do contêiner
COPY backend /app/backend

# Define o diretório de trabalho para a pasta backend
WORKDIR /app/backend

# Garante que o script Maven Wrapper tem permissões de execução
RUN chmod +x ./mvnw

# Executa o Maven Wrapper para limpar e empacotar o projeto
RUN ./mvnw clean package -DskipTests

# Etapa de execução
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copia o arquivo JAR gerado na etapa de build para o diretório de trabalho do contêiner de execução
COPY --from=build /app/backend/target/gestao-contribuicao-0.0.1-SNAPSHOT.jar app.jar

# Define o comando de entrada para executar o aplicativo JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
