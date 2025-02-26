Ref:
* [System-design-primer](https://github.com/donnemartin/system-design-primer/blob/master/solutions/system_design/twitter/README.md)

# Timeline and Search
1. Functional Requirements
   1. Post a tweet
   2. View the user timeline
   3. View the home timeline
   4. Search keyword
2. Non Functional Requirements
   1. HA
   2. Low latency - request should be fast
   3. read heavy then write heavy
   4. ingesting tweets is write heavy
   5. search is ready-heavy
3. Estimation
   1. 100m active users
   2. 500m tweets per day
   3. 250b read requests per month
   4. 10b search per month
4. High-level design
   1. [Diagram](../../image/tweeter1.png)
5. Design Core Components
   1. User posts a tweet
      1. Store users' own tweet to populate the user timeline in a relational database.
         1. NOTE: use case and tradeoff [between choosing SQL and NoSQL](../Database.md#sql-or-nosql)
      2. Deliver tweets and build the home timeline is tricky. Fanning out tweets to all followers (60k per second) will overload a traditional relational database. We'll probably choose a data store with fast writes such as a NoSQL database or Memory Cache. Reading 1 MB sequentially from memory takes about 250 microseconds, while reading from SSD takes 4x and from disk takes 80x longer.
      3. We could store media such as photos or videos on an Object Store.
      4. Steps:
         1. The Client posts a tweet to the Web Server, running as a reverse proxy
         2. The Web Server forwards the request to the Write API server
         3. The Write API stores the tweet in the user's timeline on a SQL database
         4. The Write API contacts the Fan Out Service, which does the following:
            1. Queries the User Graph Service to find the user's followers stored in the Memory Cache
            2. Stores the tweet in the home timeline of the user's followers in a Memory Cache
               1. O(n) operation: 1,000 followers = 1,000 lookups and inserts
            3. Stores the tweet in the Search Index Service to enable fast searching
            4. Stores media in the Object Store
            5. Uses the Notification Service to send out push notifications to followers:
               1. Uses a Queue (not pictured) to asynchronously send out notifications
      5. API
      ```
      $ curl -X POST --data '{ "user_id": "123", "auth_token": "ABC123",  "status": "hello world!", "media_ids": "ABC987" }' https://twitter.com/api/v1/tweet
      ```
      6. For internal communications, we could use Remote Procedure Calls.







# Search

