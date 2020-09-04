import java.util.concurrent.*;

class Share {
    static int count = 5;
}

class MyThread extends Thread {
    Semaphore s0;
    Semaphore s1;
    Semaphore s2;
    String threadName;
    boolean ifOdd;

    public MyThread(Semaphore s0, Semaphore s1, Semaphore s2, String threadName) {
        super(threadName);
        this.s0 = s0;
        this.s1 = s1;
        this.s2 = s2;
        this.threadName = threadName;
        this.ifOdd = true;
    }

    @Override
    public void run() {
        if (this.getName().equals("zero")) {
            try{
                for (int i = 0 ; i < Share.count; i++) {
                    System.out.println("zero thread start");  
                    if (i == 0) s0.acquire();
                    System.out.println(0);
                    
                    if (ifOdd) {
                        s1.release();
                    } else {
                        s2.release();
                    }
                    ifOdd = !ifOdd;
                    s0.acquire();
                    System.out.println("zero thread end");  
                }
            } catch (InterruptedException e) {

            }
        } else if (this.getName().equals("odd")){
            try{
                for (int i = 1; i <= Share.count; i+=2) {
                    System.out.println("odd thread start");  
                    if (i == 1) s1.acquire();
                    System.out.println(i);
                    s0.release();
                    if (i <= Share.count && i+2 <= Share.count) s1.acquire();
                    System.out.println("odd thread end");
                }
            } catch (InterruptedException e) {

            }
        } else {
            try {
                for (int i = 2; i <= Share.count; i+=2){
                    System.out.println("even thread start");  
                    if (i == 2) s2.acquire();
                    System.out.println(i);
                    s0.release();
                    if (i <= Share.count && i+2 <= Share.count) s2.acquire();
                    System.out.println("even thread end"); 
                }
            } catch (InterruptedException e) {

            }
        }
    }
}

public class SourceCode1116 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore s0 = new Semaphore(1);
        Semaphore s1 = new Semaphore(0);
        Semaphore s2 = new Semaphore(0);
        MyThread t0 = new MyThread(s0, s1, s2, "zero");
        MyThread t1 = new MyThread(s0, s1, s2, "odd");
        MyThread t2 = new MyThread(s0, s1, s2, "even");
        t0.start();
        t1.start();
        t2.start();
        t0.join();
        t1.join();
        t2.join();
        System.out.println("final count number is "+Share.count);
    }
}