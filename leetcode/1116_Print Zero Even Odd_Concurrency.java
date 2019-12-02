/*
Semaphore, limit the number of thread to access the specific resource
Semaphore sem = new Semaphore(limitNumber), definition
acquire(), lock (means this step is locked & stopped and cannot go through)
release(), unlock

If the counter is greater than zero, then access is allowed. 
If it is zero, then access is denied. What the counter is counting are permits that allow access to the shared resource.
*/

/**
zero                odd                 even
s0-lock             s1-lock             s2-lock
0
s0-lock
s1-release
                    1
                    s0-release
                    s1-lock
0
s0-lock
s2-release                              2
                                        s0-release
                                        s2-lock
0
s0-lock
s1-release
                    3
                    s0-release
                    s1-lock
 */

/**
zero                odd                 even
                    s1-lock             s2-lock
0
s0-lock
s1-release
                    1
                    s0-release
                    s1-lock
0
s0-lock
s2-release                              2
                                        s0-release
                                        s2-lock
0
s0-lock
s1-release
                    3
                    s0-release
                    s1-lock
 */
class ZeroEvenOdd {
    
    private int n;

    private Semaphore s0 = new Semaphore(1);
    private Semaphore s1 = new Semaphore(0);
    private Semaphore s2 = new Semaphore(0);
    private boolean ifOdd = true;
    
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0 ; i < n; i++) {
            if (i == 0) s0.acquire();
                printNumber.accept(0);
                
                if (ifOdd) {
                    s1.release();
                } else {
                    s2.release();
                }
                ifOdd = !ifOdd;
                s0.acquire();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i+=2){
            if (i == 2) s2.acquire();
            printNumber.accept(i);
            s0.release();
            if (i+2 <= n) s2.acquire();
        }
            
        
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
       for (int i = 1; i <= n; i+=2) {
            if (i == 1) s1.acquire();
            printNumber.accept(i);
            s0.release();
            if (i+2 <= n) s1.acquire();
       }
        
    }
}