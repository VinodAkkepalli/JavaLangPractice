package practice.java8.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <b>Description</b> :
 *
 * @author Vinod Akkepalli
 */
public class StreamsReduceDemo {

    public static void main(String[] args) {
        List<String> words = List.of("GFG", "Geeks", "for",
                "GeeksQuiz", "GeeksforGeeks");
        //Get longest string
        Optional<String> longest = words.stream()
                .reduce((word1, word2) -> word1.length() < word2.length() ? word2 : word1);
        longest.ifPresent(System.out::println);

        List<String> words1 = Arrays.asList("GFG", "Geeks", "for",
                "GeeksQuiz", "GeeksforGeeks");
        Collections.sort(words1);

        String[] array = { "Geeks", "for", "Geeks" };
        //Get combined string
        Optional<String> str = Arrays.stream(array).reduce((word1, word2)-> word1 + ", " + word2);
        str.ifPresent(System.out::println);
        //Another way
        String resultStr = Arrays.stream(array).collect(Collectors.joining(", "));
        System.out.println(resultStr);
        //yet another way
        System.out.println(String.join(", ", array));

        List<Integer> intList = List.of(-2, 0, 4, 6, 8);
        //Get the sum of all elements
        Optional<Integer> sum = intList.stream().reduce(Integer::sum);
        sum.ifPresent(System.out::println);
    }

}
