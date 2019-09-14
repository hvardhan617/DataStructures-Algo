package sorting;

import java.util.Arrays;

//https://www.techiedelight.com/find-triplet-maximum-product-array/
//Do a sort on the array & check for product of last 3 elements or first 2 & last elemet if array has negative numbers.
public class TripletWithMaxProduct {

	// Find a triplet having maximum product in a given array
	public static void findTriplet(int[] A) {
		// Sort the given array in natural order
		Arrays.sort(A);
		int n = A.length;

		// wrong input
		if (n <= 2) {
			System.out.print("No triplet exists since the list has less than 3 elements");
		}

		// Consider maximum of last three elements or
		// first two element and last element
		if (A[n - 1] * A[n - 2] * A[n - 3] > A[0] * A[1] * A[n - 1]) {
			System.out.print("Triplet is (" + A[n - 1] + ", " + A[n - 2] + ", " + A[n - 3] + ")");
		} else {
			System.out.print("Triplet is (" + A[0] + ", " + A[1] + ", " + A[n - 1] + ")");
		}
	}

	// main function
	public static void main(String[] args) {
		int[] A = { -4, 1, -8, 9, 6 };
		findTriplet(A);
	}
}
