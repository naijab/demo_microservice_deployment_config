# Demo Micro Service with Golang

> Kubernetes Deploy

## How to run

1. Install [docker](https://www.docker.com/products/docker-desktop) and [golang](https://golang.org/dl/)
2. `cd && GO111MODULE=on go get github.com/google/ko/cmd/ko` for install ko
3. `KO_DOCKER_REPO=ko.local ko apply -f <fileName>.yaml` for set deployment
4. `kubectl create -f namespace-prod.json` for create namespace
5. `kubectl create -f namespace-dev.json` for create namespace
6. `kubectl apply -f user-service.yaml` for expose service port [http://localhost:1331](http://localhost:1331)
7. `kubectl apply -f post-service.yaml` for expose service port [http://localhost:1332](http://localhost:1332)
8. If Production Server must download config.yaml of k8s to can apply deployment

## Link

- [User Service](https://github.com/naijab/demo_microservice_user_service)
- [Post Service](https://github.com/naijab/demo_microservice_post_service)