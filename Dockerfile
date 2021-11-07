FROM openjdk:8-jdk-alpine
COPY out/artifacts/appCentCaseStudy_jar/appCentCaseStudy.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]