FROM openjdk:10.0.2-jre-slim
EXPOSE 8761
ADD /target/microservice-eureka-server-1.0-SNAPSHOT.jar microservice-eureka-server-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "microservice-eureka-server-1.0-SNAPSHOT.jar"]