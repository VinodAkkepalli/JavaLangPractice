package practice.collections;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetPractice {

	public static void main(String[] args) {
		
		Set<Integer> ts = new TreeSet<>();
		ts.add(9);
		ts.add(99);
		ts.add(1);
		ts.add(6);
		ts.add(0);
		
		System.out.println("the treeset elements are: " + ts.toString());
		
		ts.add(37);
		
		System.out.println("the treeset elements are: " + ts.toString());
	}

}
