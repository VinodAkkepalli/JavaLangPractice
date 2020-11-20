package practice.concurrency.sequence;

/**
 * <b>Description</b> : Print numbers in sequence using thread synchronization
 *
 * @author Vinod Akkepalli
 */

public class PrintNumbersInSequence {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

	public static void main(String[] args) {

	    PrintNumbersInSequence seqObj = new PrintNumbersInSequence();

        Thread t1 = new Thread(new OddThread(seqObj));
        Thread t2 = new Thread(new EvenThread(seqObj));

        t1.start();
        t2.start();
    }
}
