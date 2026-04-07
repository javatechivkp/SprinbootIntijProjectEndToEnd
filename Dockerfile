FROM eclipse-temurin:21-jdk
EXPOSE 8080
ADD target/springbootexample-ijtest.jar springbootexample-ijtest.jar
ENTRYPOINT ["java","-jar","/springbootexample-ijtest.jar"]