FROM maven:3.9.6-eclipse-terumin-17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests
FROM eclipse-terumin-17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
Expose 8080
ENTRYPOINT ["java","-jar","app.jar"]
