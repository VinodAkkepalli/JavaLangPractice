package practice.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Vinod Akkepalli
 * 
 *         Code to demonstrate ConcurrentHashMap vs HashTable vs SynchronizedMap
 *
 */

public class ConcurrentHashMapImpl {

	public final static int THREAD_POOL_SIZE = 5;

	public static Map<String, Integer> hashTableOb = null;
	public static Map<String, Integer> synchronizedMapOb = null;
	public static Map<String, Integer> concurrentHashMapOb = null;

	public static void main(String[] args) throws InterruptedException {

		// Test with Hashtable Object
		hashTableOb = new Hashtable<>();
		performTest(hashTableOb);

		// Test with synchronizedMap Object
		synchronizedMapOb = Collections
				.synchronizedMap(new HashMap<>());
		performTest(synchronizedMapOb);

		// Test with ConcurrentHashMap Object
		concurrentHashMapOb = new ConcurrentHashMap<>();
		performTest(concurrentHashMapOb);

	}

	public static void performTest(final Map<String, Integer> map)
			throws InterruptedException {

		System.out.println("Test started for: " + map.getClass());
		long averageTime = 0;

		for (int i = 0; i < 5; i++) {

			long startTime = System.nanoTime();
			ExecutorService threadPool = Executors
					.newFixedThreadPool(THREAD_POOL_SIZE);

			for (int j = 0; j < THREAD_POOL_SIZE; j++) {
				threadPool.execute(() -> {

					for (int i1 = 0; i1 < 500000; i1++) {
						Integer randomNumber = (int) Math
								.ceil(Math.random() * 550000);

						// Retrieve value. We are not using it anywhere
						Integer value = map
								.get(String.valueOf(randomNumber));

						// Put value
						map.put(String.valueOf(randomNumber), randomNumber);
					}
				});
			}

			// Make sure executor stops
			threadPool.shutdown();

			// Blocks until all tasks have completed execution after a shutdown
			// request
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

			long entTime = System.nanoTime();
			long totalTime = (entTime - startTime) / 1000000L;
			averageTime += totalTime;
			System.out.println(
					"2500K entried added/retrieved in " + totalTime + " ms");
		}
		System.out.println("For " + map.getClass() + " the average time is "
				+ averageTime / 5 + " ms\n");
	}
}