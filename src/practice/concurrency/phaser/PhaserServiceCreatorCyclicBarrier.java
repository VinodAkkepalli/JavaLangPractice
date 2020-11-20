package practice.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * <b>Description</b> : A service thread to work and emulate CyclicBarrier like behavior
 *
 * @author Vinod Akkepalli
 */
public class PhaserServiceCreatorCyclicBarrier implements Runnable {
    Phaser phaser;
    String name;

    public PhaserServiceCreatorCyclicBarrier(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
    }

    @Override
    public void run() {
       do {
           System.out.println(this.name + " Started!!!");
           phaser.arriveAndAwaitAdvance();
//           System.out.println(this.name + " Finished!!!");
       } while (!phaser.isTerminated());    //needed for conditional termination of phases
    }
}
