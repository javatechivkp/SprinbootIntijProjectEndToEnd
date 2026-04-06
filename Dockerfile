FROM eclipse-temurin:17-jdk
EXPOSE 8080
ADD target/springboot-endtoend-ijtest.jar springboot-endtoend-ijtest.jar
ENTRYPOINT ["java","-jar","/springboot-endtoend-ijtest.jar"]