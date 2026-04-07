FROM eclipse-temurin:21-jdk
EXPOSE 8080
ADD target/springbootexample-test.jar springbootexample-test.jar
ENTRYPOINT ["java","-jar","/springbootexample-test.jar"]