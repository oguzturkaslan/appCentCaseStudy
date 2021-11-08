FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/appCentCaseStudy.jar appCentCaseStudy.jar
ENTRYPOINT ["java","-jar","appCentCaseStudy.jar"]