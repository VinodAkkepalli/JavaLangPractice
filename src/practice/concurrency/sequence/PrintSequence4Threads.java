package practice.concurrency.sequence;

/**
 * <b>Description</b> :
 * Print numbers in sequence using 4 threads
 *
 * @author Vinod Akkepalli
 */

public class PrintSequence4Threads implements Runnable {
    int remainder;
    int numUpTo = 20;
    int noOfThreads;
    final Object lock;
    final MyValObject num;

    public PrintSequence4Threads(int remainder, int numThreads, Object lock, MyValObject num) {
        this.remainder = remainder;
        this.noOfThreads = numThreads;
        this.lock = lock;
        this.num = num;
    }

    public void run() {

        while (num.getValue() < numUpTo) {
            synchronized (lock) {
                while (num.getValue() % noOfThreads != remainder) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(" value = " + num.getValue() + " " +Thread.currentThread().getName());
                num.setValue(num.getValue() + 1);
                lock.notifyAll();
            }

        }
    }

    public static void main(String[] args) {
        Object ob = new Object();
        MyValObject num = new MyValObject(0);

        new Thread(new PrintSequence4Threads(0, 4, ob, num)).start();
        new Thread(new PrintSequence4Threads(1, 4, ob, num)).start();
        new Thread(new PrintSequence4Threads(2, 4, ob, num)).start();
        new Thread(new PrintSequence4Threads(3, 4, ob, num)).start();
    }

}

class MyValObject {
    int value;

    public MyValObject(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}