FROM openjdk:17-jdk-slim-buster
EXPOSE 8080
COPY ./target/no-geeks-brewing-api.jar no-geeks-brewing-api.jar
ENTRYPOINT ["java","-jar","no-geeks-brewing-api.jar"]
