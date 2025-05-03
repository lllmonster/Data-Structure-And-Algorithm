
Ref : System Design Interview - An Insider's Guide_ Volume 2  

1. Requirements
   1. IDs must be unique
   2. IDs are numerical values only
   3. IDs fit into 64 bit
   4. IDs are ordered by date
   5. Ability to generate over 10,000 unique IDs per second
2. High-level Design
   1. There are multiple options can be used to generate unique IDs in distributed system, like :
      1. Multi-master replication
         1. This approach use the database's auto_increment feature.
         2. Cons : Hard to scale with multiple data centers ; IDs donot go up with time across servers; It doesnot scale well when a server is added or removed
      2. UUID (Universally unique identifier)
         1. UUID is a 128 bit number used to identify information in computer systems.
         2. Pros: Simple; Easy to scale
         3. Cons: 128 bits long; do not go up with time; could be non-numeric
      3. Ticket server
         1. The idea is to use a centralized auto_increment feature in a single database server.
         2. pros: Numeric IDs; Easy to implement
         3. Cons: Single point of failures
      4. Twitter snowflake approach
         1. Divide and conquer : Instead of generating an ID directly, we divide an ID into different sections:
            1. Sign bit : 1 bit
            2. Timestamp : 41 bits
            3. Datacenter ID: 5 bits
            4. Machine ID: 5 bits
            5. Sequence number : 12 bits
3. Additional Talking points
   1. Clock synchronization - Network Time Protocol
   2. Section length tuning
   3. High availability