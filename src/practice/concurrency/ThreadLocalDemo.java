package practice.concurrency;

/**
 * <b>Description</b> : Code to demonstrate ThreadLocal object
 *
 * @author Vinod Akkepalli
 */
public class ThreadLocalDemo implements Runnable {

    private Integer shareInteger;
    private ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ThreadLocalDemo sharedObject = new ThreadLocalDemo();
        Thread t1 = new Thread(sharedObject);
        Thread t2 = new Thread(sharedObject);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.start();
        System.out.println("**values accessed from main thread**");
        System.out.println("Shared Integer is: " + sharedObject.shareInteger);
        System.out.println("ThreadLocal Object reference is: " + sharedObject.integerThreadLocal);
        System.out.println("ThreadLocal Object valueis: " + sharedObject.integerThreadLocal.get());

    }

    @Override
    public void run() {

        synchronized (ThreadLocalDemo.class) {
            integerThreadLocal.set((int) (Math.random() * 100));
            shareInteger = (int) (Math.random() * 100);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //This Object is expected to be same for both threads
        System.out.println(Thread.currentThread().getName() + "::ThreadLocal Object : " + integerThreadLocal);
        //This value is local to each thread
        System.out.println(Thread.currentThread().getName() + "::ThreadLocal Integer value : " + integerThreadLocal.get());
        //This value is different for each thread, but final value is retained
        System.out.println(Thread.currentThread().getName() + "::shared Integer value: " + shareInteger);
    }
}
