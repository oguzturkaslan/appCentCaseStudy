FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/appCentCaseStudy-0.0.1-SNAPSHOT.jar appCentCaseStudy-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","appCentCaseStudy-0.0.1-SNAPSHOT.jar"]