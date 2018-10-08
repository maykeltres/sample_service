#!/bin/sh

HOST="$1"
PORT="$2"

while ! nc -z "$HOST" "$PORT" ; do
    echo ">>> WAITING TO START SERVICE $HOST:$PORT ! >>>"
    sleep 10
done

java $JAVA_OPTS $SPRING_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar