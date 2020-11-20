package practice.collections;

import java.util.HashMap;
import java.util.Set;

public class HashMapPractice {

	public static void main(String[] args) {

		HashMap<String, Character> hm = new HashMap<>();
		hm.put("Vinod", 'V');
		hm.put("Bapu", 'B');
		hm.put("Rajni", 'R');
		hm.put("Kanth", 'K');

		hm.put("somekey", null);
		hm.put(null, null);

		System.out.println("Complete HashMap is: ");
		System.out.println(hm.toString());

		System.out.print("Value corresponding to key Vinod = ");
		System.out.println(hm.get("Vinod"));

		if (hm.containsKey("Rajni")) {
			System.out.println("hashmap contains key Rajni");
		}

		if (!hm.containsKey("Ramesh")) {
			System.out.println("hashmap doesn't contain key Ramesh");
		}

		if (hm.containsValue('V')) {
			System.out.println("hashmap contains value V");
		}

		System.out.println("Set view of the hashmap is ");
		System.out.println(hm.entrySet());

		System.out.println("Set of all keys are");
		System.out.println(hm.keySet());

		Set<String> keys = hm.keySet();

		for (String key : keys) {
			System.out.println("Value of " + key + " is: " + hm.get(key));
		}

		System.out.println("Set of all values are");
		System.out.println(hm.values());

		System.out.println("Size of the Hashmap is = " + hm.size());

		System.out.println("hashset after removing Kanth ");
		hm.remove("Kanth");
		System.out.println(hm.entrySet());

		System.out.println("new hashmap which is clone of first one is ");
		@SuppressWarnings("unchecked")
		HashMap<String, Character> hm2 = (HashMap<String, Character>) hm
				.clone();
		System.out.println(hm2.entrySet());

	}

}