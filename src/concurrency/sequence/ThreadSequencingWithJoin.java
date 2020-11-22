package concurrency.sequence;

import java.util.Random;

/**
 * 
 * <b>Description</b> : Code to demonstrate usage of Join() method to make a set
 * of thread execute in a desired sequence. The following code executes the
 * three Threads T1, T2 & T3 in the reverse sequence T3, T2, T1
 * 
 * @author Vinod Akkepalli
 *
 */

public class ThreadSequencingWithJoin implements Runnable {

	private final String name;

	public ThreadSequencingWithJoin(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadSequencingWithJoin obj1 = new ThreadSequencingWithJoin("T1");
		ThreadSequencingWithJoin obj2 = new ThreadSequencingWithJoin("T2");
		ThreadSequencingWithJoin obj3 = new ThreadSequencingWithJoin("T3");

		Thread t1 = new Thread(obj1);
		Thread t2 = new Thread(obj2);
		Thread t3 = new Thread(obj3);

		t3.start();

		t3.join();
		t2.start();

		t2.join();
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
	}
}
