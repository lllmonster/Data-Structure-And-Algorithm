
1. Functional Requirement
   1. send Message
   2. receive Message
   3. delete Message
   4. create/delete group
2. Non-Functional Requirement
   1. Scalable (handle load increase, more queue and msg)
   2. High Avaiable (survive hardware/network failure)
   3. low latency
   4. Durable(once submitted, no data lost)
3. High-level Design
   1. Gateway & Load Balancer
   2. Front End Service, actions:
      1. Request validation
      2. Authentication/Authorization
      3. TLS(SSL)
      4. Server-side encryption
      5. Caching
      6. Rate limiting
      7. Requet Dispatching
      8. Request deduplication
      9. Usage data collection
   3.  Metadata Service: a caching layer between frontEnd and persistent storage, many reads, little write
   4.  Back End Service:
       1.  How to store messeage? no DB, use RAM and file system
       2.  How to replicate data? 
           1.  Option A: Lead-Follower relationship
           2.  Option B: send to one, and forward to the correct one