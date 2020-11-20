package practice.java8.streams;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <b>Description</b> : Practice Streams usage
 *
 * @author Vinod Akkepalli
 */
public class IntStreamsPractice {

    public static void main(String[] args) {

        IntStream.range(1,100)  //Create
                .parallel()     //Process
                .forEach(System.out::println);  //Consume



        System.out.println();

        System.out.println("*****Generate integers using intSupplier*****");
        IntSupplier intSupplier = () -> (int) (Math.random() * 10);
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " --> " +intSupplier.getAsInt());
        }

        System.out.println();

        System.out.println("*****Generate integers using IntStream.generate()*****");
        IntStream.generate(intSupplier)
                .limit(5)
                .forEach(System.out::println);

        System.out.println();

        System.out.println("******Summary Statistics******");
        IntSummaryStatistics iss = IntStream.range(1,100)   //Create
                .summaryStatistics();   //Process
        System.out.println("Count: " + iss.getCount()); //Consume
        System.out.println("Maximum: " + iss.getMax());
        System.out.println("Minimum: " + iss.getMin());
        System.out.println("Sum: " + iss.getSum());
        System.out.println("Average: " + iss.getAverage());

        System.out.println();

        int[] arr = {1,3,5,7,9};
//        System.out.println("Sum >5 is: " + Arrays.stream(arr).filter(i->i>4).sum());  //
        int reduce = Arrays.stream(arr).filter(i -> i > 4).reduce(10, Integer::sum);
//        if(reduce.isPresent()) {
//            System.out.println(reduce.getAsInt());
//        }
        System.out.println("Sum >5 is: " + reduce);

        System.out.println();
        System.out.println("Average of a array {1,3,5,7,9}: "
                + Arrays.stream(arr).summaryStatistics().getAverage());

        System.out.println();
        arr = new int[]{1, 2, 3, 5, 3};
        System.out.println("Distinct of a array " + Arrays.toString(arr) + ": ");
        Arrays.stream(arr).distinct().forEach(System.out::println);

        int[] collect = Arrays.stream(arr).distinct().toArray();
        System.out.println("Distinct array collected: " + Arrays.toString(collect));

        Set<Integer> collectSet = Arrays.stream(arr).distinct().boxed()
                .collect(Collectors.toSet());
        System.out.println("Distinct array collected: " + collectSet.toString());

        int[] collect1 = Arrays.stream(arr).sorted().toArray();
        System.out.println("Sorted array: " + Arrays.toString(collect1));

        List<Integer> collectList = Arrays.stream(arr).sorted()
                .boxed()
                .collect(Collectors.toList());
        Optional<Integer> opt1 = collectList.stream().reduce((a, b) -> a > b ? a : b);
        System.out.print("max of list: ");
        System.out.println(opt1.isPresent()? opt1.get(): "not there");
        System.out.println("sorted array: " + collectList.toString());

        System.out.println("Sorted array after skipping first two numbers: "
                + Arrays.toString(Arrays.stream(arr).sorted().skip(2).toArray()));

        System.out.println("Smallest 4 numbers of the array: "
                + Arrays.toString(Arrays.stream(arr).sorted().limit(4).toArray()));

        System.out.println("Array size: " + Arrays.stream(arr).count());

        System.out.println("All integers of arrays doubled: "
                + Arrays.toString(Arrays.stream(arr)
                .map(i -> i * 2)
                .toArray()));

        System.out.println("All odd integers of array: "
                + Arrays.toString(Arrays.stream(arr)
                .filter(i -> i % 2 == 1)
                .toArray()));

        List<Integer> list = Arrays.asList(1, 10, 3, 7, 5);
        int a = list.stream()
                .filter(i -> i > 5)
                .findFirst()
                .orElse(0);
        System.out.println("First element greater than 5: " + a);

        System.out.println(Arrays.toString(arr) + "Array has at least one even number : "
                + Arrays.stream(arr)
                .anyMatch(i -> i % 2 == 0));

        System.out.println(Arrays.toString(arr) + "All numbers of array are  odd numbers : "
                + Arrays.stream(arr)
                .allMatch(i -> i % 2 == 1));

        Integer[] arr2 = {3,7,-4,8,9,-5};
        //arrange the array elements such that all -ve numbers come to left side and +ve numbers come to right side
        //using Streams.concat
        List<Integer> collect2 = Stream.concat(Arrays.stream(arr2).filter(i -> i <= 0),
                Arrays.stream(arr2).filter(i -> i > 0))
                .collect(Collectors.toList());
        System.out.println(collect2);

        //Get product of all numbers using reduce
        OptionalInt prod = IntStream.range(2, 8).reduce((i, j) -> i * j);
        prod.ifPresent(System.out::println);

    }
}
