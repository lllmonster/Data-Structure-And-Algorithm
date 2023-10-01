# System Design

## Introduction
System Design is the process of defining the architecture, interfaces, and data for a system that satisfies specific requirements.

Horizontal scaling: add more hardware to the existing hardware resource pool. It increases the computational power of the system.

Vertical scaling: add more power to your server. It increases the power of the hardware running the application.

Microservice: structures an application using loosely coupled services.

Proxy server: act as a channel between a user and the internet. (improved security, privacy; access to blocked resources; cache data to speed up request; control of internet usage)

CAP theorem: It formalizes the tradeoff between consistenct and availability when there's a partition.

Redundancy and replication

Storage:  
* Block Storage: data is broken down into blocks of equal size, and each block is given a unique identifier for easy accessibility. These blocks are stored in physical storage. As opposed to adhering to a fixed path, blocks can be stored anywhere in the system, making more efficient use of the resources.  

* File Storage: Is a hierarchical storage methodology. The data is store in files. The files are stored in folders. This storage is only good for a limited amount of data, primarily structured data.  

* Object Storage: is designed to handle large amounts of unstructured data. It is the preferred data storage method for data archiving and data backups because it offers dynamic scalability. It isn't directly accessible to an operating system. Communication happens through RESTful APIs at the application level. This type of storage provides immense flexibility and value to systems, because backups, unstructured data, and log files are important to any systems.  

* Redundant Disk Arrays (RAID): Is a technique to use multiple disks in concert to build a faster, bigger, and more reliable disk system.

Message Queues:  
MQ is a queue that routes messages from a source to a destination, or from sender to the receiver. It follows FIFO policy. MQ facilitate asynchronous behavior, which allows modules to communicate with each other in the background without hindering primary tasks.
* Kafka: Kafka is a distributed system consisting of servers and clients that communicate through a TCP network protocol. The system allows us to read, write, store and process events. Kafka is primarily used for builidng data pipelines and implementing streaming solutions.
    * kafka vs RabbitMQ
    * Kafka vs Kinesis
    * Kafka vs Flink
    * Kafka vs RedisStream


File Systems:
* Google File System: is a scalable distributed file system designed for large data-intensive applications, like gmail or youtube. It was built to handle batch processing on large data sets and is designed for system-to-system interaction, rather that user-to-user interaction. It's scalable and fault-tolerant.  
* Hadoop Distributed File System(HDFS): is a distributed file system that handles large sets of data and runs on commodity hardware. It was built to store unstructured data. HDFS is a more simplified version of GFS.

System Design patterns:  

## Distributed system fundamentals
### Data Durability and Consistency
Know the difference and impacts of failure rates of storage solutions and corruption rates in read-write processes.

### Replication
The key to unlocking data durability and consistency; replication deals with backups of data, but also being able to repeat processes at scale.

### Partitioning
Also called shards, partitions divide data across different nodes within the system. As replication distributes the data across nodes, partitioning distributes processes across nodes. This reduces the reliance on pure application.

### Consensus
### Distributed Transactions



## The architecture of large-scale web applications
### N-Tier applications
### HTTP and REST
### DNS and Load Balancing
### Caching
### Stream Processing


## Designing distributed systems