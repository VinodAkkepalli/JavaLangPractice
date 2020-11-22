package concurrency.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 
 * <b>Description</b> : Multi-Threading CyclicBarrier demo code to achieve
 * thread execution flow control
 * 
 * @author Vinod Akkepalli
 *
 */

public class CyclicBarrierDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//This CyclicBarrier will wait for 4 parties(threads) to arrive to break the barrier
		//then starts waiting for the next 4 parties to arrive
		final CyclicBarrier cb = new CyclicBarrier(4, new Runnable() {
			@Override
			public void run() {
				// This task will be executed once all thread reaches barrier
				System.out.println(
						"All parties are arrived at barrier, lets start execution!!!");
			}
		});

		ServiceCreator sc1 = new ServiceCreator("EarthService", cb);
		ServiceCreator sc2 = new ServiceCreator("WaterService", cb);
		ServiceCreator sc3 = new ServiceCreator("FireService", cb);
		ServiceCreator sc4 = new ServiceCreator("AirService", cb);
		ServiceCreator sc5 = new ServiceCreator("PeaceService", cb);

		ServiceCreator sc6 = new ServiceCreator("Service-6", cb);
		ServiceCreator sc7 = new ServiceCreator("Service-7", cb);
		ServiceCreator sc8 = new ServiceCreator("Service-8", cb);
		ServiceCreator sc9 = new ServiceCreator("Service-9", cb);
		ServiceCreator sc10 = new ServiceCreator("Service-10", cb);
		
		//Observe that though we have 10 parties started and waiting, 
		//only 8(4X2) will be able to finish
		new Thread(sc1).start();
		new Thread(sc2).start();
		new Thread(sc3).start();
		new Thread(sc4).start();
		new Thread(sc5).start();
		
		new Thread(sc6).start();
		new Thread(sc7).start();
		new Thread(sc8).start();
		new Thread(sc9).start();
		new Thread(sc10).start();

		System.out.println("Message from main thread. " +
				"Main thread does not depend on any of the other threads");
	}

}
