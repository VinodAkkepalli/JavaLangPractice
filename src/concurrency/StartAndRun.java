package concurrency;

/**
 * 
 * @author VinodAkkepalli
 *
 *	Program to demonstrated the difference between thread execution using ob.start() & ob.run()
 *
 *	In summary:
 *	ob.start() <- New thread is created and code executed on new thread
 *	ob.run() <- the run method code is executed on the same/main thread 
 *
 */

public class StartAndRun {

	public static void main(String[] args) {
		
		ThreadClass ob1 = new ThreadClass("StartMethod");
		Thread t1 = new Thread(ob1);
		t1.start();
		
		ThreadClass ob2 = new ThreadClass("RunMethod");
		Thread t2 = new Thread(ob2);
		t2.run();
	}

}

class ThreadClass implements Runnable{

	private String callingThreadname;
	
	public ThreadClass(String name) {
		this.callingThreadname = name;
	}
	
	@Override
	public void run() {
		System.out.println("Called by " + callingThreadname + " executed by " + Thread.currentThread().getName());

		
	}
	
}