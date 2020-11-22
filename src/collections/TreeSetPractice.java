package collections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetPractice {

	public static void main(String[] args) throws IOException {
		
		Set<Integer> ts = new TreeSet<>();
		ts.add(9);
		ts.add(99);
		ts.add(1);
		ts.add(6);
		ts.add(0);
		
		System.out.println("the treeset elements are: " + ts.toString());
		
		ts.add(37);
		
		System.out.println("the treeset elements are: " + ts.toString());

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		TreeSet<String> ts1 = new TreeSet<>();

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			ts1.add(str);
		}

		System.out.println(ts1.size());
		for(String str1 : ts1){
			System.out.println(str1);
		}
	}

}
