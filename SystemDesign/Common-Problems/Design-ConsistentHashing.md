
1. The rehashing problem
   1. serverIndex = hash(key) % N
   2. most key will be redistributed when server is added or removed
2. Consistent hashing
   1. Is a special hashing such that when a hash table is resized and only k/n keys need to be remapped on average, where k is the number of keys and n is the number of slots.
   2. Pros: minmized keys are redistributed when servers are added or removed; It's easily to scale horizontally because data are more evenly distributed; Mitigate hotspot key problem.
   3. Cons: data parition might not be evenly distributed.
3. Virtual node
   1. resolve above issue.