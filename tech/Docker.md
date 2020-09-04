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
# Delete all dangling images
docker image prune  
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
# List all containers
docker ps -a
# Delete container
docker rm $(container_id)
# Delete all running and stopped containers
docker container rm -f $(docker ps -aq)
# Print the last 100 lines of a container's logs
docker container logs --tail 100 web
# List networks
docker network ls
# Example  
docker run --privileged --hostname example.domain.com -name example example:1
```

**EXEC**
```bash
# create a new file in container EXAMPLE
docker exec -d EXAMPLE touch /tmp/execWorks
# execuate an interactive bash shell on the container
docker exec -it EXAMPLE bash
# set an env var in the current bash session
docker exec -it -e VAR=1 EXAMPLE bash
# select working dir for the cmd to execute into
docker exec -it -w /root EXAMPLE pwd
# execute as root user
docker exec -it -u root EXAMPLE bash
```

**CLEAN UP**  
```bash
docker volume rm $(docker volume ls -qf dangling=true)
docker network rm $(docker network ls | grep "bridge" | awk '/ / { print $1 }')
docker rmi $(docker images --filter "dangling=true" -q --no-trunc)
docker rm $(docker ps -qa --no-trunc --filter "status=exited")
```

**CHECK MEM USAGE**
```bash
docker ps -q | xargs  docker stats --no-stream
```

### Create a new Image
Create  `Dockerfile`
Cmd:
```bash
docker image build . -t helloworld
docker container run helloworld
docker run --name helloworld helloworld
```

### Docker Compose
Compose is a tool for defining and running multi-container Docker applications. With Compose, you use a YAML file to configure your application's services.  

Install docker-compoes:
```
# sudo curl -L "https://github.com/docker/compose/releases/download/1.25.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
# sudo chmod +x /usr/local/bin/docker-compose
# docker-compose --version // For testing
```

Three Steps:
1. Define `Dockerfile`
2. Define `docker-compose.yml`
3. Run `docker-compose up`
3. Stop `docker-compose down`

[Example: To build a simple Python web app running on Docker Compose](https://docs.docker.com/compose/gettingstarted/)  

[Example: Control startup and shutdown order in Compose](https://docs.docker.com/compose/startup-order/)

[Variable substitution](https://docs.docker.com/compose/compose-file/#variable-substitution)


### Create Docker Image From a Container
You have a running Contianer `helloworld`, which you can modify it after it starts.
``` 
# docker commit helloworld
# docker tag $IMAGE_ID_NONE helloworldimg 
```
Then you can start run a new container with the new image.
Also, you can change some configuration when you commit the image.
But the commit operation will not include any data contained in `volumes`.
[Ref](https://docs.docker.com/engine/reference/commandline/commit/)


### Push a docker image to a personal repository
1. In the server side, set up a registry
    ```
    # docker run -d -p 5000:5000 --restart=always --name $HOSTNAME registry:2
    ```
2. Add registry entry to daemon.json
    ```
    vi /etc/docker/daemon.json 
    ```
    Add the following to the above created file:
    ```json
    {
    "insecure-registries" : ["$HOSTNAME:5000"]
    }
    ```
3. Restart docker
    ```
    # systemctl status docker
    ```
4. Push Image
    ``` bash
    # Assuming the image called myapp:1
    docker tag myapp:1 $HOSTNAME:5000/myapp:1
    docker rmi myapp:1
    docker push $HOSTNAME:5000/myapp:1
    ```

5. Pull Image From localhost
    ```
    docker pull $HOSTNAME:5000/myapp:1
    ```

6. Pull Image From Remote Repository
    ```bash
    # Make sure you edit /etc/docker/daemon.json and restart docker
    docker pull $HOSTNAME:5000/myapp:1
    ```
[Ref](https://docs.docker.com/registry/deploying/)

### Repository Rest API
```bash
URL=$HOSTNAME:5000/v2/
# list all available repositories
$URL/_catalog
# check all tags for one repository
$URL/${REPOSITORY_NAME}/tags/list
```



### Install Docker in CentOS
```
# sudo yum remove docker docker-client docker-client-latest docker-common docker-latest docker-latest-logrotate docker-logrotate docker-selinux  docker-engine-selinux docker-engine
# sudo yum install -y yum-utils device-mapper-persistent-data lvm2
# sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
# sudo yum install docker-ce


# sudo systemctl start docker
# sudo systemctl enable docker
# sudo systemctl status docker
```

### Clean up rancher docker host
[Ref](https://www.claudiokuenzler.com/blog/674/reset-clean-up-a-rancher-docker-host)

### Build OralceDB SE image
Download oracleDB zip file from [Oracle DB Software Downloads](https://www.oracle.com/database/technologies/oracle-database-software-downloads.html)

Download docker-images from [Github repository](https://github.com/oracle/docker-images/tree/master/OracleDatabase/SingleInstance/dockerfiles)

Copy `linuxx64_12201_database.zip` into `12.2.0.1 folder`

Run Command
```
# cd ..
# ./buildDockerImage.sh -v 12.2.0.1 -s

// -e: creates image based on 'Enterprise Edition'
   -s: creates image based on 'Standard Edition 2'
   -x: creates image based on 'Express Edition'
```
OR
```
# docker build . -t 'oracle/database:12.2.0.1-se2'
```

```
docker run --name OracleSE -p 1521:1521 oraclese:12.2.0.1-se2
docker container rm OracleSE

docker exec OracleSE ./setPassword.sh oraclese
sqlplus system/oraclese@//localhost:1521/ORCLPDB1

docker stop OracleSE
docker start OracleSE

docker run --privileged --hostname domain.example.com --name App app:1
```

### TroubleShooting
**INS-30131 Error When Installing Oracle Database or Oracle Client**
```
For a client installation:

-ignorePrereq -J"-Doracle.install.client.validate.clientSupportedOSCheck=false"

For a server installation:

-ignorePrereq -J"-Doracle.install.db.validate.supportedOSCheck=false"
```

**INS-30060:Check for group existence failed**
```
Add Flag: -ignoreInternalDriverError
```

**Change Docker Base Storage Location**

Edit /etc/docker/daemon.json
Put the following content
```json
{
    "graph":"$YOUR_CUSTOM_DIR"
}
```

**RUN VS CMD**  

`RUN` is executed in the build process.  
`CMD` is executed in the running time. Only after you created a container, the cmd will be executed.

**Manage Data**  

Three types of mount, to persist data even container is stopped.  
`bind mount`: can be stored anywhere in the host system. Non-Docker or Docker process can modify them at anytime.  
`volume`: is stored in the part of filesystem which is managed by docker. Only Docker process can modify them.  
`tmpfs mount`: is stored in the memory and are never written into the filesystem.  

