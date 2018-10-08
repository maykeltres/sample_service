FROM registry.alpha.cloud.neogrid.com/neogrid/openjdk:8-jre-alpine

LABEL maintainer="Marcelo Tocchetto <marcelo.tocchetto@neogrid.com>"

#Variável pode ser sobreescrita
ENV SPRING_PROFILES_ACTIVE="docker"

#Variável pode ser sobreescrita
ENV JAVA_OPTS="-Xmx768m -Xms768m"

ENV SPRING_OPTS=" "

RUN apk --no-cache add netcat-openbsd

COPY /target/microservice-sample.jar /app.jar

COPY /entrypoint.sh /entrypoint.sh

RUN sh -c 'chmod +x /entrypoint.sh'

RUN sh -c 'touch /app.jar'

ENTRYPOINT exec java $JAVA_OPTS $SPRING_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar