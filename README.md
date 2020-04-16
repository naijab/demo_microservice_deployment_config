# Demo Micro Service with Golang

> Kubernetes Deploy

## How to run

1. Install [docker](https://www.docker.com/products/docker-desktop) and [golang](https://golang.org/dl/)
2. `cd && GO111MODULE=on go get github.com/google/ko/cmd/ko` for install ko
3. `kubectl create -f https://bit.ly/k4k8s` for install kong api gateway
4. `kubectl create -f namespace.json` for create namespace for production
5. `kubectl create -f namespace-dev.json` for create namespace for dev (default namespace)
6. `KO_DOCKER_REPO=ko.local ko apply -f <fileName>.yaml` for set deployment
7. `kubectl apply -f api-gateway.yaml` for create kong api gateway controller
8. `kubectl apply -f rate-limit.yaml` for use kong rate limit plugin
9. `kubectl apply -f user-service.yaml` for expose service [http://localhost/api/user](http://localhost/api/user)
10. `kubectl apply -f post-service.yaml` for expose service [http://localhost/api/post](http://localhost/api/post))
11. If Production Server must download config.yaml of k8s to can apply deployment

## Link

- [User Service](https://github.com/naijab/demo_microservice_user_service)
- [Post Service](https://github.com/naijab/demo_microservice_post_service)