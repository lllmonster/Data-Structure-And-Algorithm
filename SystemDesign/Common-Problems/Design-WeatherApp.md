[Ref](https://www.tryexponent.com/courses/system-design-interviews/design-weather-app)

1. Functional Requirements
   1. Get weather information from third-party (Temperature, Humidity, Air Quality, Pollen Index, Predict)
   2. Based on zip code, give current weather and forecast, weather history
2. Non-Functional Requirements
   1. Accuracy and latest information
   2. Scalability
   3. Low latency
   4. High Available
   5. Eventual Consistent
3. Estimation
   1. every 5 min, we get a update
   2. 100M daily users and one query per day -> 100M/100k sec = 1000 rps
   3. during peak time, there will 10x load -> 10k rps
   4. Read-heavy system
4. API
   1. Fetch weather based on zip code
   `GET /weather?{zipCode} -> {Temperature, CityName, Prediction, Sunrise/Sunset ...}`
5. High-level Design
   1. DB : use a time-series db (influx DB) to store weather information. Considering scalability, nosql is a good choice.
6. Deep Dive
   1. Peek time: 
      1. we can add more nodes for our current weather service.
      2. Add some cache which will reduce query time, that will help scales and keep latency very low.
      3. Add CDN.
   2. What's the index strategy? How to query data?
      1. locationId, and date
   