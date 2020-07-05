FROM openjdk:8
EXPOSE 8076
ADD target/sentiment-analysis-spring-boot.jar sentiment-analysis-spring-boot.jar
ENTRYPOINT ["java","-jar","/sentiment-analysis-spring-boot.jar"]