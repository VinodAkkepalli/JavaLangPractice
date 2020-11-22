package concurrency.synchronousqueue;

import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

/**
 * 
 * <b>Description</b> : This class is a consumer in this producer consumer code.
 * This has a SynchronousQueue to help communicate with producer.
 * 
 * @author Vinod Akkepalli
 *
 */

public class SynchronousQueueConsumer implements Runnable {

	private SynchronousQueue<Integer> queue;

	public SynchronousQueueConsumer(Queue<Integer> queue) {
		this.queue = (SynchronousQueue<Integer>) queue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				System.out.println(Thread.currentThread().getName()
						+ " waiting for the string!!!");
				Integer i = queue.take();
				System.out.println(Thread.currentThread().getName()
						+ " consuming: " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}

	}

}
