[Ref](https://www.hellointerview.com/learn/system-design/problem-breakdowns/whatsapp)

1. Functional Requirements
   1. send/receive message
   2. group chats (limit 100)
   3. send/receive media
   4. receive message while they are not online
   5. (optional) delivered and sent acknowledgement
   6. (optional) audio/video calling
   7. (optional) registration and profile management
2. Non-Functional Requirements
   1. low latency
   2. guarantee deliverability of messages
   3. handle billions of users with high throughput
   4. HA
   5. (optional) security and spam prevention systems
3. Core Entity
   1. user
   2. group
   3. message
   4. client (a user might have multiple devices)
4. API
   1. create a group
   ```
   {members, name} -> groupId
   ```
   2. send message in the chat
   ```
   {chatId, message, attachement} -> SUCCESS | FAILURE
   ```
   3. add/remove user to the group
   ```
   {chatId, userId, operation(ADD|REMOVE)} -> SUCCESS | FAILURE
   ``` 
5. High-level Design
6. Deep Dive
   1. How to handle billions of simultaneous users?
   2. How to optimize the chat lookup?
   3. How to handle high throughput and global users?