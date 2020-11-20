package practice.concurrency.sequence;

/**
 * <b>Description</b> :
 *
 * @author Vinod Akkepalli
 */
public class OddThread implements Runnable {

    final PrintNumbersInSequence obj;

    public OddThread(PrintNumbersInSequence obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        while(obj.getValue() < 100) {
            synchronized (obj) {
                while (obj.getValue() % 2 == 0) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Odd Thread: " + obj.getValue());
                obj.setValue(obj.getValue() + 1);
                obj.notify();
            }
        }
    }
}
