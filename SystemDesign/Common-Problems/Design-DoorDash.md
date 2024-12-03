[Ref](https://www.hellointerview.com/learn/system-design/problem-breakdowns/gopuff)

1. Functional Requirements
   1. select avaiable items, deliverable in 1 hour, by location
   2. order multiple items at the same time
   3. (optional) payment
   4. (optional) handle driver routing and deliveries
   5. (optional) Search functionality and catalog API
   6. (optional) Cancellations and returns
2. Non-Functional Requirements
   1. High Available for request or search
   2. Ordering should be strongly consistent
   3. support 10k DC and 100k items in the catalog
   4. order volume will be 10m order/day
   5. (optional) Privacy and Security, Diasater recovery
3. Core Entity
   1. Item
   2. Inventory
   3. Distribution Center
   4. Order
   5. Order item
4. API
   1. get availability of items given a location/keyword
   `GET /v1/availability?lat={LAT}&long={LONG}&keyword={}&page_size={} -> Items[]`
   2. place order
   `POST /v1/order   {lat, long, items} -> Succcess|Failure`
5. High-level Design
   1. Customer should be able to query availability of items
      1. nearby service -> DC table