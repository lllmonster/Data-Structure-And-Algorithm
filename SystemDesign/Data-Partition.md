
REF: DDIA-CH6

### Partition by key range (can adapt partition boundaries to distribute data evenly)  
How to choose primary key is important  
### Partition by hash of key
How to choose a good hash function to make data uniformly distributed

### Relieve hot spots
* Consistent hashing is rarely used in practice. If a key is known, you can add a random number to the key to split writes. (downside is read might have to do additional work to track the keys)
* request coalescing and consistent hash-based routing
  * Ref: Discord to resolve hot shard (issue)[https://engineeringatscale.substack.com/p/how-discord-solved-hot-partition-problem]

### Primary and Seconday index
Document-based partition (local index, good for write, not read)
Term-based partition(global index, good for read, not write)

### Rebalancing Partitions
1. hash mode n: frequent move make rebalancing expensive
2. fixed number of partition: create more partition than nodes, and assign a few partition to new node.
3. dynamic partitioning: the number of partitions dynamically adjust to fit the size of data.
4. partitioning proportionally to nodes:fixed number of partitions per node
5. Request Routing:service discovery - locating requests over a network
   1. allow client to contact any node, and then forward to the relevant node
   2. send all requests to a routing tier first, then forwarding. (like a partition-aware load balancer)
   3. require client be aware of partitioning and do assignment.
