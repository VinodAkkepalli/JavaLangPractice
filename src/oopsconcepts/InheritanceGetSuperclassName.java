package oopsconcepts;

/**
 * 
 * @author Vinod Akkepalli
 *
 * Have a look at the code below, which is present in main method of the public class
 * 
 * 		Adder X=new Adder();
        System.out.println("My superclass is: "+X.getClass().getSuperclass().getName());    
        System.out.print(X.add(10,32)+" "+X.add(10,3)+" "+X.add(10,10)+"\n");
 * 
 * write code such that the output will be as below
 * 
 * My superclass is: Arithmetic
	42 13 20
 *
 */

public class InheritanceGetSuperclassName {

	public static void main(String[] args) {
		Adder X=new Adder();
        System.out.println("My superclass is: "+X.getClass().getSuperclass().getName());    
        System.out.print(X.add(10,32)+" "+X.add(10,3)+" "+X.add(10,10)+"\n");
	}

}

class Adder extends Arithmetic{
	public int add(int a, int b){
		return a+b;
	}
}

class Arithmetic{
	
	public void someMethod(){
		System.out.println("");
	}
}