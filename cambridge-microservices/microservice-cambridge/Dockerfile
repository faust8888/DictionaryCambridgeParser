FROM openjdk:10.0.2-jre-slim
EXPOSE 8081
ADD /target/microservice-cambridge-1.0-SNAPSHOT.war microservice-cambridge-1.0-SNAPSHOT.war
ENTRYPOINT ["java", "-jar", "microservice-cambridge-1.0-SNAPSHOT.war"]