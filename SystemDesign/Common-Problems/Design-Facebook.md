[Ref-LiveComments](https://www.hellointerview.com/learn/system-design/problem-breakdowns/fb-live-comments)  
[Ref-NewFeeds](https://www.hellointerview.com/learn/system-design/problem-breakdowns/fb-news-feed)  
[Ref-PostSearch](https://www.hellointerview.com/learn/system-design/problem-breakdowns/fb-post-search)  

# Live Comments
1. Functional Requirements
   1. post comment
   2. view comments near real-time
   3. view previous comments
   4. (optional) reply to comments
   5. (optional) react to comments
2. Non-Functional Requirements
   1. scalable - support millions concurrent views and thousands of comment per second
   2. high availability, eventual consistency is fine
   3. low latency, near real-time
   4. (optional) secure: authorized user, appropriate comment
3. Core Entity
   1. User
   2. Live Video
   3. Comment
4. API
   1. post a comment
   ```
   POST /comment/create
   {videoId, message}
   ```
   2. fetch comments
   ```
   GET /comment/:videoId
   -> Comment[]
   ```
5. High-level Design
   1. post comment: client -> LB -> CommentManagementService -> DB
   2. see all comments
      1. Websocket : not suitable our case which read/write ratio is not balanced
      2. SSE (undirectional and goes over HTTP): better when read > write. The infrequent writes goes over HTTPS and the frequent reads are served by SSE. -> Chanllenge: it maintain a persistent connection which may disrupt the continuous stream data
   3. see previous comments made before they joined, know as "Infinite scrolling"
      1. Cursor Pagination with Prefetching and Caching
         1. Cursor Pagination : `GET /comments/:liveVideoId?cursor={last_comment_id}&pagesize=10`
         2. system also prefetch a larger set of results and stores them in a cache. -> Challenge is enaure cache stay synchronized with the latest data
6. Deep Dives
   1. How to support millions of concurrent views?
      1. We already land on SSE technology. With SSE, we need to maintain an open connection for each viewer, and each connection will occupy a port. For a server, the maximum available port is 65535. To suport millions viewers, we need to scale horizontally. But then how do we distribute load and how does each server know which comments to send to which viewer?
         1. while traditional port limit is around 65535. Advanced techniques like port reuse and enhanced TCP/IP stack implementation (TCP port multiplexing and HTTP/2 enable) allow single port to handle multiple connections.
         2. To address the issue of servers handling viewers watching many different live videos, we can implement a more intelligent allocation strategy. This strategy would ensure that servers primarily handle viewers watching the same video. To do this, we'll upgrade our load balancer to a layer 7 load balancer that can route traffic based on the live video ID. This will ensure that viewers watching the same live video are routed to the same server.
         3. Pub-sub partition: server only need to subscribe the topic for the small number of live videos. -> Challenge: the uneven distribution load, which need dynamic resource allocation strategy, such as spinning up additional server ore reallocating resources.
         4. Scalable Dispatcher instead of PubSub: We need to introduce a new component called a Dispatcher Service. The dispatcher service is responsible for receiving comments from the comment management service and sending them to the correct Realtime Messaging Server. To achieve this, the Dispatcher Service maintains a dynamic mapping of viewers to their corresponding Realtime Messaging Servers. This mapping is constantly updated in response to viewer activities, such as joining or leaving a live video stream. When a new Realtime Messaging Server comes online, it registers itself with the Dispatcher Service, updating the Dispatcher’s understanding of the system's current topology. This registration process includes information about the server's capacity and the live videos it is currently serving. The Dispatcher Service is also designed to be scalable and replicable. In high-demand scenarios, multiple instances of the Dispatcher Service can be deployed to share the load. This replication not only balances the traffic but also adds redundancy to the system, enhancing its resilience.
7. Diagram
![Diagram](../../image/FB-livecomments1.png)


# News Feed
1. Functional Requirements
   1. create post
   2. follow people
   3. view a feed in chronological order
   4. page through their feed
   5. (optional) like and comment the post
   6. (optional) have restricted visibility