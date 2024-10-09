# Overview
This project demonstrates setting up two microservices using Spring Boot, Docker, and Kubernetes on a local Minikube cluster. It includes Service A (retrieving Bitcoin prices) and Service B (printing a message), managed via an Ingress controller and secured with RBAC and network policies.

### Project Overview
- **Service A**: Retrieves Bitcoin prices in USD from a web API every minute and prints it. Every 10 minutes, it calculates and prints the average price.
- **Service B**: Responds with "Hello Microsoft!" when accessed.
- **Ingress Controller**: Routes traffic to `/service-A` and `/service-B` respectively.
- **RBAC**: Role-based access control is enabled for secure communication.
- **Network Policy**: Service A cannot communicate with Service B.
- **Liveness & Readiness Probes**: Ensures pod health and readiness for both services.

- ### Services
- **Service A**: Runs on port 8083. Endpoint: /api/fetch-btc
- **Service B**: Runs on port 8084. Endpoint: /api/msg

## Cluster Setup

1. **Install Minikube**  
   Follow the instructions from the official [Minikube documentation](https://minikube.sigs.k8s.io/docs/start/) to install and set it up locally.

2. **Start Minikube**  
   Start the Minikube cluster with RBAC enabled:

   ```bash
   minikube start --extra-config=apiserver.authorization-mode=RBAC
   ```
   You can verify RBAC enable with command below (you see result --authorization-mode=Node,RBAC):
   ```bash
   kubectl get pod -n kube-system -l component=kube-apiserver -o yaml | Select-String authorization-mode
   ```
   
   Deploy Services and Ingress Controller The Kubernetes manifests are included in the k8s directory. To apply them, use the following commands:

   ```bash
   kubectl apply -f k8s/namespace.yaml
   kubectl apply -f k8s/service-a.yaml
   kubectl apply -f k8s/service-b.yaml
   kubectl apply -f k8s/ingress-controller.yaml
   kubectl apply -f k8s/network-policy.yaml
   ```

Verify Ingress After deploying the Ingress, you can access the services by opening a browser and navigating to:
```bash
http://<minikube-ip>/service-A for Service A
http://<minikube-ip>/service-B for Service B
```

To find your Minikube IP, run:

```bash
minikube ip
```


Build & Deployment
Build Docker Images Navigate to each service directory and build Docker images:
```bash
cd service-a
mvn clean package
docker build -t service-a:latest .
```


```bash
cd ../service-b
mvn clean package
docker build -t service-b:latest .
```

Push Images to Minikube Use Minikube’s Docker environment to build and push images:

 ```bash
eval $(minikube docker-env)
docker build -t service-a:latest service-a/
docker build -t service-b:latest service-b/
```
Deploy to Kubernetes Apply the Kubernetes manifests in the k8s/ folder to deploy the services and configure Ingress:

 ```bash
kubectl apply -f k8s/
```


Check Services
Verify that the services are running correctly:

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
Logs
To view logs for Service A (Bitcoin retrieval):

```bash
kubectl logs <service-a-pod-name>
```







  


