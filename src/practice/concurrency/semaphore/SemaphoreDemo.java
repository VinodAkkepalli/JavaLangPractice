package practice.concurrency.semaphore;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * 
 * <b>Description</b> : solution to producer consumer problem using binary
 * Semaphore. producer and consumer classes use two semaphores to achieve exclusive access
 * 
 * @author Vinod Akkepalli
 *
 */

public class SemaphoreDemo {

	private static final Semaphore producerSemaphore = new Semaphore(1);
	//semaphore initialized with 0 to make consumer wait for producer to start first
	private static final Semaphore consumerSemaphore = new Semaphore(0);
	protected static final Queue<Integer> pool = new LinkedList<>();

	private SemaphoreDemo() {
	}

	public static void main(String[] args) {

		ThreadProducer producer = new ThreadProducer(pool,
				"PRODUCER", producerSemaphore, consumerSemaphore);
		ThreadConsumer consumer = new ThreadConsumer(pool,
				"CONSUMER", producerSemaphore, consumerSemaphore);

		new Thread(producer).start();
		new Thread(consumer).start();
	}

}
