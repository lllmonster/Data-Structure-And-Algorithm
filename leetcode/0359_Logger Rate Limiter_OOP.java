/**
hashmap - o(1),o(m) 
This is the simplest way.
BUT the disadvantage to this solution is that the memory usage never stops growing.*/
class Logger {
    
    Map<String, Integer> map;
    
    public Logger() {
        map = new HashMap<>();
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message)) {
            map.put(message, timestamp);
            return true;
        }
        int time = map.get(message);
        if (timestamp - time >= 10) {
            map.put(message, timestamp);
            return true;
        }
        return false;
    }
}

/**
Queue+Set - o(1),o(n) 
It will control the memory usage.*/
class Logger {
    
    private LinkedList<Pair<String, Integer>> msgQueue;
    private Set<String> msgSet;
    
    public Logger() {
        msgQueue = new LinkedList<>();
        msgSet = new HashSet<>();
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        // clean the queue 
        while (msgQueue.size() > 0) {
            Pair<String, Integer> head = msgQueue.getFirst();
            if (timestamp - head.second >= 10) {
                msgQueue.removeFirst();
                msgSet.remove(head.first);
            } else {
                break;
            }
        }
        
        if (msgSet.contains(message)) {
            return false;
        } else {
            msgSet.add(message);
            msgQueue.addLast(new Pair<String,Integer>(message, timestamp));
            return true;
        }
    }
}

class Pair<U,V> {
    public U first;
    public V second;
    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}

/**
Threadsafe with synchronized lock*/
class Logger {
    
    private final Map<String, Integer> map;
    private final Object lock;
    
    public Logger() {
        map = new HashMap<>();
        lock = new Object();
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        synchronized (lock) {
            Integer ts = msgMap.get(message);
            if (ts == null || timestamp - ts >= 10) {
                msgMap.put(message, timestamp);
                return true;
            }
        }
        return false;
    }
}