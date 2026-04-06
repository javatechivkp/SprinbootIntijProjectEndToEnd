FROM eclipse-temurin:17-jdk
EXPOSE 8080
ADD target/springboot-endtoend-IJtest.war springboot-endtoend-IJtest.war
ENTRYPOINT ["java","-war","/springboot-endtoend-IJtest.war"]