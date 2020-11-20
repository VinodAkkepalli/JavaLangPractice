package practice.concurrency;

public class LiftOff implements Runnable{

	
	protected int countDown = 10;
	private static int taskDown = 0;
	private final int id = taskDown++;
	
	public LiftOff() {
	}
	
	public LiftOff(int i) {
		this.countDown = i;
	}

	@Override
	public void run() {
		while(countDown  --> 0){
			System.out.print(status());
			Thread.yield();
		}
	}

	public String status() {
		return "#" + id + "(" + 
				(countDown > 0 ? countDown : "LiftOff!") + "), ";
	}
	
	
}
