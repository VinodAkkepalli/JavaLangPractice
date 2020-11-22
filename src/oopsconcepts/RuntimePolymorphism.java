package oopsconcepts;

import java.util.Random;

/**
 * <b>Description</b> :
 * Code to demo runtime polymorphism
 *
 * @author Vinod Akkepalli
 */

interface Animal {
    void move();
}

class Bird implements Animal {

    @Override
    public void move() {
        System.out.println( "I am flying");
    }
}

class Tiger implements Animal {

    @Override
    public void move() {
        System.out.println("I am running on my legs");
    }
}


public class RuntimePolymorphism {

    public static void main(String[] args) {
        Animal animal;

        for (int i = 0; i < 10; i++) {
            int r = new Random().nextInt();
            animal = (r % 2 == 0) ? new Bird() : new Tiger();

            animal.move();
        }
    }
}


