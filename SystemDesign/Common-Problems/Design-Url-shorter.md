[REF](https://www.hellointerview.com/learn/system-design/answer-keys/bitly)

1. Functional Requirements
   1. user submit a long url and receive a shorten one
      1. (optional) set expiration time for shorten url
      2. (optional) set custom alias for shorten url
   2. user can access the original url based on the shorten version
   3. (optional) Analytics on link clicks (e.g., click counts, geographic data).
2. Non-Functional Requirements
   1. low latency
   2. high available
   3. shorten url need to be unqiue
   4. support 1B shortened URLs and 100M DAU
   5. (optional) Advanced security features like spam detection and malicious URL filtering.
3. Estimation
   1. read-heavy, 100:1 r/w ratio
   2. Assume 500M urls per month, redirect times will be 500M * 100 = 50B per month -> 20k/s
4. Core Entity
   1. original URL
   2. shorten URL
   3. user
5. API
   1. shorten url
   ```
   POST /urls
   {longUrls, CustomAlias, ExpirationTime}
   -> shortUrls
   ```
   2. Redirect to the original URL
   ```
   GET /{short_url}
   -> HTTP 302 Redirect to the original long URL
   ```
6. High-Level Design
   1. client -> web server (generate short url) -> DB
   2. client -> web server (redirect to the original url)
      1. Temproray redirect 302 vs Permanent redirect 301 :  302 is better
         1. give us more control over the redirection process for update or expire links
         2. prevent browser from cacheing the redirect, which will cause the issue if we need to change or delete short url in the future
         3. allow us to track click statistics for each short url
   3. DB: need to store billions of records, read heady, each object is small, no relationship between record -> NoSQL would be a good choice.
7. Deep Dive
   1. how to ensure short url is unqiue?
      1. increment a global counter and encode it -> easy to scale with proper counter management -> BUT it's challenge to maintain a single global counter
         1. when we try to scale our write service, the counter need to be accesiable for all write instances. -> To resolve this, we can use a centralized Redis instance to store the counter. Redis is single-threaded and is very fast for this use case. It also supports atomic increment operations which allows us to increment the counter without any issues.
         2. optimized method - we can invlove Counter Batch
            1. Each Write Service instance requests a batch of counter values from the Redis instance (e.g., 1000 values at a time).
            2. The Redis instance atomically increments the counter by 1000 and returns the start of the batch
            3. The Write Service instance can then use these 1000 values locally without needing to contact Redis for each new URL
            4. When the batch is exhausted, the Write Service requests a new batch.
   2. how to define short url length?
      1. Assuming 1 billion urls and use base62 encoding, we only need 6 character string.
   3. how to ensure redirect is fast?
      1. add a index of shorten url
      2. implement in-memory cache: store frequently used urls -> for read scalability
      3. Leverage CDN and Edge Computing -> Challenge: complexity and cost
   4. how to support scalability and HA?
      1. each row consist of : shortUrl(8 bytes), longUrl(100bytes),createdAt(8bytes),customAlias(100bytes),expiredAt(8bytes) -> total ~100 bytes, around up to 500 bytes for additional metadata -> store 1b mappings, we need 1B * 500bytes = 500GB storage -> most of db can support it
      2. what if db goes down -> data replication
      3. As our read throughput is much larger than write throughput, we can split primary server into Read Service and Write Service. And horizontally scale each of them.
8. Diagram
![Diagram](../../image/shortenurl-1.png)

