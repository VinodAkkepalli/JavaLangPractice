package practice.concurrency.semaphore;

import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * 
 * <b>Description</b> : SynchronizationProducer class is using binary semaphore
 * to mutually exclude its instances when writing to queue.
 * 
 * @author Vinod Akkepalli
 *
 */

public class ThreadProducer implements Runnable {

	private final Queue<Integer> pool;
	private final String name;
	private final Semaphore producerSemaphore;
	private final Semaphore consumerSemaphore;


	public ThreadProducer(Queue<Integer> poolQueue, String name, Semaphore ps, Semaphore cs) {
		this.pool = poolQueue;
		this.name = name;
		this.producerSemaphore = ps;
		this.consumerSemaphore = cs;
	}

	@Override
	public void run() {
		int i;
		while (true) {
			try {
				producerSemaphore.acquire();

				i = (int) (Math.random() * 10);
				System.out.println(name + " produced : " + i);
				pool.add(i);
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
				Thread.currentThread().interrupt();
			} finally {
				consumerSemaphore.release();
			}
		}

	}

}
