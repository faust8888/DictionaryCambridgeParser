FROM openjdk:10.0.2-jre-slim
EXPOSE 8888
ADD /target/microservice-config-server-1.0-SNAPSHOT.jar microservice-config-server-1.0-SNAPSHOT.jar
ADD /cambridge-config/ Users/ilyamerkurev/IdeaProjects/cambridge-config
RUN ["apt-get", "update"]
RUN ["apt-get", "install", "-y", "vim-tiny"]
ENTRYPOINT ["java", "-jar", "microservice-config-server-1.0-SNAPSHOT.jar"]