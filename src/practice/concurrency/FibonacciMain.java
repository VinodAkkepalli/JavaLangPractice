package practice.concurrency;

public class FibonacciMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for(int i=0; i<2;++i){
			new Thread(new Fibonacci(20)).start();
		}
		

	}

}
