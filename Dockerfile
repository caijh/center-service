FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl && echo "Asia/Shanghai" > /etc/timezone
ARG JAR_FILE
COPY target/${JAR_FILE} app.jar
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"
ENV APP_OPTS=""
ENTRYPOINT ["java","-jar", "$JAVA_OPTS","/app.jar", "$APP_OPTS"]
