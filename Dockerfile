FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-alpine
COPY --from=build /target/*.jar uit-simulator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "uit-simulator.jar"]