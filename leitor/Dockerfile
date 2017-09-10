FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN mkdir /tmp/arquivos
ADD target/leitor-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar" ]