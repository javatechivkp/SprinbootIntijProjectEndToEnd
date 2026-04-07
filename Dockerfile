# Use Java 21
FROM eclipse-temurin:21-jdk

# Set working directory inside container
WORKDIR /app

# Expose default Spring Boot port
EXPOSE 8080

# Copy WAR file from target folder to container, rename to app.war
COPY target/springapp.war app.war

# Run WAR with java -jar (do NOT use -war)
ENTRYPOINT ["java", "-jar", "app.war"]