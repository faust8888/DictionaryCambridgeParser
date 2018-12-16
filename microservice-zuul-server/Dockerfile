FROM openjdk:10.0.2-jre-slim
EXPOSE 8083
ADD /target/microservice-zuul-server-1.0-SNAPSHOT.jar microservice-zuul-server-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "microservice-zuul-server-1.0-SNAPSHOT.jar"]