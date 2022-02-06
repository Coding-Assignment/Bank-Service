FROM openjdk:16-alpine3.13
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bank-service.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/bank-service.jar"]
