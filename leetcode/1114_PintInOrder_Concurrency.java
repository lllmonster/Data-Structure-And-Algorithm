import java.util.concurrent.Semaphore;

/*
Semaphore, limit the number of thread to access the specific resource
Semaphore sem = new Semaphore(limitNumber), definition
acquire(), lock
release(), unlock
*/

class Foo {
    
    Semaphore run2;
    Semaphore run3;

    public Foo() {
        run2 = new Semaphore(0);
        run3 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        run2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        run2.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        run3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        run3.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

/*
CoundDownLatch is similar.
It is used to make sure that a task waits for other threads before it starts.
When we create an object of CoundDownLatch, we specify the number of threads it should wait for.
countDown(), unlock.
await(), lock.
*/

import java.util.concurrent.CountDownLatch;

class Foo {
    
    CountDownLatch run2;
    CountDownLatch run3;

    public Foo() {
        run2 = new CountDownLatch(1);
        run3 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        run2.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        run2.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        run3.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        run3.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}