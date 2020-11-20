package practice.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vinod Akkepalli
 * Program to demonstrate the functioning details of HashMap()
 * This example clearly explains the function call-backs invoked in collisions
 */

class Vehicle{
	private String name;
	private Integer rank;
	
	Vehicle(String name, Integer rank){
		this.name = name;
		this.rank = rank;
	}
	
	//self defined hashCode() method
	public int hashCode(){
		System.out.println("inside the hashcode method of: (" + this.name + ", " + this.rank +")");
		return rank;
	}
	
	//eclipse generated toString() method
	@Override
	public String toString() {
		return "Vehicle [name=" + name + ", rank=" + rank + "]";
	}

	//eclipse generated equals() method
	@Override
	public boolean equals(Object obj) {
		
		System.out.println("inside the equals method of: (" + this.name + ", " + this.rank + ") comparing with (" + ((Vehicle)obj).name + ", " + ((Vehicle)obj).rank + ")");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		return true;
	}
}

public class HashMapAnatomy {

	public static void main(String[] args) {

		Map<Vehicle, String> vMap = new HashMap<>();
		
		//creating vehicle objects
		Vehicle ferrari = new Vehicle("Ferrari", 1);
		Vehicle porche = new Vehicle("Porche", 2);
		Vehicle mercedez = new Vehicle("Mercedez", 3);
		Vehicle hummer = new Vehicle("Hummer", 1);
		Vehicle audi = new Vehicle("Audi", 1);
		
		vMap.put(ferrari, "One");
		vMap.put(porche, "Two");
		vMap.put(mercedez, "Three");
		// Collision here as hashCode is 1 which is same as that of ferrari
		vMap.put(hummer, "Four");
		// Collision here as hashCode is 1 which is same as that of ferrari & hummer
		vMap.put(audi, "Five");	
		// Same key porche inserted second time in HashMap => value is updated/overwritten
		// Methods invoked hashCode(returns 2), hash(of key porche)
		// hash matches with existing porche in vMap; hence value is overwritten
		vMap.put(porche,"Four");
		// Same key hummer inserted second time in HashMap => value is updated/overwritten
		vMap.put(hummer,"One");	
		
		System.out.println("Hashmap entries are :");
		System.out.println(vMap.toString());
		
		System.out.println(vMap.get(audi));
		System.out.println(vMap.get(ferrari));
		System.out.println(vMap.get(hummer));
		System.out.println(vMap.get(porche));
		
		
		System.out.println("Hashmap entries sorted by value are:");
		Map<Vehicle, String> vMap1 = MapUtils.sortByValue(vMap);
		System.out.println(vMap1.toString());
	}
}
