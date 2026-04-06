FROM eclipse-temurin:17-jdk
EXPOSE 8080
ADD target/springboot-endtoend-ijtestlatest.jar springboot-endtoend-ijtestlatest.jar
ENTRYPOINT ["java","-jar","/springboot-endtoend-ijtestlatest.jar"]