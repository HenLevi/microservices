# Overview
This project demonstrates setting up two microservices using Spring Boot, Docker, and Kubernetes on a local Minikube cluster. It includes Service A and Service B, managed via an Ingress controller and secured with RBAC and network policies.

### Project Overview
- **Service A**: Retrieves Bitcoin prices in USD from a web API every minute and prints it. Every 10 minutes, it calculates and prints the average price.
                 Runs on port 8083. Endpoint: http://localhost:8083/api/fetch-btc
- **Service B**: Responds with "Hello Microsoft!" when accessed.
                 Runs on port 8084. Endpoint: http://localhost:8084/api/msg
- **Ingress Controller**: Routes traffic to `/service-A` and `/service-B` respectively.
- **RBAC**: Role-based access control is enabled for secure communication.
- **Network Policy**: Service A cannot communicate with Service B.



- ### Technologies Used
  Java 22
  Spring Boot microservices
  Maven
  Docker
  Kubernetes
  Minikube


## Cluster Setup (some of then with my root directory)
  * the command with my path but you can change accordingly
1. **Install Minikube**  
2. **Start Minikube**  
   Start the Minikube cluster with RBAC enabled:

   ```bash
   minikube start --extra-config=apiserver.authorization-mode=RBAC
   ```
   You can verify RBAC enable with command below (you see result --authorization-mode=Node,RBAC):
   ```bash
   kubectl get pod -n kube-system -l component=kube-apiserver -o yaml | Select-String authorization-mode
   ```

   Build & Deployment: Build Docker Images Navigate to each service directory and build Docker images:
   ```bash
   cd service-a
   mvn clean package
   docker build -t henlevi/service-a-image:latest .
   cd ../service-b
   mvn clean package
   docker build -t henlevi/service-b-image:latest .
   ```

  Push Images to Minikube Use Minikube’s Docker environment to build and push images:
  ```bash
  docker run -p 8083:8083 henlevi/service-a-image:latest
  docker run -p 8084:8084 henlevi/service-b-image:latest
  ```

  Deploy all files in the k8s directory. To apply them, use the following commands:
   
   ```bash
   kubectl apply -f k8s/rbac.yaml
   kubectl apply -f k8s/service-a.yaml
   kubectl apply -f k8s/service-b.yaml
   kubectl apply -f k8s/service-a-deployment.yaml
   kubectl apply -f k8s/service-b-deployment.yaml
   kubectl apply -f k8s/ingress.yaml
   kubectl apply -f k8s/network-policy.yaml
   ```

   Check ip and verify Ingress:
  ```bash
  minikube ip
  http://<minikube-ip>/service-A    
  http://<minikube-ip>/service-B 
  ```


  
  Deploy to Kubernetes Apply the Kubernetes manifests in the k8s/ folder to deploy the services and configure Ingress:

  ```bash
    kubectl apply -f k8s/
  ```

  Check Services-Verify that the services are running correctly:
  ```bash
  kubectl get pods
  kubectl get services
  ```

  Access Services via Ingress
  Open your browser and test:
  ```bash
  Service A: http://<minikube-ip>/service-A
  Service B: http://<minikube-ip>/service-B
  ```
