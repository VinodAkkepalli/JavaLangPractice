package practice.concurrency.synchronization;

import java.util.Queue;

/**
 * 
 * <b>Description</b> This producer class pushes random integers into a queue of
 * size maxSize
 * 
 * @author Vinod Akkepalli
 *
 */
public class SynchronizationProducer implements Runnable {

	private final Queue<Integer> pool;
	private final String name;
	private final int maxSize;

	public SynchronizationProducer(Queue<Integer> poolQueue, int maxSize,
			String name) {

		this.pool = poolQueue;
		this.name = name;
		this.maxSize = maxSize;
	}

	@Override
	public void run() {

		int i;

		while (true) {
			synchronized (pool) {

				while (pool.size() == maxSize) {
					try {
						System.out.println(
								"Queue is full, PRODUCER is waiting for CONSUMER action!!");
						pool.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}
				}

				i = (int) (Math.random() * 10);
				System.out.println(name + " produced item: " + i);
				pool.add(i);

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
