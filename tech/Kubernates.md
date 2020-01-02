## Kubernetes

Suppose you want to install a web application on a linux sever. How do you do?  
1. Install it directly on the physical server's OS.  
2. Install it directly using virtual machine.  
    But VM requires some administrative effort and cost as well. It will be underutilized if you just dedicate it for just one task.
3. Install it in a container (a mini-virtual machine)  

There's an inherent problem with containers, just like there's like vms. That is the need to keep track of them. Orchestration tackles these problems. This is where `Kubernetes` comes in.  

Kubernetes is a cluster and container management tool. It lets you deploy containers to clusters, meaning a network of VMs.