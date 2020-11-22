package concurrency.futuretask;

import concurrency.future.DelayedResponseTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 
 * <b>Description</b> : Demonstration of FutureTask which can be used to wrap a
 * Runnable or Callable Object. FutureTask can be submitted to an executor pool.
 * This class provides a base implementation of Future,
 * with methods to start and cancel a computation, query to see if the
 * computation is complete, and retrieve the result of the computation
 * 
 * @author Vinod Akkepalli
 *
 */
public class FutureTaskDemo {

	public static void main(String[] args)
			throws InterruptedException, ExecutionException {

		DelayedResponseTask task1 = new DelayedResponseTask(12);
		DelayedResponseTask task2 = new DelayedResponseTask(20);

		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		FutureTask<Integer> futureTask1 = new FutureTask<>(task1);
		FutureTask<Integer> futureTask2 = new FutureTask<>(task2);

		System.out.println("Submitting Task1 ...");
		threadPool.execute(futureTask1);
		System.out.println("Task1 is submitted");

		while (!futureTask1.isDone()) {
			System.out.println("Task1 is not complete yet....");
			Thread.sleep(100); // sleep for 1 millisecond before checking again
		}

		System.out.println("Task1 is completed, let's check result");
		int factorial = futureTask1.get();

		System.out.println("Factorial of 12 is : " + factorial);

		System.out.println("Submitting Task2 ...");
		threadPool.execute(futureTask2);
		System.out.println("Task2 is submitted");

		while (!futureTask2.isDone()) {
			System.out.println("Task2 is not complete yet....");
			futureTask2.cancel(true);
		}

		if (futureTask2.isCancelled()) {
			System.out.println("Task2 is cancelled");
		}

		threadPool.shutdown();

	}
}
