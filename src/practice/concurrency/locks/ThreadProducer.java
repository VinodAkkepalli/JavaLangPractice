package practice.concurrency.locks;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 
 * <b>Description</b> : producer class which uses lock mechanism to achieve
 * synchronization
 * 
 * @author Vinod Akkepalli
 *
 */
public class ThreadProducer implements Runnable {

	private final Queue<Integer> pool;
	private final int capacity;
	Lock lock;

	// condition1 for checking if the pool is empty
	private final Condition isEmpty;
	// condition1 for checking if the pool is full
	private final Condition isFull;

	public ThreadProducer(Queue<Integer> poolObject, int size, Lock lock, Condition isEmpty, Condition isFull) {
		this.pool = poolObject;
		this.capacity = size;
		this.lock = lock;
		this.isEmpty = isEmpty;
		this.isFull = isFull;
	}

	@Override
	public void run() {
		int i;
		boolean isAdded;
		while (true) {
//			lock.lock();
			try {
				lock.tryLock(1000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				if (pool.size() == this.capacity) {
					System.out.println(
							"Queue is full, PRODUCER is waiting for CONSUMER action!!");
					isFull.await();
				}

				i = (int) (Math.random() * 10);
				isAdded = pool.add(i);

				if (isAdded) {
					System.out.println("PRODUCER has produced an item: " + i);
					isEmpty.signalAll();
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
