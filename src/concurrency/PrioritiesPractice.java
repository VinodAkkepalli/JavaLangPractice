package concurrency;


public class PrioritiesPractice implements Runnable{

	public static void main(String[] args) {
		(new Thread(new PrioritiesPractice())).start();
		System.out.println("Main Thread");
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(6);
		System.out.println("child Thread priority : " + Thread.currentThread().getPriority());
	}

}
