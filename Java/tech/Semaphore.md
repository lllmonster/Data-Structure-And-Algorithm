## Semaphore

### Introduction

In java, we can use *Semaphore* to limit the number of threads to access a certain resource.

In short, a semaphore maintains a set of permits (tickets), each **acquire()** will take a permit (ticket) from semaphore, each **release()** will return back the permit (ticket) back to the semaphore. If permits are not available, **acquire()** will block until one is available.

```java
package com.mkyong.concurrency.synchronizer.semaphore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

// Throttle task submission
public class TaskLimitSemaphore {

    private final ExecutorService executor;
    private final Semaphore semaphore;

    public TaskLimitSemaphore(ExecutorService executor, int limit) {
        this.executor = executor;
        this.semaphore = new Semaphore(limit);
    }

    public <T> Future<T> submit(final Callable<T> task) throws InterruptedException {
        
        semaphore.acquire();
        System.out.println("semaphore.acquire()...");

        return executor.submit(() -> {
            try {
                return task.call();
            } finally {
                semaphore.release();
                System.out.println("semaphore.release()...");
            }
        });

    }

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();

        // Only 2 tasks are able to run concurrently
        TaskLimitSemaphore obj = new TaskLimitSemaphore(executor, 2);

        obj.submit(() -> {
            System.out.println(getCurrentDateTime() + " : task1 is running!");
            Thread.sleep(2000);
            System.out.println(getCurrentDateTime() + " : task1 is done!");
            return 1;
        });

        obj.submit(() -> {
            System.out.println(getCurrentDateTime() + " : task2 is running!");
            Thread.sleep(2000);
            System.out.println(getCurrentDateTime() + " task2 is done!");
            return 2;
        });

        obj.submit(() -> {
            System.out.println(getCurrentDateTime() + " task3 is running!");
            Thread.sleep(2000);
            System.out.println(getCurrentDateTime() + " task3 is done!");
            return 3;
        });

        obj.submit(() -> {
            System.out.println(getCurrentDateTime() + " task4 is running!");
            Thread.sleep(2000);
            System.out.println(getCurrentDateTime() + " task4 is done!");
            return 4;
        });

        obj.submit(() -> {
            System.out.println(getCurrentDateTime() + " task5 is running!");
            Thread.sleep(2000);
            System.out.println(getCurrentDateTime() + " task5 is done!");
            return 5;
        });

        executor.shutdown();
    }

    private static String getCurrentDateTime() {
        return sdf.format(new Date());
    }
}

```

output

```
semaphore.acquire()...
semaphore.acquire()...
2018/12/06 18:45:22 : task1 is running!
2018/12/06 18:45:22 : task2 is running!
2018/12/06 18:45:24 : task1 is done!
2018/12/06 18:45:24 task2 is done!
semaphore.release()...
semaphore.acquire()...
semaphore.release()...
semaphore.acquire()...
2018/12/06 18:45:24 task3 is running!
2018/12/06 18:45:24 task4 is running!
2018/12/06 18:45:26 task4 is done!
2018/12/06 18:45:26 task3 is done!
semaphore.acquire()...
semaphore.release()...
semaphore.release()...
2018/12/06 18:45:26 task5 is running!
2018/12/06 18:45:28 task5 is done!
semaphore.release()...
```

