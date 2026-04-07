FROM eclipse-temurin:21-jdk
EXPOSE 8080
COPY target/springapp.war springapp.war
ENTRYPOINT ["java", "-jar", "springapp.war"]