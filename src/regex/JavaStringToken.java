package regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author VinodAkkepalli
 *
 *Problem Statement
Given a string, find number of words in that string. For this problem a word is defined by a string of one or more English alphabets.
There are multiple ways to tokenize a string in java, learn how to tokenize a string in java and demonstrate your skill by solving this problem!

Input Format
A string not more than 400000 characters long. The string can be defined by following regular expression:

[A-Za-z !,?.\_'@]+
That means the string will only contain English alphabets, blank spaces and this characters: "!,?._'@".

Output Format
In the first line, print number of words n in the string. The words don't need to be unique. In the next n lines, print all the words you found in the order they appeared in the input.

Sample Input
He is a very very good boy, isn't he?

Sample Output

10
He
is
a
very
very
good
boy
isn
t
he
 *
 */

public class JavaStringToken {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int startIndex = 0;
		int endIndex = str.length()-1;
		
		while(!(str.substring(startIndex, startIndex+1).matches("[a-zA-Z]"))){
			++startIndex;
		}
		
		while(!(str.substring(endIndex, endIndex+1).matches("[a-zA-Z]"))){
			--endIndex;
		}
		
		String[] tokens = str.substring(startIndex,endIndex+1).split("[ !,?._'@]+");
		System.out.println(tokens.length);

		for (String token : tokens) {
			System.out.println(token);
		}
		
	}

}
