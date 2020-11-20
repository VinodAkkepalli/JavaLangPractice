package practice.concurrency;

public class MainThread{
	public static void main(String[] args) {
		LiftOff liftoff = new LiftOff();
		liftoff.run();
	}
}