package arrays;

import java.util.Arrays;

//https://www.techiedelight.com/find-missing-number-array-without-extra-space/
public class MissingNumberInARange {
	// Find missing number in a limited range array [1..n+1]
	public static int findMissingElement(int[] A) {
		int n = A.length;

		// calculate sum of all elements of the array A
		int sum = Arrays.stream(A).sum();

		// expected sum - actual sum
		// sum of n numbers is n*(n+1)/2,hence sum of n+1 numbers would be to add last
		// number
		return (n + 1) + n * (n + 1) / 2 - sum;
	}

	public static void main(String[] args) {
		// input array contains n numbers between [1 to n + 1]
		// with one number missing and no duplicates
		int[] A = { 3, 2, 4, 6, 1 };

		System.out.print("The missing element is " + findMissingElement(A));
	}
}
