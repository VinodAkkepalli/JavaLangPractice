package practice.collections;

import java.util.*;

	//practice addition, remove, define iterator next, hasNext, previous, hasPrevious

public class LinkedListPractice {

	public static void main(String[] args) {

		List<Integer> ll1 = new LinkedList<>();
		
		ll1.add(1);
		ll1.add(2);
		
		System.out.println("Initial Linked list is : ");
		System.out.println(ll1.toString());
		
		
		System.out.println("After inserting at second position : ");
		ll1.add(1,11);
		
		//Print using LinkedList Iterator
        for(Iterator<Integer> it = ll1.iterator(); it.hasNext();){
			System.out.println(it.next());
		}
		
		System.out.println("printing the list in reverse order : ");
		//Print using LinkedList Iterator
		for(ListIterator<Integer> it = ll1.listIterator(ll1.size()); it.hasPrevious();){
			System.out.println(it.previous());
		}
		
		System.out.println("after sorting the list");
		Collections.sort(ll1);
		System.out.println(ll1.toString());
		
		List<Integer> ll2 = new LinkedList<>(ll1);
		Collections.fill(ll2, 100);
		System.out.println("new list filled with 100");
		System.out.println(ll2);
		
		
		System.out.println("first list copied onto new list");
		Collections.copy(ll2,ll1);
		System.out.println(ll2);
		
		System.out.println("The position of element 2 in second list is");
		System.out.println(Collections.binarySearch(ll2, 2));
		//TODO the above binarySearch does not provide (log n) time search. Why?? Read binarySearch documentation
		//For what kind of collections does it provide log n time search? compare ArrayList & LinkedList

		ll2.add(3);
		ll2.add(4);
		ll2.add(5);
		System.out.println(ll2 + " <- is the final List");
		Collections.rotate(ll2.subList(1,4), 2);
		System.out.println(ll2 + " <- List after rotation of the subList(1,4) by distance 2");
	}

}
