package practice.exceptions;

public class TestException {

	int var = 10;
	
	void getVar(){
		System.out.println("Var = " + var);
	}
	
	public static void main(String[] args) {
		TestException ob = null;
		
		try {
			ob.getVar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
