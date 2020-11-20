package practice.concurrency.sequence;

/**
 * <b>Description</b> :
 *
 * @author Vinod Akkepalli
 */
public class EvenThread implements Runnable {

    final PrintNumbersInSequence obj;

    public EvenThread(PrintNumbersInSequence obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        while(obj.getValue() < 100) {
            synchronized (obj) {
                while (obj.getValue() % 2 == 1) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Even Thread: " + obj.getValue());
                obj.setValue(obj.getValue() + 1);
                obj.notify();
            }
        }
    }
}
