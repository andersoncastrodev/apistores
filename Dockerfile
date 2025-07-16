# Build stage
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia apenas o necessário primeiro
COPY pom.xml .
COPY src ./src

# Build (as chaves devem estar em src/main/resources/)
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia o JAR
COPY --from=build /app/target/*.jar app.jar

# Copia as chaves do JAR extraído (alternativa mais segura)
RUN mkdir -p /app/resources && \
    unzip -j /app/app.jar "BOOT-INF/classes/app.*" -d /app/resources/

EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
