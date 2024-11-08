# Database

## Catagory
1. Relational DB : MySQL, Oracle, PostgreSQL
2. Non-Relational DB
   1. Column DB : Cassandra, HBase, CosmosDB
   2. Key-value Store: Redis, DynamoDB, CosmosDB
   3. Document Store: MongoDB, Firebase, DynamoDB
   4. Search Engine: ElasticSearch, Solr, Splunk
   5. Graph DB: Neo4j, CosmosDB
   6. Time-Series DB: Prometheus, InfluxDB

## Summary
RelationalDB:  
1. Many-to-many relationships
2. fixed schema
3. consisteny transactions
4. hard to scale

Key-Value Store:  
1. no schema/relationships
2. simple operation
3. easy to scale
4. opaque values
5. Suitable for caching.  
6. When values need to be large, this kind of DB is like ObjectStore, or Blob Store. In this case, data might be serialized and optimized for large file sizes. Use cases include videos, images, audio, disk images, and log

Document Store:  
1. isolated documents
2. retreve by a key
3. doc with different schemas that are easy to udpate
4. easy to scale
5. Suitable for content management application such as blogs and video platforms. Also effective for storing catalog information.

Graph DB:  
1. many-to-many relationship
2. fast at following graph edges
3. Use case include Social Networking, recommendation engines, and fraud detection, complex network analytics

Column DB:  
1. Store data in columns rather than the rows.  
2. easy to scale
3. memory effective for sparse data 
4. quick data retrieval and analysis. suitable for online analytical processing.  
5. Can repidly access and retrieve ony the necessary data for a particular query, significantly reducing IO operations and enhancing performance.

Search Engine:  
1. large amounts of unstructured data
2. full text search and for logging and analysis.

Time Series DB:  
1. data is ordered by time
2. many data streams
3. real time entry ordering functionality
4. suitable for system monitors.



## Key Concept
1. CAP Theorem
   1. Consistency: Every read receives the most recent write or an error.
   2. Availability (A): Every request receives a (non-error) response, without guaranteeing that it contains the most recent write.
   3. Partition Tolerance (P): The system continues to operate despite an arbitrary number of messages being dropped (or delayed) by the network between nodes.

    Scenario:
    * CP:
      * use case:  Financial transactions
      * example: google cloud spanner
    * AP:
      * use case:  Social media platforms
      * example: Cassandra, Couchbase
    * CA:
      * use case: However, in real-world distributed systems, achieving CA is challenging due to the inevitability of partitions3.
      * example: Traditional relational databases (under ideal conditions without partitions)
2. ACID - used for transaction
   1. Atomicity: A transaction is an atomic unit
   2. Consistency: A database is initially in a consistent state and keep it.  
   3. Isloation: thread-safe
   4. Durability: Changes that have been committed to the database should remain. 
3. BASE - NoSQL database tend to conform to BASE principle.
   1. Basically Available: Rather than enforcing immediate consistency, BASE-modelled NoSQL db will ensure availability of data by spreading and replicating it across the nodes of the database cluster.
   2. Soft State: Due to the lack of immedidate consistency, data values may change over time.
   3. Eventually Consistent: still it will achieve the final consistency state.
4. Transactions
5. Schemas
6. Scaling

## Relational DB Concept
1. Federation(functional partitioning): split up database by function, resulting in less read and write traffic to each db and less replication lag. But might not effective, add more complexity.
2. Sharding: make each db only manage a subset of data. Similar to federation, sharding results in less read and write traffic, less replication and more cache hits.
3. Denormalization: attemps to improve read performance at the expense of some write performance. But data is duplicated, and heavy write might be even worse than its normalized couterpart.
4. SQL tuning: It's important to benchmark and profile to simulate and uncover bottlenecks. 
   1. Benchmark: simulate high-load situations with tools
   2. Profile: enable tools like slow query log to help track performance issue

## Non-relational DB Concept

  * Key-Value, Wide-Column, Graph, Document
  * |  |  |  |
    |-----------------|-----------------|-----------------|
    | RAM    | [Bounded size]    | Redis, Memcached    |
    | AP    | [Unbounded size]    | Cassandra, RIAK, Voldemort  |
    | CP    | [Unbounded size]    | HBase, MongoDB, Couchbase, DynamoDB    |
  

## Database sharding and partitioning:  
When sharding a database, you make partitions of data so that the data is divided into various smaller, distinct chunks called shards.  
Two type: vertical sharding and horizontal sharding. You need to dertemine a shading key to partition your data. Sharding allows your application to make fewer queries and improves your application's overall performance and scalability, load balancing and manageability.   

## Database indexing:  
Allows you to make it faster and easier to search and through your tables and find the rows or columns that you want. While indexes dramatically speed up data retrieval, they typically slow down data insertion and updates because of their size.  


## Database calculation
  | Data Type      | Size                                | Range                   |
  |----------------|-------------------------------------|-------------------------|
  | INT            | 4 bytes                             | 2^31 - 2^-31            |
  | DECIMAL        | DECIMAL(10,2) might take up around 5 bytes |                         |
  | FLOAT          | 4 bytes                             |                         |
  | DOUBLE         | 8 bytes                             |                         |
  | CHAR(n)        | n bytes                             |                         |
  | VARCHAR(n)     | n bytes                             |                         |
  | TEXT           | 64 KB                               |                         |
  | TIMESTAMP      | 4 bytes                             |                         |
  | BINARY(n)      | n bytes                             |                         |
  | BLOB           | 64 KB                               |                         |
  | BOOLEAN        | 1 byte                              |                         |
  | UUID           | 16 bytes                            |                         |