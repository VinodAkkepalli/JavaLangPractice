package concurrency.callable;

import java.util.concurrent.Callable;

/**
 * 
 * <b>Description</b> : A Callable class to compute factorial of a given number
 * 
 * @author Vinod Akkepalli
 *
 */
public class CallableFactorialCalcTask implements Callable<Integer> {

	private final int number;

	CallableFactorialCalcTask(int number) {
		this.number = number;
	}

	@Override
	public Integer call() throws Exception {

		System.out.println("Thread name is: " + Thread.currentThread().getName());
		int result = 1;
		for (int i = this.number; i > 1; i--) {
			result *= i;
		}

		Thread.sleep(2000);
		return result;
	}
}
