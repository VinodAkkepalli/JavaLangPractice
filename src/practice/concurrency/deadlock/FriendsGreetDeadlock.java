package practice.concurrency.deadlock;

/**
 * 
 * @author VinodAkkepalli
 *
 *	Program to demonstrate a deadlock situation
 *	If/when both Batman & Joker say hi to each other at the same time, and waiting for the other to say hiBack
 *	Deadlock occurs here, because they don't say hi back
 */


public class FriendsGreetDeadlock {

	private final String name;

	public FriendsGreetDeadlock(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public synchronized void sayHi(FriendsGreetDeadlock bower) {
		System.out.format("%s: " + "Hi, How are you %s?\n", this.getName(), bower.getName());
		bower.hiBack(this);
		System.out.println(this.getName() + ": says we are good fiends!!");
	}

	public synchronized void hiBack(FriendsGreetDeadlock bower) {
		System.out.format("%s: " + "Hi, I am good %s!!\n", this.getName(), bower.getName());
	}

	public static void main(String[] args) {
		final FriendsGreetDeadlock batman = new FriendsGreetDeadlock("Batman");
		final FriendsGreetDeadlock joker = new FriendsGreetDeadlock("Joker");
		
		new Thread(() -> batman.sayHi(joker)).start();
		
		new Thread(() -> joker.sayHi(batman)).start();
	}
}