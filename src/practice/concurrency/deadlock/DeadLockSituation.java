package practice.concurrency.deadlock;

/**
 * 
 * @author Vinod Akkepalli
 *
 *	Problem Description: This code demonstrates a deadlock occurrence 
 *
 *	Deadlocks occur when all the below 4 conditions are present in a scenario
 *	1) Circular Wait
 *	2) No preemption
 *	3) Mutual Exclusion(Only one thread can access a resource at a time)
 *	4) Acquire a resource and wait for another
 *	
 *	If at least one of the above conditions is excluded from happening, we can avoid deadlock
 *
 */

public class DeadLockSituation {

	public static void main(String[] args) {
	    
		// These are the two resources R1 and R2 Thread thr1 and thr2 try to get locks for
	    final Object R1 = "Resource1";
	    final Object R2 = "Resource2";
	    
	    // Here's the first thread.  It tries to lock R1 then R2
	    Thread thr1 = new Thread(() -> {
		  // Lock resource 1
		  synchronized(R1) {
			System.out.println("Thread 1: Acquired and Locked resource 1");

			//Force deadlock by sleeping for sometime so that thread2 can come and
			//acquire lock for R2
			try {
				Thread.sleep(100);
			}catch (InterruptedException ignored) {}

			System.out.println("Thread 1: Trying to acquire lock on R2");
			// Now wait 'till we can get a lock on resource 2
			synchronized(R2) {
			  System.out.println("Thread 1: Acquired and Locked resource 2");
			}

			System.out.println("Thread 1: execution finished!!");
		  }
		});
	    
	    // Here's the second thread.  It tries to lock resource2 then resource1
	    Thread thr2 = new Thread(() -> {
		  //This thread locks resource 2
		  synchronized(R2) {
			System.out.println("Thread 2: Acquired and Locked resource 2");

			// Then it pauses, for the same reason as the first thread does
			try {
				Thread.sleep(100);
			}catch (InterruptedException ignored) {}

			System.out.println("Thread 2: Trying to acquire lock on R1");

			synchronized(R1) {
			  System.out.println("Thread 2: Acquired and Locked resource 1");
			}

			System.out.println("Thread 2 execution finished!!");
		  }
		});
	    
	    // Start the two threads. If all goes as planned, deadlock will occur, 
	    // and the program will never exit.
	    thr1.start(); 
	    thr2.start();
	  }	
}