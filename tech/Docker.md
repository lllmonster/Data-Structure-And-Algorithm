## Docker

### Common Command
**BUILD**
```bash
# Build Image and tag
docker build -t myimage:1.0
# List all images
docker image ls
# Delete an image form local store
docker image rm alpine:3.4
```
**RUN**
```bash
# Run a container, image=Alpine version 3.9, name=web, expose port=5000, map to port 80
docker container run --name web -p 5000:80 alpine:3.9
# Stop a running container
docker container stop web
docker container kill web
# List running containers
docker container ls
# Delete all running and stopped containers
docker container rm -f $(docker ps -aq)
# Print the last 100 lines of a container's logs
docker container logs --tail 100 web
# List networks
docker network ls
```

### Create a new Image
Create  `Dockerfile`
Cmd:
```bash
docker image build . -t helloworld
docker container run helloworld
```

### Docker Compose
Compose is a tool for defining and running multi-container Docker applications.

Three Steps:
1. Define `Dockerfile`
2. Define `docker-compose.yml`
3. Run `docker-compose up`
3. Stop `docker-compose down`

[Example: To build a simple Python web app running on Docker Compose](https://docs.docker.com/compose/gettingstarted/)  

[Example: Control startup and shutdown order in Compose](https://docs.docker.com/compose/startup-order/)

[Variable substitution](https://docs.docker.com/compose/compose-file/#variable-substitution)
