package practice.variables;

import java.util.Arrays;

public class ArrayVariable {

	public static void main(String[] args) {

		int[] intArray;
		//
		intArray = new int[] {0,1,2,3,4,5,6,7,8,9};
		System.out.println("intArray = " + Arrays.toString(intArray));
		
		char[] charArray = {'a','k','k','e','p','a','l','l','i'};
		System.out.println("charArray = " + Arrays.toString(charArray));
		System.out.println("the name is: " + (new String(charArray)));
		
		//to copy a part of an array into another
		int[] intArray2 = java.util.Arrays.copyOfRange(intArray, 2, 5);
		System.out.println("position of 2 is = " + Arrays.binarySearch(intArray,2));
		
		int[] intArray3 = intArray;
		if(Arrays.equals(intArray3, intArray)){
			System.out.println("intArray3 == intArray");	
		}
		
		
		System.out.println("Elements of intArray2 = " + Arrays.toString(intArray2));
		System.out.println("lengh of intArray = " + intArray2.length);
		
		
		
	}

}
