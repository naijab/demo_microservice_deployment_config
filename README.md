# Demo Micro Service with Golang

> Kubernetes Deploy

## How to run

1. Install [docker](https://www.docker.com/products/docker-desktop) and [golang](https://golang.org/dl/)
2. `cd && GO111MODULE=on go get github.com/google/ko/cmd/ko` for install ko
3. `KO_DOCKER_REPO=ko.local ko apply -f <fileName>.yaml` for set deployment
4. `kubectl expose deployment <deployment-name> --type=NodePort` for expose service (for first time)
5. If Production Server must download config.yaml of k8s to can apply deployment

## Link

- [User Service](https://github.com/naijab/demo_microservice_user_service)
- [Post Service](https://github.com/naijab/demo_microservice_post_service)