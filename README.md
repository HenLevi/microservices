# Overview
This project demonstrates a simple microservices architecture deployed on Kubernetes using Minikube for local development.

Service A (Port 8083):  Fetches the Bitcoin price in USD from an external API every minute and calculates the average of bitcoin price every 10 minutes.
Endpoint: /api/fetch-btc
                        
Service B (Port 8084): Print "Hello Microsoft!".
Endpoint: /api/msg

# Prerequisites
Minikube (with RBAC enabled)
Kubernetes CLI (kubectl)
Docker (with a Docker Hub account)
Java 22 and Maven



# step:
run command :
Step 1: Clone the Repository
git clone https://github.com/HenLevi/microservices.git
cd microservices

Step 2: Build the Spring Boot Applications
mvn clean install for service A and service B

Kubernetes Cluster Setup


Step 1: Start Minikube with RBAC enabled
* Run the Command(this can be done from any directory in your system):
  minikube start --extra-config=apiserver.Authorization.Mode=RBAC

* Run the Command(check status):
  minikube status

Step 2: Enable Ingress
* Run the Command(Enable the NGINX ingress controller in Minikube to route traffic):
  minikube addons enable ingress




  


