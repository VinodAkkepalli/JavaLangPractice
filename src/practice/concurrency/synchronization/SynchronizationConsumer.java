package practice.concurrency.synchronization;

import java.util.Queue;

/**
 * 
 * <b>Description</b> : This consumer class consumes integers that are available
 * in the queue of size maxSize
 * 
 * @author Vinod Akkepalli
 *
 */

public class SynchronizationConsumer implements Runnable {

	private final Queue<Integer> pool;
	private final String name;

	public SynchronizationConsumer(Queue<Integer> poolQueue, String name) {
		this.pool = poolQueue;
		this.name = name;
	}

	@Override
	public void run() {
		int i;
		while (true) {
			synchronized (pool) {

				while (pool.isEmpty()) {

					System.out.println(
							"Queue is empty, CONSUMER is waiting for PRODUCER action!!");
					try {
						pool.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}

				}

				i = pool.poll();
				System.out.println(name + " is consuming: " + i);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}

				pool.notifyAll();

			}

		}
	}

}
