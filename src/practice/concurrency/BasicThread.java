package practice.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicThread {

	public static void main(String[] args) {

		/*for(int i=0; i<5;++i){
		new Thread(new LiftOff()).start();
		}*/
		
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			exec.execute(new LiftOff(20));
		}
		System.out.println("Yes Sir!!");
		exec.shutdown();
	}
}