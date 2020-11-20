package practice.concurrency;

/**
 * <b>Description</b> :
 *
 * @author Vinod Akkepalli
 */
public class InterruptedExceptionDemo implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(4000);
            if(Thread.interrupted()){
                System.out.println("I have been interrupted!");
            }
        } catch (InterruptedException e) {
            //To preserve the interruption; observe isInterrupted() value on the next line
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() +
                    " Inside catch block:: isInterrupted() value: " + Thread.currentThread().isInterrupted());
            e.printStackTrace();
            return;
        }
        System.out.println(Thread.currentThread().getName() + " is Running");
    }

    public static void main(String[] args) {
        Thread t0= new Thread(new InterruptedExceptionDemo());
        t0.start();
        System.out.println(t0.getName() + " isInterrupted? : " + t0.isInterrupted());

        Thread t1= new Thread(new InterruptedExceptionDemo());
        t1.start();
        t1.interrupt();
        System.out.println(t1.getName() + " isInterrupted? : " + t1.isInterrupted());
    }
}