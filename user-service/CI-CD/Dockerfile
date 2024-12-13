ARG AND_DOCKER_PROXY_REGISTRY_NAME
ARG BUILDER_IMAGE
ARG APP_IMAGE
# build stage
FROM $AND_DOCKER_PROXY_REGISTRY_NAME/$BUILDER_IMAGE AS builder
ARG MAVEN_CLI_OPTS
WORKDIR /app
COPY . .
RUN mvn clean install $MAVEN_CLI_OPTS -Dmaven.test.skip

# Start with a base Java image
FROM openjdk:17-jdk-alpine

#app package stage
FROM $AND_DOCKER_PROXY_REGISTRY_NAME/$APP_IMAGE
RUN apk add curl && rm -rf /var/cache/apk/*
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
WORKDIR /app
RUN chown -R javauser:javauser /app /tmp
USER javauser
CMD ["java", "-Xms64m", "-Xmx256m", "-jar", "/app/app.jar"]
