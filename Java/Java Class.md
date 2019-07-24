### Future

`public interface Future<V>`

A `Future` represents the result of an asynchronous computation. Methods are provided to check if the computation is complete, to wait for its computation, and to retrieve the result of the computation.

Is useful when working with asynchronous calls and concurrent processing.

```java
public class SquareCalculator {
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    
    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }
}
```

### ThreadPoolExecutor

`public class ThreadPoolExecutor`

`extends AbstractExecutorService`

An `ExecutorService` that executes each submitted task using one of possibly several pooled threads, normally configured using `Executors` factory methods.

Thread pools address two different problems: 

1. they usually provide improved performance when executing large numbers of asynchronous tasks, due to reduced per-task invocation overhead

2. They provide a means of bounding and managing the resources, including threads, consumed when executing a collection of tasks.

   Each `ThreadPoolExecutor` also maintains some basic statistics, such as the number of completed tasks.

### Callable

`public interface Callable<V>`

A task that returns a result and may throw an exception. Implementors define a single method with no arguments called `call`

The `callable` interface is similar to `Runnable`, in that both are designed for classes whose instances are potentially executed by another thread. A `Runnable`, however, does not return a result and cannot throw a checked exception.

The `Executors` class contains utility methods to convert from other common forms to `callable` classes.