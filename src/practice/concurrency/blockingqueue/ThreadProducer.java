package practice.concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * 
 * <b>Description</b> : Producer class which pushes random integers into the
 * pool which is a BlockingQueue and a shared resource
 * 
 * @author Vinod Akkepalli
 *
 */

public class ThreadProducer implements Runnable {

	public BlockingQueue<Integer> pool;
	public int capacity;

	public ThreadProducer(BlockingQueue<Integer> bq, int size) {
		this.pool = bq;
		this.capacity = size;
	}

	@Override
	public void run() {
		while (true) {
			try {

				if (pool.size() == capacity) {
					System.out.println(
							"BlockingQueue is full, PRODUCER is waiting for CONSUMER action!!");
				}

				int i = (int) (Math.random() * 10);
				pool.put(i);
				System.out.println("PRODUCER has produced an item: " + i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
}
