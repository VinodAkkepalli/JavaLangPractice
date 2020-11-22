package oopsconcepts;

/*
 * To check whether a class can implement 2 interfaces which contain common method declaration
 * */

public class MultipleInterface implements Interface1, Interface2{

	public static void main(String[] args) {

		
	}

	@Override
	public void commonMethod() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int methodOfInterface2(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int methodOfInterface2() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}

interface Interface2 {
	void commonMethod();
	int methodOfInterface2();
	int methodOfInterface2(int j);
}

interface Interface1 {
	void commonMethod();
}