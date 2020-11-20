package practice.concurrency.uncaughtexceptionhandler;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 
 * <b>Description</b> : Demo of UncaughtExceptionHandler in Multi-Threading
 * When defined, UncaughtExceptionHandler's uncaughtException method will be executed
 * whenever a thread throws an exception
 * 
 * @author Vinod Akkepalli
 *
 */

public class UncaughtExceptionHandlerDemo extends Exception implements Runnable {

	private static UncaughtExceptionHandler eh = new UncaughtExceptionHandler() {

		@Override
		public void uncaughtException(Thread t, Throwable e) {

			System.out.println("UncaughtExceptionHandler in action!");
			System.out.println(t + "throws exception " + e);
		}
	};

	public static void main(String[] args) {

		UncaughtExceptionHandlerDemo ueh = new UncaughtExceptionHandlerDemo();

		Thread t1 = new Thread(ueh);
		t1.setUncaughtExceptionHandler(eh);

		t1.start();

	}

	@Override
	public void run() {
		while (true) {
			System.out.println("About to throw exception from thread!!");

			// this line of code terminates execution of thread abruptly.
			// defining UncaughtExceptionHandler takes the control flow to
			// uncaughtException method
			throw new ArithmeticException();
		}
	}

}
