package practice.concurrency.threadpool;

/**
 * <b>Description</b> :
 *
 * @author Vinod Akkepalli
 */
public class MyTask implements Runnable {
    private int num;

    public MyTask(int n) {
        num = n;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + ":: Task " + num + " is running.");
    }
}
