package concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * <b>Description</b> : A Runnable class which will be instantiated multiple
 * times The demo class will wait for the instances to call countDown() method
 * to proceed forward with its execution
 * 
 * @author Vinod Akkepalli
 *
 */

public class ServiceCreator implements Runnable {

	final String name;
	final CountDownLatch cdl;

	public ServiceCreator(String serviceName, CountDownLatch latch) {
		this.name = serviceName;
		this.cdl = latch;
	}

	@Override
	public void run() {

		System.out.println(this.name + " is ready to Run!!");
		try {
			synchronized (this) {
				this.wait(1000);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		this.cdl.countDown();
		System.out.println(this.name + " is Up and Running!!");
	}
}
