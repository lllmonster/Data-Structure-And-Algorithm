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