package oopsconcepts;

/**
 * 
 * @author VinodAkkepalli
 *
 *         Problem Statement: Without changing the main method print the below
 *         output:
 *
 *         My Name is: FastFood 
 *         My Name is: Fruits
 *         Our superclass is: Food
 *         I'm serving FastFood
 *         I'm serving Fruits
 * 
 *
 */

class FoodFactory extends Food {

	FoodFactory() {
	}

	public FoodFactory(String string) {
		super(string);
	}

	public Food getFood(String string) {

		if (string.equals("FastFood")) {
			return new FastFood("FastFood");
		} else{
			return new Fruits("Fruits");
		}
	}
}

class Fruits extends Food {
	Fruits(String str) {
		super(str);
	}
}

class FastFood extends Food {
	FastFood(String str) {
		super(str);
	}
}

class Food extends InheritancePractice {
	String name;

	Food() {
	}

	public Food(String string) {
		name = string;
	}

	public void serveFood() {
		System.out.println("I'm serving " + name);
	}
}

public class InheritancePractice {

	public static void main(String[] args) {

		FoodFactory myFoods = new FoodFactory();
		Food food1 = myFoods.getFood("FastFood");
		Food food2 = myFoods.getFood("Fruits");
		System.out.println("My Name is: " + food1.getClass().getName());
		System.out.println("My Name is: " + food2.getClass().getName());
		System.out.println("Our superclass is: "
				+ food1.getClass().getSuperclass().getName());
		food1.serveFood();
		food2.serveFood();
	}

}
