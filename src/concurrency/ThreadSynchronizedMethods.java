package concurrency;

/**
 * @author VinodAkkepalli
 *
 *	Problem Description:
 *	Program to demonstrate synchronization as a mechanism in java threads
 *	When two threads operate on a single object, only one synchronized method will be executing at an instance
 *	Hence this code consistently produces result 16
 */

class SynchronizedCounter{
	int count = 0;
	
	public synchronized void increment(){
		count++;
		//System.out.println(this.value());
	}
	
	public synchronized void decrement(){
		count--;
		//System.out.println(this.value());
	}
	
	public synchronized int value(){
		return count;
	}
}

class MyThread implements Runnable{

	protected SynchronizedCounter sCounter = null;
	
	public MyThread(SynchronizedCounter counter) {
		this.sCounter = counter;
	}
	
	@Override
	public void run() {
		sCounter.decrement();
		for(int i = 0; i < 10; i++){
			sCounter.increment();
		}
		sCounter.decrement();
	}
	
}

public class ThreadSynchronizedMethods {

	public static void main(String[] args) throws InterruptedException {
		
		//Two threads are synchronized over a single object
		//and these produce a consistent final value for SynchronizedCounter.counter variable
		SynchronizedCounter counter = new SynchronizedCounter();
		new Thread(new MyThread(counter)).start();
		new Thread(new MyThread(counter)).start();

		Thread.sleep(1000);
		System.out.println(counter.value());
	}
}
