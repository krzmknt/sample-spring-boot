FROM public.ecr.aws/amazoncorretto/amazoncorretto:21
COPY ./target/sampleSpringBootCicd-0.0.1-SNAPSHOT.jar /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]
