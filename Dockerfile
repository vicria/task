FROM maven:3.9.1 as build
ENV HOME=/task
RUN mkdir -p $HOME
WORKDIR $HOME

ADD pom.xml $HOME
RUN mvn clean install

FROM openjdk:18-jdk-alpine
COPY --from=build /task/target/task-1.0.0-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "task-1.0.0-SNAPSHOT.jar"]



