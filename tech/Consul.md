# Consul
## Introduction
Consul is a tool for discovering and configuring services in your infrastructure. Consul's key feature include service discovery, health checking, a KV store, and robust support for multi-datacenter deployments.  
Nomad's integration with Consul enables automatic clustering, built-in service registration, and dynamic rendering of configuration files and environment variables.  

Nomad servers and clients will be automatically informed of each other's existence when a running Consul cluster already exists and the Consul agents is installed and configured on each host.  

**Bootstrapping a Datacenter**
An agent can run in either client or server mode. Server nodes are responsible for running the `consensus protocol` and storing the cluster state. The client nodes are mostly stateless and rely heavily on the server nodes.  
Before a Consul cluster can begin to service requests, a server node must be elected leader. Bootstrapping is the process of joining these initial server nodes into a cluster.  
It is recommended to have 3 or 5 total servers per datacenter. A single server deployment is highly discouraged as data loss is inevitable in a failure scenario.  

**Service Discovery**
Consul provides a DNS interface the downstream services can use to find the IP addresses of their upstream dependencies.  
Consul knows where these services are located because each service registers with its local Consul client.  
In a multi-agent Consul datacenter, each service would register with its local Consul client, and the clients would forward the registration to the Consul servers, which maintain the service catalog.  
If you wanted to register multiple services, you could create multiple service definition files in the Consul configuration directory.  

**Integration With Nomad**
Nomad integrates with Consul to provide secure service-to-service communication between Nomad jobs and task groups. Nomad adds a new networking mode for jobs that enables tasks in the same task group to share their networking stack. When Connect is enabled, Nomad will launch a proxy alongside the application in the job file. The proxy provides secure communication with other applications in the clsuter.  

## Detailed Introduction
[REF](https://thenewstack.io/implementing-service-discovery-of-microservices-with-consul/)  
Consul from HashiCorp is one of the early implementations of service mesh technology. It comes with a full-featured control plane with service discovery, configuration, and segmentation functionality. The best thing about Consul is the support for a variety of environments including traditional applications, VMs, containers, and orchestration engines such as Nomad and Kubernetes.  

At a high level, Consul provides the below functionality:

**Distributed Architecture**: Consul supports multiple data centers and for delivering a scalable, highly available, abstract, and resilient service mesh infrastructure. A Consul deployment may span multiple zones or even regions in a public cloud environment.  
**Service Discovery**: Distributed applications can use Consul to dynamically discover service endpoints. Once a service is registered with Consul, it can be discovered using typical DNS or custom API.  
**Health Checking**: Consul can track and report the health of registered services. Each microservice can provide an endpoint that Consul probes to check the health. This is useful in implementing patterns such as circuit-breaking.  
**Key/Value Store**: Consul is one of the popular distributed, in-memory KV store. Applications and infrastructure can utilize to store hierarchical data that’s accessible to any participating service. A simple HTTP API exposes the read/write functionality to deal with the data.  
**Secure Communication**: Like most of the service mesh implementations, Consul enables implicit communication security for services through mTLS. The traffic between the proxies/sidecars is automatically encrypted without the need to code or configure.  


## Installation
`curl -O https://releases.hashicorp.com/consul/1.8.0/consul_1.8.0_linux_amd64.zip`  
Add into PATH  