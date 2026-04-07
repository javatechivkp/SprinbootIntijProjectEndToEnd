FROM eclipse-temurin:21-jdk
EXPOSE 8080
ADD target/springrealtime-ij-app.jar springrealtime-ij-app.jar
ENTRYPOINT ["java","-jar","/springrealtime-ij-app.jar"]