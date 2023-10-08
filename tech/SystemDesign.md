- [System Design](#system-design)
  - [Tutorials](#tutorials)
    - [To be seen](#to-be-seen)
    - [Questions](#questions)
  - [Introduction](#introduction)
  - [Data Structure Or Method](#data-structure-or-method)
    - [Quadtree](#quadtree)
    - [Hash and Encode](#hash-and-encode)
    - [Consistent Hashing](#consistent-hashing)
    - [Round-Robin load balancing](#round-robin-load-balancing)
  - [Interview Guide - Common Questions](#interview-guide---common-questions)
    - [Design Uber](#design-uber)
    - [Design TinyURL (DONE)](#design-tinyurl-done)
    - [Design Instragram (TODO)](#design-instragram-todo)
    - [Design Yelp (TODO)](#design-yelp-todo)

# System Design

## Tutorials
[Education-how-to-prepare-system-design-interview](https://www.educative.io/blog/how-to-prepare-system-design-interview)
[Educative-complete-guide-to-system-design-2023](https://www.educative.io/blog/complete-guide-to-system-design)
[Education-complete-guide-to-system-design-interview-2023](https://www.educative.io/blog/complete-guide-system-design-interview)

### To be seen
Google Systems Design Interview With An Ex-Googler: Code-deployment system   
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

free course  
https://www.educative.io/courses/system-design-interview-handbook/the-system-design-interview
https://github.com/donnemartin/system-design-primer  

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
* ACID Model:  (consistency, predictability, reliability)
  * properties: 
    * Atomicity: A transaction is an atomic unit
    * Consistency: A database is initially in a consistent state and keep it.  
    * Isloation: thread-safe
    * Durability: Changes that have been committed to the database should remain. 
  * use case: Financial institutions will almost use ACID database (like money transfer depends on the atomic nature of ACID)
  * what db are acid compliant? a relational database management, including MySQL, PostgreSQL, Oracle, SQLite, etc. 
* BASE Model: (easy scale, more flexibility)
  * properties
    * Basically Available: Rather than enforcing immediate consistency, BASE-modelled NoSQL db will ensure availability of data by spreading and replicating it across the nodes of the database cluster.
    * Soft State: Due to the lack of immedidate consistency, data values may change over time.
    * Eventually Consistent: still it will achieve the final consistency state.
  * use case: Marketing and customer service companies who deal with sentiment analysis will prefer the elasticity of BASE when conducting their social network research. Social network feeds are not well structured but contain huge amounts of data which a BASE-modelled db can easity restore.
  * what db are using the BASE model? NoSQL database tend to conform to BASE principle. MongoDB, Cassandra and Redis are among the most popular NoSQL solutions, together with Amazon DynamoDB and Couchbase.
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
    * Cache memory: According to 80/20 rule (20% data are used 80% time), we can cache 20% of data and improve 80% latency time.

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

## Data Structure Or Method
### Quadtree
Quadtrees are a knowledge structure that encodes a two-dimensional space into adaptable cells. It's a tree structure where every non-leaf node has four children. During the technique of quadtrees, nodes are recursively divided into pieces, with successive subdivisions leading to smaller and smaller cells.  

When use? Quadtreees are used for scaling an internet service to handle thoudsands of requests every second. This requires an excellent caching strategy. When geolocation is the main parameter of those requests, conventional caching techniques and procedures fall through. Quadtrees are used to understand high cache hit ratios, while also keeping the responses relevant, even in intense areas.

It's a dynamic grid and every night we recompute the quadtree to support out currently subscribed business. If the number of search results entered on a node exceeds a predefined threshold, then we divide it further.
```java
public static void buildQuadtree(Node node) {
    if (searchBusinesses(node).size() > 20) {
        node.subdivide();
        for (Node child : node.getChildren()) {
            buildQuadtree(child);
        }
    }
}
```
When our server receives an invitation for businesses in a location, we first find the quadtree node for that location:
```java
public static void getNearbyBusinesses(float latitude, float longitude) {
    QuadtreeNode node = LookupQuadTreeNode(latitude, longitude);
    String cacheKey = "quadtree_" + node.getId();
    List<Result> results = Cache.get(cacheKey);
    if (results == null) {
        results = searchBusinesses(node);
        Cache.set(cacheKey, results, "2h");
    }
    render(results);
}
```
Quadtrees allow us to mix the advantages of caching the geo-locations with both high and low precisions. 

### Hash and Encode
hash method: MD5 / SHA256  
encode method: base32 / base64  
* How to calcuate url length after hash and encode?
  * The MD5 hash function produces a fixed-size hash value of 128 bits, which is equivalent to 16 bytes or 32 hexadecimal characters. 
  * If you hash a 6-bit URL using MD5, the resulting hash will still be 128 bits in length. It doesn't matter how long or short the input is; MD5 always produces the same size hash.
  * Now, if you encode the 128-bit MD5 hash using Base64, you'll need to calculate the length of the encoded string. Base64 encoding is done in groups of 3 bytes at a time, where each 3 bytes are represented as 4 Base64 characters. If the number of bytes in your hash is not a multiple of 3, padding characters ('=') are added at the end.
  * In the case of a 128-bit hash from MD5, you have 16 bytes. When encoding in Base64:
    - You'll have 16 bytes / 3 bytes per group = 5 groups.
    - Each group is represented as 4 Base64 characters.
    - So, you have 5 groups * 4 characters per group = 20 Base64 characters.
  * However, you also need to consider padding, which is added to make the length a multiple of 4. In this case, you don't need any padding because 20 is already a multiple of 4.
  * So, the encoded URL will be 20 characters long when using Base64 encoding for a 128-bit MD5 hash.
  
### Consistent Hashing
Consistent Hashing
In order to solve the above problem, consistent hashing is used because in this technique on average r/n needs to be remapped, where r is the number of records and n is the number of servers slots. It should be done when:  
* There are a number of servers that need to be scaled up or down depending upon the load.
* There are cache servers that need to be scaled.
* There are many benefits to using consistent hashing:
  * Scalability
  * Load distribution
  * Quick replication and partitioning of data
  * Faster retrieval of keys as each server holds a limited number of keys

The key idea behind consistent hashing is that every record and server is mapped on the unit circle. Each record is then assigned to the first server that appears on the circle in a clockwise direction. This brings even distribution of records.

### Round-Robin load balancing 
The round-robin load balancing technique is the simplest way to distribute traffic across a group of servers. The load balancer forwards the incoming requests to dedicated servers sequentially (one by one mechanism).  
Round-robin is a static load balancer, because it does not modify the state of the servers while distributing incoming traffic.  
Principal: i mod T  
Limitation: This load balancing algorithm is not adaptive, as it does not consider the existing load on servers to distribute incoming requests. Ideally, the load balancer should forward the request to the server whose current state is idle.  
Real-time load balancer: LVS


## Interview Guide - Common Questions
[ref](https://www.educative.io/blog/complete-guide-system-design-interview)
Three major focus areas in your prep plan should include the **fundamentals of distributed systems, large-scale web application architecture, and how to design distributed systems.**  

### Design Uber
[ref](https://www.educative.io/blog/uber-backend-system-design)
* Problem overview: when designing Uber, there're two kinds of users: drivers and users. Requirements are as follow:  
  * Drivers can frequently notify the service regarding location and availability
  * Users can see all nearby drivers in real-time
  * Users can request a ride using a destination and pick-up time
  * Nearby drivers are notified when a user needs a ride
  * Once a ride is accepted, both the driver and user can see the other’s location for the duration of the trip
  * Once the drive is complete, the driver completes the ride and is available for other users
* Constraints
  * 300 million users and 1 million drivers in the system
  * 1 million active customers and 500 thousand active drivers per day
  * 1 million rides per day
  * All active drivers notify their current location every three seconds
  * System contacts drivers in real-time when a user requests a ride
* Design considerations
  * We need to update data structures to reflect drivers’ locations every three seconds. To update a driver to a new location, we need to find the right grid based on their previous location.
  * If the new position doesn’t belong to the current grid, we need to remove the driver from the grid and reinsert them into the appropriate grid. If the new grid reaches a maximum limit, we have to repartition it.
  * We need a quick mechanism to propagate the current location of nearby drivers to users in the area. Our system needs to notify the driver and the user of the car’s location for the duration of the ride.
* Solving
  * Keeping those considerations in mind, we can determine that a QuadTree isn’t ideal because we can’t guarantee that the tree will update as quickly as our system requires. We can keep the most recent driver position in a hash table and update our QuadTree less frequently. We want to guarantee that a driver’s current location is reflected in the QuadTree within 15 seconds. We’ll call our hash table DriverLocationHT.
  * Driver Location Record
    * DriverId - 3 bytes
    * Old Latitude - 8 bytes
    * Old longitude - 8 bytes
    * New Latitude - 8 bytes
    * New longitude - 8 bytes
      * Total: 1 million * 35 bytes = 35MB
      * Bandwidth: if we get the driverId and location, it will require (3+16=19 bytes). This information is gathered every three sec from 500 thousand daily active drivers, so we will receive 19 * 500,000 = 9.5 MB every three sec.
  * To randomize distribution, we could distribute DriverLocationHT on multiple servers based on the DriverID. This will help with scalability, performance, and fault tolerance. We’ll refer to the machines holding this information as “driver location servers”. These servers will do two more things. Once the server receives an update on a driver’s location, it will broadcast it to relevant users. The server will also notify the respective QuadTree server to refresh the driver’s location.
  * Broadcasting driver locations: we can use a push Model. We can use a notification service and build it on the pub/sub model. User subscribe all updates from nearby drivers. And Each update in a driver's location will be broadcasted to all subscribed users.
    * Assume one million active users and 500 thousand active users per day. Also five customers subscribe to one driver. So we need 3 bytes for driver id and 8 bytes for userId, (500,000*3)+(500,000*5*8)=21.5 MB of memory
    * for bandwidth, for each active driver, we have 5 subscribers, and each driver record is 19 bytes, so we need 5 * 500,000 * 19 bytes = 47.5 MB/s
  * Notification service: we can either use HTTP long polling or push notifications. Users are subscribed to nearby drivers when they open the app for the first time. When new drivers enter their areas, we need to add a new user/driver subscription dynamically. To achieve this, we track the area that a user is watching. However, this can get pretty complicated.
    * To repartition, we can create a cushion so that each grid grows beyond the limit before we decide to partition it. Assume our grids can grow or shrink by an extra 10% before we partition them. This will decrease the load for a grid partition.
* Use case: request ride
  * The customer enters a ride request.
  * One of the Aggregator servers accepts the request and asks the QuadTree servers to return nearby drivers.
  * The Aggregator server collects the results and sorts them by ratings.
  * The Aggregator server sends a notification to the top drivers simultaneously.
  * The first driver to accept will be assigned that ride. The other drivers will receive a cancellation.
  * If the drivers do not respond, the Aggregator will request a ride from the next drivers on our list.
  * The customer is notified once a driver accepts a request.
* Advanced considerations:
  * Fault tolerance and replication
  * Ranking
  * Clients using slow or disconnecting networks
  * Clients that are disconnected during the duration of a ride
  * how to handle billing if a ride is disconnected
  * how to implement machine learning components in your system

### Design TinyURL (DONE)
[ref](https://www.educative.io/blog/system-design-tinyurl-instagram)

### Design Instragram (TODO)
[ref](https://www.educative.io/blog/system-design-tinyurl-instagram)

### Design Yelp (TODO)
[ref](https://www.educative.io/blog/top-10-system-design-interview-questions#proximity)