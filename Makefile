DOCKER_REGISTRY=registry.zanox.com

NAME_APP=canonical-stub

IMG_APP=${DOCKER_REGISTRY}/${NAME_APP}

build:
	./mvnw clean package
	docker build -f Dockerfile -t ${IMG_APP} .
