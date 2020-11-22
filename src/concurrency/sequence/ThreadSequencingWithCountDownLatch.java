package concurrency.sequence;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 
 * <b>Description</b> : Code to demonstrate usage of CountDownLatch to make a
 * set of thread execute in a desired sequence. The following code executes the
 * three Threads T1, T2 & T3 in the reverse sequence T3, T2, T1
 * 
 * @author Vinod Akkepalli
 *
 */

public class ThreadSequencingWithCountDownLatch implements Runnable {

	private final String name;
	private final CountDownLatch count;

	public ThreadSequencingWithCountDownLatch(String name, CountDownLatch cdl) {
		this.name = name;
		this.count = cdl;
	}

	public String getName() {
		return name;
	}

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch count = new CountDownLatch(3);

		ThreadSequencingWithCountDownLatch obj1 = new ThreadSequencingWithCountDownLatch(
				"T1", count);
		ThreadSequencingWithCountDownLatch obj2 = new ThreadSequencingWithCountDownLatch(
				"T2", count);
		ThreadSequencingWithCountDownLatch obj3 = new ThreadSequencingWithCountDownLatch(
				"T3", count);

		Thread t1 = new Thread(obj1);
		Thread t2 = new Thread(obj2);
		Thread t3 = new Thread(obj3);

		t3.start();

		// keep waiting as long as t3 did not countDown
		while (count.getCount() == 3) {
			Thread.sleep(1000);
		}
		t2.start();

		// keep waiting as long as t2 did not countDown
		while (count.getCount() == 2) {
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
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		System.out.println(this.getName() + " in execution!!");
		this.count.countDown();
	}
}
