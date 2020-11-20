package practice.concurrency.synchronousqueue;

import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

/**
 * 
 * <b>Description</b> : This class is a producer in this producer consumer code.
 * This has a SynchronousQueue to help communicate with consumer.
 * 
 * @author Vinod Akkepalli
 *
 */

public class SynchronousQueueProducer implements Runnable {

	private SynchronousQueue<Integer> queue;

	public SynchronousQueueProducer(Queue<Integer> queue) {
		this.queue = (SynchronousQueue<Integer>) queue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				System.out.println(Thread.currentThread().getName()
						+ " about to produce a string!!!");
				int i = (int) (Math.random() * 10);
				queue.put(i);
				System.out.println(Thread.currentThread().getName()
						+ " produced: " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

}
