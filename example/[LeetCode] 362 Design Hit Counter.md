Design a hit counter which counts the number of hits received in the past 5 minutes.  
[Reference](https://nuttynanaus.wordpress.com/2014/03/09/software-engineer-interview-questions/)  

## Method 1 : Using Queue
If we want full accuracy, we don't have much room for optimization. 
What we need is to record all the raw data. We can use a `queue` for the purpose.

**Pros**: Simple, accurate  
**Cons**: Cost more memory and less efficient.

```
class HitCounter {
public:
    /** Initialize your data structure here. */
    HitCounter() {
        
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    void hit(int timestamp) {
        q.push(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    int getHits(int timestamp) {
        while (timestamp - q.front() >= 300 && !q.empty()) {
            q.pop();
        }
        return q.size();
    }
    
private:
    queue<long> q;
};
```

## Method 2: Using Array which size is N
We can choose granularity to optimize this algorithm. We can have rolling window array of N elements to store the hit in each second.

**Pros** : less memory and higher efficient  
**Cons** : less accurate

```
class HitCounter {
public:
    /** Initialize your data structure here. */
    HitCounter() {
        N = 300;
        hits.resize(N, 0);  
        lastPosition = 0;
        lastTime = 0;
        sum = 0;
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    void hit(int timestamp) {
        move(timestamp);
        hits[lastPosition]++;
        sum++;
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    int getHits(int timestamp) {
        move(timestamp);
        return sum;
    }
    
private:
    vector<int> hits;
    int N;
    int lastPosition;
    int lastTime;
    int sum;
    
    void move(int timestamp) {
        int gap = min(N, timestamp - lastTime);
        for (int i = 0; i < gap; i++) {
            lastPosition = (lastPosition + 1) % N;
            sum -= hits[lastPosition];
            hits[lastPosition] = 0;
        }
        lastTime = timestamp;
    }
};
```

## Follow Up
If there are multiple threads accessing the structs, then there were two solutions.  
1. Have a lock on top it.  
2. Have thread-local version of the struct.  

Both version requires locks, however, the first solu, every hit may wait on the lock while the second, only
when calling `getcount()` may block the lock.  
In the real life, usually `hit()` is called much higher frequency than `getcount()`, so the 
second solution could perform better. But the second is also harder to implemented, 
so there is another trade-off need to be made, depends on the real requirement.

