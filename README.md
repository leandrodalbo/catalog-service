# Catalog Service

Project to refresh different spring-boot concepts and explore.

## build image

```bash
$ ./gradlew bootBuildImage
```

## run container

```bash
$ docker run --rm --name catalog-service -p 8080:8080 catalog-service:0.0.1-SNAPSHOT
```

## run on minikube

### start cluster
```bash
$ minikube start 
```

### check cluster
```bash
$ minikube status 
```

### load image
```bash
$ minikube image load catalog-service:0.0.1-SNAPSHOT
```

### create deployment
```bash
$ kubectl create deployment catalog --image=catalog-service:0.0.1-SNAPSHOT
```

### expose deployment
```bash
$ kubectl expose deployment catalog --name=catalog-service --port=8080
```

### port-forward
```bash
$ kubectl port-forward service/catalog-service 8000:8080
```

### check everything is up and running
```bash
$ curl http://127.0.0.1:8000/
$ kubectl get deployment
$ kubectl get pod
$ kubectl get service
```

### clean-up
```bash
$ $ kubectl delete service catalog-service
$ kubectl delete deployment catalog
$ minikube stop
```