FROM openjdk:8-jdk-alpine

VOLUME [ "/tmp" ]
ENV JAVA_OPTS=""

COPY target/suricato-*.war app.war

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.war"]