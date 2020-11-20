package practice.concurrency.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * <b>Description</b> : Multi-Threading demo using producer-consumer problem. We
 * used BlockingQueue instead of using a simple Queue. A BlockingQueue manages
 * blocking and waking up producers and consumers as per the size of the Queue
 * by itself
 * 
 * @author Vinod Akkepalli
 *
 */

public class ThreadProducerConsumerDemo {

	private ThreadProducerConsumerDemo() {
	}

	public static void main(String[] args) {

		int poolSize = 2;
		BlockingQueue<Integer> pool = new ArrayBlockingQueue<>(poolSize);

		ThreadProducer producer = new ThreadProducer(pool, poolSize);
		ThreadProducer producer2 = new ThreadProducer(pool, poolSize);
		ThreadConsumer consumer = new ThreadConsumer(pool);
		ThreadConsumer consumer2 = new ThreadConsumer(pool);

		(new Thread(producer)).start();
		(new Thread(producer2)).start();
		(new Thread(consumer)).start();
		(new Thread(consumer2)).start();
	}

}
