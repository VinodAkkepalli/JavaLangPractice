package practice.concurrency.semaphore;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ThreadConsumer implements Runnable {

	private final Queue<Integer> pool;
	private final String name;
	Semaphore producerSemaphore;
	Semaphore consumerSemaphore;

	public ThreadConsumer(Queue<Integer> poolQueue, String name, Semaphore ps, Semaphore cs) {
		this.pool = poolQueue;
		this.name = name;
		producerSemaphore = ps;
		consumerSemaphore = cs;
	}

	@Override
	public void run() {
		Integer i;
		while (true) {

			try {
				consumerSemaphore.acquire();
				i = pool.poll();
				System.out.println(name + " is consuming: " + i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			} finally {

				producerSemaphore.release();
			}
		}

	}

}
