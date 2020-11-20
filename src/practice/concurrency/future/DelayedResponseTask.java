package practice.concurrency.future;

import java.util.concurrent.Callable;

/**
 * 
 * <b>Description</b> : A Callable which calculates factorial of a number and
 * returns the value after calculation. The intention is to demo Callable which
 * is capable of returning a value.
 * This Callable also delays posting the result for 2 Seconds
 * 
 * @author Vinod Akkepalli
 *
 */

public class DelayedResponseTask implements Callable<Integer> {

	private final int number;

	public DelayedResponseTask(int number) {
		this.number = number;
	}

	@Override
	public Integer call() throws Exception {

		System.out.println("Thread name is: " + Thread.currentThread().getName());

		int result = 1;

		// Calculate factorial of number
		for (int i = this.number; i > 1; i--) {
			result *= i;
		}

		Thread.sleep(2000);
		return result;
	}

}
