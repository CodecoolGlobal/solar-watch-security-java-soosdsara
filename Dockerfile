# Stage 1: Build
FROM maven:3.8.1-openjdk-17 as build

WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Run
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/solar-watch-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]