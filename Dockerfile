From openjdk:17-jdk-slim

WORKDIR /app

COPY target/eventos_mascotas-0.0.1-SNAPSHOT.jar app.jar

COPY Wallet /app/Wallet

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]