## Concurrency

1. How to achieve thread synchronization?
   1. Use `synchronized` keyword to block one method or blocks to make sure only thread can access it at a time.  
   ```
    public synchronized void increment() {
        count++;
    }

    public void increment() {
        synchronized (this) {  // Synchronized block
            count++;
        }
    }
    ```
    2. use `ReentrantLock`, provides more flexible locking than `synchronized`, like try lock, time lock
    3. use `ReentrantReadWriteLock`, allow control for read and write separately
    4. use `Automic` variables
    5. use `Semaphore`, allows a limited number of threads to access
 2. Spin Lock: which allows thread to check repeatedlly, waiting for a lock to become available. 
 3. How to resolve dead lock?
    1. Lock Orderly
    2. Lock Timeout
    3. Deadlock Detection
    4. Lock free data structure
    5. Try to avoid nested lock
 4. How to resolve race condition?
    1. Synchronization
    2. Atomic Variables
    3. Volatile Keyword
    4. Thread safe collections
    5. Immutable Objects



    



* Race Condition  
When output is dependent upon the uncontrollable sequence of events. In some cases, it works (no bug), in some cases, it fails (bug) but in all cases, it suffers from a race condition.
* DeadLock  
Two or more resources are waiting for each other to finish and thus neither ever do finish.
* Resource Starvation  
When a process/thread is perpetually denied resources.  
* Atomicity  
An operation appears to occur to components of a system instantaneously.
* Mutual Exclusion  
For concurrent programs, mutual exclusion ensures that no two concurrent process/threads are in a critical section at the same time.   
* Locks  
A synchronization mechanism to limit access to a shared resource; a way of implementing mutual exclusion.  
* Semaphores  
A particular type of Lock. Records how many units of a resource are available in conjunction with providing a safe means of adjusting the record and potentially waiting for resources to become available.  

### Synchronization
Multi-threaded programs may often come to a situation when multiple threads try to access the same resources and finally produce erroneous and unforeseen results.
Synchronization method make sure that only thread can access the resource at a given point of time.
```java
synchronized(sync_object)
{
   // Access shared variables and other shared resources
}
```


## Threads

### Multi-thread Example
```Java
public class MultiThreadExample {

    public static void main(String[] args) {
        int n = 10; // Number of threads
        for (int i = 0; i < n; i++) {
            String threadName = "Thread" + i;
            MyMultiThread mythread = new MyMultiThread(threadName);
            mythread.start();
        }
    }
}

class MyMultiThread extends Thread {
    private String threadName;
    MyMultiThread( String name) {
        this.threadName = name;
    }
    public void run()
    {
        try {
            // the run function will be run parallel in 10 threads.
            // You can use this to test your pq.
            System.out.println("Thread " + threadName + " is running");
        }
        catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}

```

### Thread Local

#### What is Thread Local?

Thread Local can be considered as a scope of access, like a request scope or session scope. It's a *thread* scope. You can set any object in Thread Local and this object will be *global* and *local* to the specific thread which is accessing this object.

* Values stored in Thread Local are *global* to the thread, meaning that they can be accessed from anywhere inside that thread. If a thread calls methods from several classes, then all the methods can see the Thread Local variable set by other methods. The value need not be passed explicitly. It's like how you use global variables.
* Values stored in Thread Local are *local* to the thread, meaning that each thread will have its own Thread Local variable. One thread cannot access/modify other thread's Thread Local variables.

#### When to use Thread Local?

Consider you have a Servlet which calls some business methods. You have a requirement to generate a unique transaction id for each and every request this servlet process and you need to pass this transaction id to the business methods, for logging purpose. One solution would be passing this transaction id as a parameter to all the business methods. But this is not a good solution as the code is redundant and unnecessary.

To solve that, you can use Thread Local. You can generate a transaction id (either in servlet or better in a filter) and set it in the Thread Local. After this, whatever the business method, that this servlet calls, can access the transaction id from the thread local.

This servlet might be servicing more than one request at a time. Since each request is processed in separate thread, the transaction id will be unique to each thread local and will be accessible from all over the thread's execution (global).

#### How to use Thread Local?

```java
public class Context {
    private String transactionId = null;
    public void setTransactionId(String id) {
        this.transactionId = id;
    }
    public String getTransactionId() {
        return this.transactionId;
    }
}

public class MyThreadLocal {
    public static final ThreadLocal userThreadLocal = new ThreadLocal();
    public static void set(Context user) {
        userThreadLocal.set(user);
    }
    public static Context get() {
        return userThreadLocal.get();
    }
    public static void unset() {
        userThreadLocal.remove();
    }
}
public Demo extends Thread {
    public static void main(String args[]) {
        Thread t1 = new Demo();
        t1.start();
        Thread t2 = new Demp();
        t2.start();
    }
    @Override
    public void run() {
        Context cxt = new Context();
        cxt.setTransactionId("id");
        MyThreadLocal.set(cxt);
        new BusinessService().businessMethod();
        MyThreadLocal.unset();
    }
}
public class BusinessService {
    public void businessMethod() {
        Context cxt = MyThreadLocal.get();
        System.out.println(cxt.getTransactionId());
    }
}
```
