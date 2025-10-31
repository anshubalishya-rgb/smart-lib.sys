# Use Maven to build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run the packaged app
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/smart-library-system-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
