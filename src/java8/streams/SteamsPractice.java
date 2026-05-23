package java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SteamsPractice {

    static void main() {
        /**
         *
         * Given a list of strings, filter out any words shorter than 4 characters, convert the remaining words to uppercase,
         * and join them into a single comma-separated string.
         *
         * Input: List<String> words = Arrays.asList("apple", "cat", "banana", "dog", "elephant");
         *
         * Expected Output: "APPLE, BANANA, ELEPHANT"
         *
         */

        List<String> words = Arrays.asList("apple", "cat", "banana", "dog", "elephant");

        Optional<String> commaSeparatedOutput = words.stream().filter(str -> str.length() > 3)
                .map(str -> str.toUpperCase())
                .reduce((a, b) -> a + ", " + b);


        String commaSeparatedOutput2 = words.stream().filter(str -> str.length() > 3)
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));


        commaSeparatedOutput.ifPresentOrElse(System.out::println, null);
        System.out.println(commaSeparatedOutput2);


        // Interview Classic: Find character frequencies in a String
        String input = "interview";
        // Result: {r=1, t=1, e=2, v=1, w=1, i=2, n=1}
        Map<String, Long> frequency = Arrays.stream(input.split(""))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println(frequency);




        List<String> sList = List.of("Ajay", "Vijay", "Ayan", "Kim");

        System.out.println(sList);

        /**
         * find distinct names starting with 'A',
         * convert to uppercase and sort them
         */

        List<String> stringList = sList.stream().filter(s -> s.startsWith("A"))
                .distinct()
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(stringList);

    }




}
