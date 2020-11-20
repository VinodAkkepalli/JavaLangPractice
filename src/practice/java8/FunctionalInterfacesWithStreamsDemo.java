package practice.java8;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * <b>Description</b> : Functional interfaces used along with Streams
 *
 * @author Vinod Akkepalli
 */
public class FunctionalInterfacesWithStreamsDemo {

    public static void main(String[] args) {

        System.out.println("****Using Predicate interface filter a stream****");
        // Predicate interface 1 input parameter and  boolean output
        IntStream.range(1,100)
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);

        System.out.println("****Using Supplier interface generate a stream****");
        // Supplier interface no input and integer output
        IntStream.generate(() -> (int) (Math.random() * 10))
                .limit(10)
                .forEach(System.out::println);

        System.out.println("****Using Consumer interface consume a stream****");
        // Consumer interface 1 input and no output
        IntStream.range(1, 10).forEach((value -> System.out.println("Value is: " + value)));

        System.out.println("****Using Function interface to apply some operation on a stream****");
        // Function interface 1 input and 1 output
        Arrays.stream(new double[]{1, 3, 7, 9, 11, 15}).map(i -> i/10).forEach(System.out::println);
    }

}
