class ZeroEvenOdd {
    private int n;

    private int id = 1;
    private Semaphore s1 = new Semaphore(0);
    private Semaphore s2 = new Semaphore(0);
    private Semaphore s3 = new Semaphore(0);
    
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        printNumber.accept(0);
        s1.acquire();
        s3.acquire();
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        s2.release();
        if (id <= n) {
            printNumber.accept(id);
            id++;
            s3.release();
        }
        
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        s1.release();
        if (id <= n) {
            printNumber.accept(id);
            id++;
            s2.acquire();
        }
    }
}