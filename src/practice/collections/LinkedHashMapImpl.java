package practice.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 
 * @author Vinod Akkepalli
 *	Description: LinkedHashMap implementation
 *	For more details on how the ordering is maintained read through http://www.javarticles.com/2012/06/linkedhashmap.html
 */

public class LinkedHashMapImpl {

	private static final int MAX_SIZE = 5;

	public static void main(String[] args) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>(
				16, 0.75f, true) {
			@Override
			protected boolean removeEldestEntry(Entry eldest) {
				return size() > MAX_SIZE;
			}
		};
		lhm.put("one", null);
		lhm.put("two", null);
		lhm.put("three", null);

		System.out.println("LinkedHashMap is: " + lhm.toString());

		System.out.println("LinkedHashMap accessed with iterator: ");
		Iterator<Entry<String, String>> itr = lhm.entrySet().iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

		System.out.println();
		System.out.println("** Access one, will move it to end **");
		lhm.get("one");

		System.out.println("LinkedHashMap accessed with iterator: ");
		itr = lhm.entrySet().iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

		System.out.println();
		System.out.println("** Access two, will move it to end **");
		lhm.put("two", "two");

		System.out.println("LinkedHashMap accessed with iterator: ");
		itr = lhm.entrySet().iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

		lhm.put("four", "4");
		lhm.put("five", "5");

		System.out.println("LinkedHashMap accessed with iterator: ");
		itr = lhm.entrySet().iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

		lhm.put("six", "6");

		System.out.println("LinkedHashMap accessed with iterator: ");
		itr = lhm.entrySet().iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

		System.out.println("Eldest Entry(LRU entry) has been removed!!");
	}

}
