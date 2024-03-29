FROM eclipse-temurin:17.0.10_7-jre@sha256:417f902587c5611dec69165d6b4059ca97d2a9f7f9d9955c100440a160b3bab2

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
