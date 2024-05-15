FROM adoptopenjdk/openjdk11

WORKDIR /app

COPY ../target/ambulance-0.0.1-SNAPSHOT.jar ambulance-service.jar

ENTRYPOINT ["java", "-jar", "ambulance-service.jar"]