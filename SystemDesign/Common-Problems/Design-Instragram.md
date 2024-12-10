
1. Functional Requirements
   1. search photo/video
   2. upload photo/video
   3. users is able to follow others
   4. generate new feeds
2. Non-Functional Requirements
   1. HA
   2. low latency
   3. photo/video are never lost
3. Estimation
   1. 500M users, 1M DAU
   2. 2M new photos/videos per day -> 23/s
   3. average each post size is 200KB
4. Core Entity
   1. User(userId, name, email, DOB, createdAt, lastLogin)
   2. Photo(photoId, originalUrl, path, location, userLocation, createdAt)
   3. UserFollow(userId, follwerId)
5. High-level Design
   1. DB 
      1. NoSQL to store photo information(key-value): easier to scale, flexible schema, no relation
      2. Wide-Column DB to store relationship between user and photo as well as userFollower
      3. Blob Storage/ Distributed file storage to store photo/videos