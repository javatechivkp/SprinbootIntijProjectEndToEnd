FROM eclipse-temurin:17-jdk
EXPOSE 8080
ADD target/springboot-endtoend-ijtest.war springboot-endtoend-ijtest.war
ENTRYPOINT ["java","-war","/springboot-endtoend-ijtest.war"]