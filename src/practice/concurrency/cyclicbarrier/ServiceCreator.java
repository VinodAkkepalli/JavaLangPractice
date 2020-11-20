package practice.concurrency.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 
 * <b>Description</b> : A runnable service creator class to help demo
 * CyclicBarrier
 * 
 * @author Vinod Akkepalli
 *
 */
public class ServiceCreator implements Runnable {

	final String name;
	final CyclicBarrier cb;

	public ServiceCreator(String serviceName, CyclicBarrier cb) {
		this.name = serviceName;
		this.cb = cb;
	}

	@Override
	public void run() {
		System.out.println(this.name + " is ready to Run!!");

		try {
			synchronized (this) {
				this.wait(1000);
			}
			this.cb.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			Thread.currentThread().interrupt();
		} catch (BrokenBarrierException e1) {
			e1.printStackTrace();
		}

		System.out.println(this.name + " is Up and Running!!");

	}
}