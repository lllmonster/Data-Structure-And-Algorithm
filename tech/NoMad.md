# NoMAD
## Introduction
### Automatic Clustering with Consul
Nomad servers and clients will be automatically informed of each other’s existence when a running Consul cluster already exists and the Consul agent is installed and configured on each host.


## Quick Start
### How to check if nomad is running
`nomad node status`
### Check how many jobs are running
`nomad status`
### Delete dead/stopped jobs
`curl --request PUT localhost:4646/v1/system/gc`

## Installation
```
sudo yum install -y yum-utils
sudo yum-config-manager --add-repo https://rpm.releases.hashicorp.com/RHEL/hashicorp.repo
sudo yum -y install nomad
#verify
nomad
```

## Start a Nomad Agent
```
sudo nomad agent -dev
```

## Discover agent information
```
nomad node status
nomad server members
```

## Jobs
```
nomad job init
nomad job run example.nomad
nomad status example
nomad alloc status 8ba85cef
nomad alloc logs 8ba85cef redis
```

## Modify a Job
```
nomad job plan example.nomad
nomad job run -check-index 7 example.nomad
```

## Stop a Job
```
nomad job stop example
```

## Clustering
[Ref](https://learn.hashicorp.com/nomad/getting-started/cluster)
```
vim server.hcl
nomad agent -config server.hcl
nomad agent -config client1.hcl
nomad agent -config client2.hcl
```

## Web UI
`http:localhost:4646`

## Job Specification
Format
```
job
    group
        task
```
Each job file has only a single job.
A job have multiple groups.
Each group may have multiple tasks.
All tasks within a group will be placed on the same host.

## Access Application Logs for Troubleshooting
```
nomad alloc logs 8ba85cef
nomad alloc logs 8ba85cef <task>
nomad alloc logs -f 8ba85cef
nomad alloc logs -tail -n 25 8ba85cef
nomad alloc logs -stderr 8ba85cef
```
## Security (mTLS)
[Nomad-official-tutorial](https://learn.hashicorp.com/tutorials/nomad/security-enable-tls)  
Nomad optionally uses mutual TLS (mTLS) for all HTTP and RPC communication. Nomad's use of mTLS provides the following properties:  
* Prevent unauthorized Nomad access  
* Prevent observing or tampering with Nomad communication  
* Prevent client/server role or region misconfigurations  
* Prevent other services from masquerading as Nomad agents  

While most uses of TLS verify the identity of the server role you are connecting to based on a domain name such as example.com, Nomad verifies the node you are connecting to is in the expected region and configured for the expected role (eg. client.us-west.nomad). This also prevents other services who may have access to certificates signed by the same private CA from masquerading as Nomad agents. If certificates were identified based on hostname/IP then any other service on a host could masquerade as a Nomad agent.  
### Steps
Creating Certificates  
In order to prevent unauthorized cluster access, Nomad requires all certificates be signed by the same Certificate Authority (CA). This should be a private CA and not a public one.  
`Nomad certificates may be signed by intermediate CAs as long as the root CA is the same. Append all intermediate CAs to the cert_file.`  
There are a variety of tools for managing your own CA:  
* The PKI sercret backend in Vault  
* cfssl command tool
  
For example, using cfssl to generate the CA's private key and certificate  
```
cfssl print-defaults csr | cfssl gencert -initca - | cfssljson -bare nomad-ca
```  
The CA key (nomad-ca-key.pem) will be used to sign certificates for Nomad nodes and must be kept private. The CA certificate (nomad-ca.pem) contains the public key necessary to validate Nomad certificates and therefore must be distributed to every node that requires access.  

Once you have a CA certificate and key you can generate and sign the certificates Nomad will use directly. TLS certificates commonly use the fully-qualified domain name of the system being identified as the certificate's Common Namd (CN). However, hosts are often ephemeral in Nomad clusters. Not only would signing a ner certificate per Nomad node be difficult, but using a hostname provides no security or functional benefits to Nomad. To fulfill security properties Nomad certificates are signed with their region and role such as:  
* `client.global.nomad` for a client node in the `global` region  
* `server.us-west.nomad` for a server node in the `us-west` region  

After all commands in the tutorial, you should get the following files:  
* cfssl.json - cfssl configuration  
* nomad-ca.csr - CA signing request  
* nomad-ca-key.pem - CA private key. (keep safe)  
* nomad-ca.pem - CA public certificate  
* cli.csr - Nomad CLI certificate signing request  
* cli-key.pem - Nomad CLI private key  
* cli.pem - Nomad CLI certificate  
* client.csr - Nomad client node certificate signing request  
* client-key.pem - Nomad client node private key  
* client.pem - Nomad client node public certificate  
* server.csr - Nomad server node certificate signing request  
* server-key.pem - Nomad server node private key  
* server.pem - Nomad server node public certificate  

Each Nomad node should have the appropriate key (-key.pem) and certificate (.pem) file for its region and role. In addition each node needs the CA's public certificate (nomad-ca.pem).  

## Deployment Nomad as System Service
[Ref](https://www.nomadproject.io/docs/install/production/deployment-guide)
```
# Download Nomad Binary
# Install Nomad
sudo chown root:root nomad
sudo mv nomad /usr/local/bin
nomad version
# nomad -autocomplete-install
sudo touch /etc/systemd/system/nomad.service
sudo mkdir -p /etc/nomad.d
sudo chmod 700 /etc/nomad.d
sudo touch /etc/nomad.d/nomad.hcl
sudo touch /etc/nomad.d/server.hcl
sudo touch /etc/nomad.d/client.hcl
sudo systemctl enable nomad
sudo systemctl start nomad
sydo systemctl status nomad
```
## Tutorial
[Nomad-network-dymystified](https://www.hashicorp.com/resources/nomad-networking-demystified/)  
Including brief intro of nomad, and network knowledge.  
