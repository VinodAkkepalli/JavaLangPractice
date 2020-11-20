package practice.collections;

import java.util.HashSet;
import java.util.Iterator;

//create, add, delete and other basic operations

public class HashSetPractice {

	public static void main(String[] args) {
		
		HashSet<Integer> hs = new HashSet<>();

		hs.add(1);
		hs.add(100);
		hs.add(300);
		
		System.out.println(hs.toString());
		
		HashSet<Integer> subSet = new HashSet<>();
		subSet.add(3);
		subSet.add(6);
		
		hs.addAll(subSet);

		Iterator<Integer> it = hs.iterator();

		while(it.hasNext()){
			System.out.print(it.next() + " ");
		}
		
		System.out.println();
		
		if(hs.contains(100)){
			System.out.println("this hashset contains element 100!");
		}
		
		System.out.println("after removing element 100");
		hs.remove(100);
		System.out.println(hs.toString());
		System.out.println("current size of the hashset is : " + hs.size());
		
		System.out.println("after adding a duplicate element: ");
		hs.add(1);
		System.out.println(hs.toString());
		
	}

}
