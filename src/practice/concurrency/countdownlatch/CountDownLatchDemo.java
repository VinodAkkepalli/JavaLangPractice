package practice.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * <b>Description</b> : code to demonstrate CountDownLatch
 * CountDownLatch waits for the specified number of tasks to complete their execution before
 * proceeding forward with its execution
 * 
 * @author Vinod Akkepalli
 *
 */

public class CountDownLatchDemo {

	private CountDownLatchDemo() {
	}

	public static void main(String[] args) {

		// CountDownLatch to wait for 5 threads to call coutDown()
		final CountDownLatch cdl = new CountDownLatch(4);

		ServiceCreator sc1 = new ServiceCreator("EarthService", cdl);
		ServiceCreator sc2 = new ServiceCreator("WaterService", cdl);
		ServiceCreator sc3 = new ServiceCreator("FireService", cdl);
		ServiceCreator sc4 = new ServiceCreator("AirService", cdl);
		ServiceCreator sc5 = new ServiceCreator("PeaceService", cdl);

		new Thread(sc1).start();
		new Thread(sc2).start();
		new Thread(sc3).start();
		new Thread(sc4).start();
		new Thread(sc5).start();

		try {
			// CountDownLatch will wait for 5 threads to call countDown()
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}

		System.out.println(
				"CountDownLatch finished waiting and MainThread is in execution!!");
	}
}
