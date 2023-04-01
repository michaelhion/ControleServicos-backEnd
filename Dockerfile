FROM openjdk

WORKDIR /app

COPY target/api.controleservicos-0.0.1-SNAPSHOT.jar /app/api.controleservicos.jar

ENTRYPOINT ["java","-jar","api.controleservicos.jar"]