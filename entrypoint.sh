#!/usr/bin/env sh

if [ "${JAVA_ENABLE_DEBUG}" = "true" ]; then
  echo "Starting with debug enabled"
  java -Xdebug -X-agentlib:jdwp=transport=dt_socket,address=0.0.0.0:${JAVA_DEBUG_PORT},server=y,suspend=n -jar ${JAR}
else
  java -jar ${JAR}
fi
