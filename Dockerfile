FROM eclipse-temurin:17-jdk
EXPOSE 8080
ADD target/realtime-ij-app.jar realtime-ij-app.jar
ENTRYPOINT ["java","-jar","/realtime-ij-app.jar"]