/*
Semaphore, limit the number of thread to access the specific resource
Semaphore sem = new Semaphore(limitNumber), definition
acquire(), lock (means this step is locked & stopped and cannot go through)
release(), unlock
*/

class FooBar {
    private int n;
    private Semaphore s1 = new Semaphore(0);
    private Semaphore s2 = new Semaphore(0);
    
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            s1.release();
            s2.acquire();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            
            // printBar.run() outputs "bar". Do not change or remove this line.
            s1.acquire();
            printBar.run();
            s2.release();
        }
    }
}
