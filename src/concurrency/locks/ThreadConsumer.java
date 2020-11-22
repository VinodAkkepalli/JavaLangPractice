package concurrency.locks;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 
 * <b>Description</b> : consumer class which uses lock mechanism to achieve
 * synchronization
 * 
 * @author Vinod Akkepalli
 *
 */
public class ThreadConsumer implements Runnable {

	private Queue<Integer> pool;
	private Lock lock;

	// condition1 for checking if the pool is empty
	final Condition isEmpty;
	// condition1 for checking if the pool is full
	final Condition isFull;

	public ThreadConsumer(Queue<Integer> poolObject, Lock lock, Condition isEmpty, Condition isFull) {
		this.pool = poolObject;
		this.lock = lock;
		this.isEmpty = isEmpty;
		this.isFull = isFull;
	}

	@Override
	public void run() {
		Integer poolElement;

		while (true) {
			lock.lock();
			try {
				if (pool.isEmpty()) {
					System.out.println(
							"Queue is empty, CONSUMER is waiting for PRODUCER action!!");
					isEmpty.await();
				}

				poolElement = pool.poll();

				if (poolElement != null) {
					System.out.println("CONSUMER consumed item: " + poolElement);
					isFull.signalAll();
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}


	}

}
