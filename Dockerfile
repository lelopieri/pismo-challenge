FROM openjdk:11
ADD target/pismo-technical-challenge-1.0.0.jar pismo-technical-challenge-1.0.0.jar
ENTRYPOINT ["java", "-jar", "pismo-technical-challenge-1.0.0.jar"]
EXPOSE 8080
