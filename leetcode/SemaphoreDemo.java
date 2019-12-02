import java.util.concurrent.*;

class Share {
    static int count = 0;
}

class MyThread extends Thread {
    Semaphore sem;
    String threadName;

    public MyThread(Semaphore sem, String threadName) {
        super(threadName);
        this.sem = sem;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        if (this.getName().equals("A")) {
            try{
                System.out.println(threadName+" is getting permits");
                sem.acquire();
                System.out.println(threadName+" got permits");
                for (int i = 0 ; i < 5 ; i++) {
                    System.out.println(threadName+" share count is "+Share.count);
                    Share.count++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {

            }
            System.out.println(threadName+" is releasing permits");
            sem.release();
        } else {
            try{
                System.out.println(threadName+" is getting permits");
                sem.acquire();
                System.out.println(threadName+" got permits");
                for (int i = 0 ; i < 5 ; i++) {
                    System.out.println(threadName+" share count is "+Share.count);
                    Share.count--;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {

            }
            System.out.println(threadName+" is releasing permits");
            sem.release();
        }
    }
}

public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(1);
        MyThread t1 = new MyThread(sem, "A");
        MyThread t2 = new MyThread(sem, "B");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("final count number is "+Share.count);
    }
}