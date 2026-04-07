FROM eclipse-temurin:21-jdk
EXPOSE 8080
ADD target/springapp.war springapp.war.war
ENTRYPOINT ["java", "-jar", "springapp.war"]