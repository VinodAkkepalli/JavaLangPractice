package practice.concurrency.phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * <b>Description</b> : Phaser usage performing CountdonwnLatch like functionality
 *
 * @author Vinod Akkepalli
 */
public class PhaserAsCyclicbarrierDemo {

    public static void main(String[] args) {
        int numPhasesToExecute = 5;
        Phaser ph = new Phaser(3) {
            //Needed for keep track of phases
            //to continue and terminate advance to next phases
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("Phase " + phase + " completed.\n");
                return phase == (numPhasesToExecute - 1) || registeredParties == 0;
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(6);

        executor.submit(new PhaserServiceCreatorCyclicBarrier(ph, "EarthService"));
        executor.submit(new PhaserServiceCreatorCyclicBarrier(ph, "WaterService"));
        executor.submit(new PhaserServiceCreatorCyclicBarrier(ph, "FireService"));
        executor.submit(new PhaserServiceCreatorCyclicBarrier(ph, "AirService"));
        executor.submit(new PhaserServiceCreatorCyclicBarrier(ph, "PeaceService"));

        System.out.println("Phaser started waiting in main thread!!");

//        ph.arriveAndAwaitAdvance();
        System.out.println("Phaser finished waiting in main thread!!");
    }
}