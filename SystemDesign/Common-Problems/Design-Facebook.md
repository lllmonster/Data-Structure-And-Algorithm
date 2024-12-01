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
2. Non-Funtional Requirements
   1. HA
   2. low latency
   3. handle massive number of users
   4. able to follow unlimited users
3. Core Entity
   1. User
   2. Follow
   3. Post
4. API
   1. create post
   ```
   POST /post/create
   {content} -> postId
   ```
   2. follow people
   ```
   POST /user/{id}/follow
   ```
   3. get feed
   ```
   GET /feed
   -> Post[]
   ```
5. High-level Design
   1. API gateway -> Post Service -> Post DB
   2. API gateway -> Follow Service -> Follow DB
   3. API gateway -> Feed Service -> Post & Follow DB
6. Deep Dive
   1. How to handle users who are following a large number of users?
      1. in this case, the query to the Follow table will take a while and build feed. This problem is known as "Fan-out" - a single requests fans out to create many more requests. -> The instinct way is we should compute feed on write action rather than the read time -> we can have a precomputed Feed table which is just a list of postIds, stored in chronological order, and limited to a small number. -> Challenge would be write.
   2. How to handle users with a large number of followers?
      1. It's a simliar fanout problem when we create a post : we need to write to millions of Feed records. We can use Async workers with Hybrid Feeds.
         1. Async workers: we can queue up write requests and have a fleet of workers consume these requests and update feeds. Each worker will look up all the followers and prepend the post to the feed entry. -> Some worker might do a lot of work while others are only writing a few. Instead of just write a postId and UserId in the event, we could also include a partition of followers so that we can split the work across many workers.
         2. Hybrid Feeds: we can choose which account we'd like to pre-calculate and which we do not. For celebrity, instead of writing to 100 million followers we can instead add a flag to the Follow table which indicates this particular follow isn't precomputed. In the async worker queue, we will ignore these requests. Once user read, we can merge precomputed feed with recent posts from those celebrity.
   3. How to handle uneven reads of posts?
      1. For the vast majority of posts, they will be read for a few days and never read again. For some celebrity, the read of post in the first few hours will be massive.
      2. We can use cache for posts and set a TTL -> Challenge: hot shard issue
7. Diagram
![Diagram](../../image/FB-newsfeed1.png)