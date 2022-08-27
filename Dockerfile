FROM maven:3.8.5-openjdk-17 AS build

ADD ./pom.xml pom.xml
ADD ./src src/

RUN mvn clean package

FROM openjdk:17-alpine
COPY --from=build target/frog-1.0-SNAPSHOT-jar-with-dependencies.jar application.jar

CMD java -jar application.jar

