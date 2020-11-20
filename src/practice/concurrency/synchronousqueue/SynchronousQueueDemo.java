package practice.concurrency.synchronousqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * 
 * <b>Description</b> : SynchronousQueue usability in thread communication
 * scenario. SynchronousQueue is a blocking queue in which each insert operation
 * must wait for a corresponding remove operation by another thread, and vice
 * versa.
 * 
 * @author Vinod Akkepalli
 *
 */
public class SynchronousQueueDemo {

	private static SynchronousQueue<Integer> queue = new SynchronousQueue<>();

	public static void main(String[] args) {

		Thread t1 = new Thread(new SynchronousQueueProducer(queue), "PRODUCER");
		Thread t2 = new Thread(new SynchronousQueueConsumer(queue), "CONSUMER");

		t1.start();
		t2.start();
	}
}
