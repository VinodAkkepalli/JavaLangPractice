package practice.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * <b>Description</b> : A service thread to work and emulate CountdownLatch like behavior
 *
 * @author Vinod Akkepalli
 */
public class PhaserServiceForCountdown implements Runnable {
    Phaser phaser;
    String name;

    public PhaserServiceForCountdown(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(this.name + " Started!!!");
        phaser.arriveAndAwaitAdvance(); //this awaitAdvance acts as a barrier
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.name + " Finished!!!");
        //This arrive can be used to make all threads finish before
        //main thread awaitAdvance makes further progress
//        phaser.arrive();
    }
}
