# Dockerfile for Service B

FROM eclipse-temurin:22

WORKDIR /app

COPY target/service-b-0.0.1-SNAPSHOT.jar /app/service-b.jar

# Expose the port on which your service will run
EXPOSE 8084

ENTRYPOINT ["java","-jar","service-b.jar"]