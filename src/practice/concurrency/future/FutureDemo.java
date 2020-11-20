package practice.concurrency.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * <b>Description</b> :Code to demonstrate Future object usage during
 * asynchronous query processing
 * 
 * @author Vinod Akkepalli
 *
 */

class FutureDemo {

	public static final ExecutorService threadPool = Executors
			.newFixedThreadPool(3);

	public static void main(String[] args)
			throws InterruptedException, ExecutionException {

		DelayedResponseTask task = new DelayedResponseTask(10);
		System.out.println("Submitting Task ...");

		//ExecutorService.submit(Callable) takes a Callable as input task
		//ExecutorService.execute(Runnable) takes a Runnable as input task
		Future<Integer> future = threadPool.submit(task);
		System.out.println("Task is submitted");
		System.out.println("Thread name is: " + Thread.currentThread().getName());

		while (!future.isDone()) {
			System.out.println("Task is not completed yet....");
			Thread.sleep(1); // sleep for 1 millisecond before checking again
		}

		System.out.println("Task is completed, let's check result");
		//get() method collects the return value from Callable
		int factorial = future.get();	//Blocking

		System.out.println("Factorial of 10 is : " + factorial);
		
		//We need to shutdown an ExecutorService after use   
		threadPool.shutdown();
	}
}
