# Dockerfile for Service A

FROM eclipse-temurin:22

WORKDIR /app

COPY target/service-a-0.0.1-SNAPSHOT.jar /app/service-a.jar

# Expose the port on which your service will run
EXPOSE 8083

ENTRYPOINT ["java","-jar","service-a.jar"]  