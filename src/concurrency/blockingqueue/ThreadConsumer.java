package concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * 
 * 
 * <b>Description</b> : consumer class which gets integers from the pool which
 * is a BlockingQueue and a shared resource
 * 
 * @author Vinod Akkepalli
 *
 */
public class ThreadConsumer implements Runnable {


	public BlockingQueue<Integer> pool;

	public ThreadConsumer(BlockingQueue<Integer> bq) {
		this.pool = bq;
	}

	@Override
	public void run() {
		int i;
		while (true) {
			try {
				if (pool.isEmpty()) {
					System.out.println(
							"BlockingQueue is empty, CONSUMER is waiting for PRODUCER action!!");
				}
				i = pool.take();
				System.out.println("CONSUMER consumed item: " + i);

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}

}
