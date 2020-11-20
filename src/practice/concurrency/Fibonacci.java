package practice.concurrency;

public class Fibonacci implements Runnable {

	private int fibCount = 0;
	public Fibonacci() {}
	
	public Fibonacci(int i) {
		this.fibCount = i;
	}
	
	@Override
	public void run() {
		int sum=0, a=0, b=1;
		
		if (fibCount == 0)
			return;
		if (fibCount == 1){
			System.out.println(a);
			return;
		}
		System.out.println(a);
		System.out.println(b);
		for (int i=2; i<fibCount; ++i){
			sum = a+b;
			System.out.println(sum);
			a=b; b=sum;
			Thread.yield();
		}

	}

}
