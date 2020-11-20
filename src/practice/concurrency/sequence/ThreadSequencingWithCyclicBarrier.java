package practice.concurrency.sequence;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 
 * <b>Description</b> : Code to demonstrate usage of CyclicBarrier to make a set
 * of thread execute in a desired sequence. The following code executes the
 * three Threads T1, T2 & T3 in the reverse sequence T3, T2, T1
 * 
 * @author Vinod Akkepalli
 *
 */
public class ThreadSequencingWithCyclicBarrier implements Runnable {

	private final String name;
	private final CyclicBarrier count;

	public ThreadSequencingWithCyclicBarrier(String name, CyclicBarrier cdl) {
		this.name = name;
		this.count = cdl;
	}

	public String getName() {
		return name;
	}

	public static void main(String[] args) throws InterruptedException {

		CyclicBarrier count = new CyclicBarrier(3, new Runnable() {
			@Override
			public void run() {
				System.out
						.println("All threads completed execution in sequence");
			}
		});

		ThreadSequencingWithCyclicBarrier obj1 = new ThreadSequencingWithCyclicBarrier(
				"T1", count);
		ThreadSequencingWithCyclicBarrier obj2 = new ThreadSequencingWithCyclicBarrier(
				"T2", count);
		ThreadSequencingWithCyclicBarrier obj3 = new ThreadSequencingWithCyclicBarrier(
				"T3", count);

		Thread t1 = new Thread(obj1);
		Thread t2 = new Thread(obj2);
		Thread t3 = new Thread(obj3);

		t3.start();

		// sleep as long as t3 still did not call await()
		// in this case number of threads waiting will be 0
		while (count.getNumberWaiting() == 0) {
			Thread.sleep(1000);
		}

		t2.start();

		// sleep as long as t2 still did not call await()
		// in this case number of threads waiting will be 1
		while (count.getNumberWaiting() == 1) {
			Thread.sleep(1000);
		}
		t1.start();
	}

	@Override
	public void run() {

		Random r = new Random();

		try {
			synchronized (this) {
				this.wait(Math.abs(r.nextInt()) % 5000);
			}
			System.out.println(this.getName() + " in execution!!");
			this.count.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}

	}
}
