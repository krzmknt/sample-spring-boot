FROM public.ecr.aws/amazoncorretto/amazoncorretto:21
ARG MODULE_NAME
COPY ./${MODULE_NAME}/target/${MODULE_NAME}.jar /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]
