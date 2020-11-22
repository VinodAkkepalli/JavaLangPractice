package java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * <b>Description</b> :
 * https://www.geeksforgeeks.org/stream-flatmap-java-examples/
 *https://examples.javacodegeeks.com/core-java/java8-flatmap-example/
 * http://tutorials.jenkov.com/java-functional-programming/streams.html#flatmap
 * https://www.youtube.com/watch?v=NNzi4PTazzE
 * @author Vinod Akkepalli
 */
public class FlatMapDemo {

    public static void main(String[] args) {
        // Creating a list of Prime Numbers
        List<Integer> PrimeNumbers = List.of(5, 7, 11, 13);

        // Creating a list of Odd Numbers
        List<Integer> OddNumbers = List.of(1, 3, 5);

        // Creating a list of Even Numbers
        List<Integer> EvenNumbers = List.of(2, 4, 6, 8);

        List<List<Integer>> listOfListOfInts = List.of(PrimeNumbers, OddNumbers, EvenNumbers);

        System.out.println("The Structure before flattening is : " + listOfListOfInts);

        // Using flatMap for transforming and flattening.
        List<Integer> listOfInts = listOfListOfInts.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        System.out.println("The Structure after flattening is : " + listOfInts);

        /*======================*/

        // Creating a List of Strings
        List<String> list1 = List.of("Geeks", "GFG", "GeeksforGeeks", "gfg");

        System.out.println("lengths of each of the strings in list is: ");
        // Using Stream flatMapToInt(Function mapper)
        // to get length of all strings present in list
        list1.stream().flatMapToInt(str -> IntStream.of(str.length())).
                forEach(System.out::println);

        /*======================*/

        // Creating a list of Strings
        List<String> list = List.of("1.5", "2.7", "3", "4", "5.67");

        System.out.println("strings converted to double values: ");
        // Using Stream flatMapToDouble(Function mapper)
        list.stream().flatMapToDouble(num -> DoubleStream.of(Double.parseDouble(num)))
                .forEach(System.out::println);

       //Can be done without using flatmap as below
//        List<Double> nl = list.stream().map(Double::valueOf).collect(Collectors.toList());
//        System.out.println(nl);
        /*======================*/

        List<String> stringList = List.of(
                "One flew over the cuckoo's nest",
                "To kill a mockingbird",
                "Gone with the wind");

        List<String> wordList = stringList.stream()
                .flatMap((sentence) -> {
                    String[] split = sentence.split(" ");
                    return Arrays.stream(split);
                })
                .collect(Collectors.toList());

        System.out.println(wordList);
    }
}