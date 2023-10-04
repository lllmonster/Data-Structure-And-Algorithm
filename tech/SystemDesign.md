# System Design

## Tutorials
[Education-how-to-prepare-system-design-interview](https://www.educative.io/blog/how-to-prepare-system-design-interview)
[Educative-complete-guide-to-system-design-2023](https://www.educative.io/blog/complete-guide-to-system-design)
[Education-complete-guide-to-system-design-interview-2023](https://www.educative.io/blog/complete-guide-system-design-interview)

### To be seen
https://www.youtube.com/watch?v=q0KGYwNbf-0&t=3135s
https://www.youtube.com/channel/UCaO6VoaYJv4kS-TQO_M-N_g
https://www.youtube.com/c/SystemDesignInterview
https://www.youtube.com/user/tusharroy2525
https://www.youtube.com/c/TechDummiesNarendraL
https://www.youtube.com/c/ScottShiCS/playlists
https://youtu.be/PdtlXdse7pw?feature=shared

design data intensive system - book

staff prep - https://www.1point3acres.com/bbs/thread-907026-1-1.html

https://www.codinginterview.com

### Questions
1. in the PACELC theorm, what means availability, consistency and partition?

## Introduction
System Design is the process of defining the architecture, interfaces, and data for a system that satisfies specific requirements.

Horizontal scaling: add more hardware to the existing hardware resource pool. It increases the computational power of the system.

Vertical scaling: add more power to your server. It increases the power of the hardware running the application.

Microservice: structures an application using loosely coupled services.

Proxy server: act as a channel between a user and the internet. (improved security, privacy; access to blocked resources; cache data to speed up request; control of internet usage)

CAP theorem: It formalizes the tradeoff between consistenct and availability when there's a partition.

PACELC theorem: states the following about a system that replicates data  
* if statement: if there's a partition, a distributed system can trade off between availability and consistency. (CAP theorem)  
* else statement: if there's no partition, the system can trade off between latency and consistency.  
* Examples of a PC/EC system include BigTable and HBase. They'll always choose consistency, giving up abailability and lower latency.
* Example of a PA/EL system include Dynamo and Cassandra. They choose availability over consistency when a partition occurs. Otherwise, they choose lower latency.
* Example of PA/EC system include MongoDB, in the case of partition, it chooses availability, but otherwise guarantees consistency.

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
* Bloom filters: Are probabilistic data structures designed to answer the set membership question: Is this element present in the set? Bloom filters are highly space-efficient and do not store actual items. They determine whether an item does not exist in a set or if an item might exist in a set. They can't tell if an item is definitely presently in a set. An empty Bloom filter is a bit vector with all bits set to zero.  
* Consistent hashing: maps data to physical nodes and ensures that only a small set of keys move when servers are added or removed. Consistent hashing stores the data managed by a distributed system in a ring. This concept is important within distributed systems and works closely with data partitioning and data replication.  
* Quorum: is the minimum number of servers on which a distributed operation needs to be performed successfully before declaring the operation's overall success.
* Checksum: It verifies that the data received from the server matches the stored checksum.  
* Merkle trees: Is a binary tree of hashes, in which each internal node is the hash of its two children, and each leaf node is a hash of a portion of the original data. Replicas can contain a lot of data. Splitting up the entire range to calculate checksums for comparison is not very feasible, because there's so much data to be transferred. Merkle trees enable us to easily compare replicas of a range.  
* Leader election: Is the process of designating a single process as the organizer of tasks distributed across several computers. Leader election improves efficiency, simplifies architectures, and reduces operations.  

Databases:
* Relational databases: are structured. They have predefined schemas.SQL database store data in rows and columns. (MySQL, Oracle, PostgreSQL, MariaDB)  
    * MySQL: Is an open-source relational database management system (RDBMS) that stores data in tables and rows. It follows client-server architecture and supports multithreading.  
    * PostgreSQL: Is an open-source RDBMS that emphasizes extensibility and SQL compliance. Postgres employs SQL to access and manipulate the database. It uses its own version of SQL which can perform more complex queries. It use foreign key, which allow us to keep our data normalized.
* Non-relational databases: are unstructured. They have a dynamic schema, like file folders that store information. (Redis/DynamoDB, MongoDB/CouchDB, Cassandra/HBase, Neo4J/InfiniteGraph)  
    * MongoDB: is a NoSQL, non-relational database management system (DBMS) that uses documents instead of tables or rows for data storage. This data model makes it possible to manipulate related data in a single database operation. MongoDB documents use JSON-like documents and files that are JavaScript supported. The document fields can vary, making it easy to change the structure over time.  
* How to choose a database: When choosing your database structure, it's important to factor in speed, reliability, and accuracy. We have relational database that can guarantee data validity, and we have non-relational database that can guarantee eventual consistency.   
* Database schemas: are abstract designs that represnet the storage of the data in a database.   
* Database queries: is a request to access data from a database to manipulate or retrieve it.  
* ACID properties: 
    * Atomicity: A transaction is an atomic unit
    * Consistency: A database is initially in a consistent state and keep it.  
    * Isloation: thread-safe
    * Durability: Changes that have been committed to the database should remain.  
* Database sharding and partitioning: When sharding a database, you make partitions of data so that the data is divided into various smaller, distinct chunks called shards. Two type: vertical sharding and horizontal sharding. You need to dertemine a shading key to partition your data. Sharding allows your application to make fewer queries and improves your application's overall performance and scalability, load balancing and manageability.   
* Database indexing: Allows you to make it faster and easier to search and through your tables and find the rows or columns that you want. While indexes dramatically speed up data retrieval, they typically slow down data insertion and updates because of their size.  

Distributed systems: Benefits: Scaling, Modular growth, Fault tolerance, Cost-effective, Low latency, Efficiency, Parallelism  
* Distributed system failures: System failure, Communication medium failure, Secondary storage failure, method failure  
* Destributed system fundamental:
    * MapReduce: handle large amounts of data in an efficient manner. Partitioning -> Map -> intermediate files -> Reduce -> Aggregate
    * Stateless and stateful systems: A stateless system maintains no state of past events. Stateful systems are responsible for maintaining and mutating a state.  
    * Raft: establishes the concept of a replicated state machine and the associated replicated log of commands as first-class citizens and supports multiple consecutive rounds of consensus by default. It requires a set of nodes that form a consensus group, or a Raft cluster (Leader, Follower, Candidate)
* Distributed system design patterns: 
    * Object communication: Describe the messaging protocols and permissions for different components of the system to communicate.  
    * Security: Handles confidentiality, integrity, and availability concerns to ensure the system is secure from unauthorized access  
    * Event-driven: Describes the production, detection, consumption, and response to system events.  

Scalable web applications:
* DNS and load balancing  
* N-tier application: are applications that have more than three components involved. (Caches, MQ, Load balancers, Search servers, Components involved in processing large amounts of data, Components running heterogeneous tech like web services)  
* HTTP and REST: HTTP stands for HyperText Transfer Protocol. This protocol dictates the format of messages, how and when messages are sent, appropriate responses, and how messages are interpreted. HTTP messages can be either requests or response. REST stands for Representational State Transfer. It's a ruleset that defines best practices for sharing data between clients and servers, and it emphasizes the scalability of components and the simplicity of interfaces.  
* Stream processing: focus on the real-time processing of continuous streams of data. (Kafka, Storm, Flink)  
* Caching: that you use to temporarily store data so it can be accessed quickly. 
    * Cache eviction: If a cache is full, some data will be evicted. Common policy include FIFO, LIFO, LRU, MRU, LFU, RR

Machine learning and System Design  
Containerization and System Design: Docker and Kubernetes  
The Cloud and System Design:

Heartbeat: A heartbeat message is a mechanism that helps us detect failures in a distributed system.


*AJAX polling vs Long polling vs WebSockets vs Server-Sent Events*  

AJAX polling: Polling is a standard technique used by most AJAX apps. The idea is that the client repeatedly polls a server for data.  
1. The client opens a connection and request data from the server using regular HTTP
2. The requested webpage sends request to the server at regular intervlas (eg 0.5s)
3. The server calculates the response and send it back, just like regular HTTP traffic
4. The client repeats the above three steps periodically to get updates from the server.  
Mainly used when no real-time data is needed. One example is the weather app

Long-Polling: The server may not respond immediately - Hanging GET
1. The client opens a connection and request data from the server using regular HTTP, then waits for a response
2. The server delays its response until an update is avilable or a timeout has occurred.
3. When an update is available, the server sends a complete response to the client
4. The client typically sends a new long-poll request, either immediately upon receiving a response or after a pause to allow an acceptable latency period.
5. Each Long-Poll request has a timeout. Therefore, the client has to reconnect periodically after the connection is closed due to timeouts.

WebSocket:  
It provides a persistent connection between a client and server that both parties can use to start sending data at any time. The WebSocket protocol enables the communication between a client and a server with lower overheads, facilitating real-time data transfer from and to the server.  

Server-Sent Events:  
1. The client opens a connection and request data from the server using regular HTTP
2. The server sends the data to the client whenever there's new information available  
SSE are best when we need real-time traffic from the server to the client or if the server is generating data in a loop and send multiple events to the client.

## Interview Guide - Common Questions
Three major focus areas in your prep plan should include the **fundamentals of distributed systems, large-scale web application architecture, and how to design distributed systems.**  

### Design Uber
* Problem overview