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
# Copia o JAR (que já contém as chaves em src/main/resources/)
COPY --from=build /app/target/*.jar app.jar

# Definindo o perfil prod como padrão
ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
