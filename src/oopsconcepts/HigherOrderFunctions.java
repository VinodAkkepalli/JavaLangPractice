package oopsconcepts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

/**
 * <b>Description</b> :
 * A higher-order function is a function that does at least one of the following:
 * 1) takes one or more functions as arguments (i.e. procedural parameters),
 * 2) returns a function as its result.
 *
 * @author Vinod Akkepalli
 */
public class HigherOrderFunctions {
    public static void main(String[] args) {

        method1();
        higherOrderFunctionTakingAnotherFunctionAsArgument();
        higherOrderFunReturningAnotherFunction();

    }

    private static void higherOrderFunReturningAnotherFunction() {
        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Abc");
        list.add("BCD");

        Comparator<String> comparator = String::compareTo;

        //here reversed() is a higher order function
        Comparator<String> comparatorReversed = comparator.reversed();
        Collections.sort(list, comparatorReversed);

        System.out.println(list);
    }

    private static void higherOrderFunctionTakingAnotherFunctionAsArgument() {
        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Abc");
        list.add("BCD");

        //here sort is a higher order function
        Collections.sort(list, String::compareTo);
        System.out.println(list);
    }


    public static void method1() {
        Function<IntUnaryOperator, IntUnaryOperator> twice = f -> f.andThen(f);
        int i = twice.apply(x -> x + 3).applyAsInt(7);// 13
        System.out.println(i);

        Function<DoubleUnaryOperator, DoubleUnaryOperator> thrice = f -> f.andThen(f).andThen(f);
        Double j = thrice.apply(x -> x*3.1).applyAsDouble(7.1);// 13
        System.out.println(j);
    }
}
