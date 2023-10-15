# FROM openjdk
#
# WORKDIR /app
#
# COPY target/api.controleservicos-0.0.1-SNAPSHOT.jar /app/api.controleservicos.jar
#
# ENTRYPOINT ["java","-jar","api.controleservicos.jar"]
# Usa uma imagem base do OpenJDK para Java 17
FROM openjdk:17

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR da sua aplicação para o diretório de trabalho no container
COPY target/api.controleservicos-0.0.1-SNAPSHOT.jar /app/api.controleservicos.jar

# Expõe a porta em que a aplicação Spring Boot vai escutar
EXPOSE 8080

# Comando para executar a aplicação Spring Boot
CMD ["java", "-jar", "api.controleservicos.jar"]