FROM public.ecr.aws/amazoncorretto/amazoncorretto:17-amd64
EXPOSE 8080
COPY ./target/no-geeks-brewing-api.jar no-geeks-brewing-api.jar
ENTRYPOINT ["java","-jar","no-geeks-brewing-api.jar"]
