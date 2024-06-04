FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/*.jar
WORKDIR /tmp
COPY ./target/solar-watch-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]