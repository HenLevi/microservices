# microservices
Spring Boot Microservices with Minikube, Kubernetes and Docker

# Overview
This project presents a production-ready microservices application built with Spring Boot and deployed on a local Kubernetes cluster using Minikube. The application consists of two services:

  # Service A:
  Fetches the Bitcoin price in USD from an external API every minute and calculates the average of bitcoin price every 10 minutes.
  
  # Service B: Print "Hello Microsoft!".
  The services are deployed with the following requirements:

# Prerequisites
Ensure you have the following installed:

Minikube: Installation Guide.
kubectl:  Installation Guide.
Docker:   Installation Guide.
Postman.
I worked with Java 22 and Maven for building the Spring Boot MicroServices.

# step:


Open Your Terminal: 
Step 1: Start Minikube with RBAC enabled
* Run the Command(this can be done from any directory in your system):
  minikube start --extra-config=apiserver.Authorization.Mode=RBAC

* Run the Command(check status):
  minikube status

Step 2: Enable Ingress
* Run the Command(Enable the NGINX ingress controller in Minikube to route traffic):
  minikube addons enable ingress


  


