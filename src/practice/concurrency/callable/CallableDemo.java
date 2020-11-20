package practice.concurrency.callable;

import java.util.concurrent.FutureTask;

/**
 * 
 * <b>Description</b> : code to demonstrate Callable interface to and its use
 * case to return a value after execution of the task
 * 
 * @author Vinod Akkepalli
 *
 */

public class CallableDemo {

	private CallableDemo() {
	}

	public static void main(String[] args) throws Exception {

		CallableFactorialCalcTask task = new CallableFactorialCalcTask(10);

		System.out.println("Thread name is: " + Thread.currentThread().getName());

		//We can not create a new thread of execution with callable interface directly
		//Notice now we are using FutureTask to create a runnable
		//We can use FutureTask object to get result
		FutureTask<?> futureTask = new FutureTask<>(task);
		Thread thread = new Thread(futureTask);
		thread.start();

//		while(!futureTask.isDone()) {
//			System.out.println("waiting for task to finish");
//			Thread.sleep(1000);
//		}
		if(!futureTask.isDone()) {
			System.out.println("Task is not finished yet...");
		}

		System.out.println("Message before task result...");

		//Get result from FutureTask
		System.out.println(futureTask.get());

		System.out.println("Message after task result...");
	}

}
