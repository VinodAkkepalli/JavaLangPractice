package practice.concurrency.phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * <b>Description</b> : Phaser usage performing CountdonwnLatch like functionality
 *
 * @author Vinod Akkepalli
 */
public class PhaserAsCountdownLatchDemo {

    public static void main(String[] args) {
        //Number of parties needed to advance to next phase
        Phaser ph = new Phaser(6);
        ExecutorService executor = Executors.newFixedThreadPool(6);

        executor.submit(new PhaserServiceForCountdown(ph, "EarthService"));
        executor.submit(new PhaserServiceForCountdown(ph, "WaterService"));
        executor.submit(new PhaserServiceForCountdown(ph, "FireService"));
        executor.submit(new PhaserServiceForCountdown(ph, "AirService"));
        executor.submit(new PhaserServiceForCountdown(ph, "PeaceService"));

        System.out.println("Phaser started waiting in main thread!!");

        ph.arriveAndAwaitAdvance();           //Makes main thread wait here
//        ph.arriveAndDeregister();   //Main thread does not wait here
        System.out.println("Phaser finished waiting in main thread!!");
    }
}
