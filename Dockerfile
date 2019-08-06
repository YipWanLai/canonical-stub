FROM registry.zanox.com/awin-docker-base-images/awin-alpine-oraclejdk8:latest

MAINTAINER Team Blue "blue@awin.com"
COPY target/canonical-*.jar /srv/canonical.jar

WORKDIR /srv

RUN mkdir -p /srv/log/canonical

EXPOSE 80
EXPOSE 8080

CMD ["java", "-jar", "canonical.jar"]
