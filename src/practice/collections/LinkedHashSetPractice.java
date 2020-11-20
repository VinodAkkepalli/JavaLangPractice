package practice.collections;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetPractice {

	public static void main(String[] args) {
		
		Set<Integer> lhs = new LinkedHashSet<>();
		lhs.add(9);
		lhs.add(99);
		lhs.add(1);
		lhs.add(6);
		lhs.add(0);
		
		System.out.println("the treeset elements are: " + lhs.toString());
		
		lhs.add(37);
		
		System.out.println("the treeset elements are: " + lhs.toString());
		
		lhs.add(66);
		
		System.out.println("the treeset elements are: " + lhs.toString());
	}

}
