FROM eclipse-temurin:17.0.10_7-jre@sha256:bf1f394f748266a208ac3eb36417413c02ea277dc7d34bea2b4a6d7a99f8bccb

WORKDIR /app

ENV JAVA_ENABLE_DEBUG=${JAVA_ENABLE_DEBUG}
ENV JAR=devops-knowledge-share-api.jar

COPY entrypoint.sh .
COPY ./build/libs/${JAR} .

RUN groupadd --system appuser -g 1001 && \
    useradd --system -g appuser -u 1001 appuser && \
    mkdir -p /app/data && \
    chown -R appuser:appuser /app && \
    chown appuser:appuser ${JAR} && \
    chmod 500 ${JAR}

EXPOSE 8080
USER 1001

CMD ["./entrypoint.sh"]
