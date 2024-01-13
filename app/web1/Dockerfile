FROM public.ecr.aws/amazoncorretto/amazoncorretto:21
COPY ./target/web1-0.0.1-SNAPSHOT.jar /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]
