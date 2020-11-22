package concurrency.locks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * <b>Description</b> : Producer consumer synchronization using lock/ReentrantLock mechanism
 * 
 * @author Vinod Akkepalli
 *
 */

public class ThreadProducerConsumerDemo {

	private ThreadProducerConsumerDemo() {
	}

	public static void main(String[] args) {

		Queue<Integer> pool = new LinkedList<>();
		int poolSize = 10;

		Lock lock = new ReentrantLock();

		// condition1 for checking if the pool is empty
		final Condition isEmpty = lock.newCondition();
		// condition1 for checking if the pool is full
		final Condition isFull = lock.newCondition();


		ThreadProducer producer = new ThreadProducer(pool, poolSize, lock, isEmpty, isFull);
		ThreadConsumer consumer = new ThreadConsumer(pool, lock, isEmpty, isFull);

		(new Thread(producer)).start();
		(new Thread(consumer)).start();
	}

}
