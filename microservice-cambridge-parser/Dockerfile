FROM openjdk:10.0.2-jre-slim
EXPOSE 8082
ADD /target/microservice-cambridge-parser-1.0-SNAPSHOT.jar microservice-cambridge-parser-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "microservice-cambridge-parser-1.0-SNAPSHOT.jar"]