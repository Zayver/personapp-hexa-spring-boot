# SET DOCKER_BUILDKIT=1 to avoid redownloading maven dependencies
FROM maven:latest as MAVEN_BUILD
COPY ./ ./
RUN --mount=type=cache,target=/root/.m2 mvn clean package
COPY */target/*.jar ./

FROM eclipse-temurin:latest
WORKDIR /app
COPY --from=MAVEN_BUILD /*.jar /app
COPY --from=MAVEN_BUILD */target/*.yml /app
EXPOSE 8080
CMD ["java", "-jar", "rest-input-adapter-0.0.1-SNAPSHOT.jar"]