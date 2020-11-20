package practice.collections;

import java.util.*;

/**
 * 
 * @author Vinod Akkepalli
 *
 *	practice addition, remove, define iterator next, hasNext, previous, hasPrevious
 */

public class ArrayListPractice {
	
	public static void main(String[] args) {
		
		List<String> al1 = new ArrayList<>();
		
		al1.add("Vinod");
		al1.add("Akkepalli");
		
		System.out.println(al1.toString());
		
		
		
		System.out.println("After inserting at second position");
		al1.add(1,"Kumar");
		for(Iterator<String> it = al1.iterator(); it.hasNext();){
			System.out.println(it.next());
		}
		
		System.out.println("After removing the first list item");
		al1.remove(0);
		System.out.println(al1.toString());
		
		System.out.println("ArrayList in reverse order");
		for(ListIterator<String> it = al1.listIterator(al1.size()); it.hasPrevious();){
			System.out.println(it.previous());
		}
		
		System.out.println("Reversing the list");
		Collections.reverse(al1);
		System.out.println(al1.toString());
	}
	
}
