FROM openjdk:11-jdk-slim
#COPY ./target/*.jar app.jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
