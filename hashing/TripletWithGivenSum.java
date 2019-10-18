package hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://www.techiedelight.com/find-triplet-given-with-given-sum/
//O(n2) O(n)
//create a hashmap with key as A[i] and value as i once by iterating the array.
//Now have 2 loops to iterate the consecutive elements & check if (sum-a[i]+a[j]) is present in hm.
public class TripletWithGivenSum {

	// Function to check if triplet exists in array with given sum
	public static boolean tripletExists(int[] A, int sum) {
		// create an empty map
		Map<Integer, Integer> map = new HashMap<>();

		// insert (element, index) pair in map for each element in the array
		for (int i = 0; i < A.length; i++) {
			map.put(A[i], i);
		}

		// consider each element except last element
		for (int i = 0; i < A.length - 1; i++) {
			// start from i + 1'th element till last element
			for (int j = i + 1; j < A.length; j++) {
				// remaining sum
				int val = sum - (A[i] + A[j]);

				// if remaining sum is found, we have found a triplet
				if (map.containsKey(val)) {
					// if triplet don't overlap, return true
					if (map.get(val) != i && map.get(val) != j) {
//						System.out.println(A[i]+"::"+A[j]+"::"+val);
						return true;
					}
				}
			}
		}

		// return false if triplet don't exists
		return false;
	}

	// Find Triplet with given sum in an array
	public static void main(String[] args) {
		int[] A = { 2, 7, 4, 0, 9, 5, 1, 3 };
		int sum = 4;

		if (tripletExists(A, sum)) {
			System.out.print("Triplet Exists");
		} else {
			System.out.print("Triplet Don't Exist");
		}
	}

	// Function to print all distinct triplet in the array with given sum
	public static void printAllTriplets(int[] arr, int sum) {
		// sort the array in ascending order
		Arrays.sort(arr);

		// check if triplet is formed by arr[i] and a pair from
		// sub-array arr[i+1..arr.length)
		for (int i = 0; i <= arr.length - 3; i++) {
			// remaining sum
			int k = sum - arr[i];

			// maintain two indices pointing to end-points of the
			// sub-array arr[i+1..n)
			int low = i + 1, high = arr.length - 1;

			// loop till low is less than high
			while (low < high) {
				// increment low index if total is less than the remaining sum
				if (arr[low] + arr[high] < k) {
					low++;
				}

				// decrement high index is total is more than the remaining sum
				else if (arr[low] + arr[high] > k) {
					high--;
				}

				// triplet with given sum found
				else {
					// print the triplet
					System.out.println("(" + arr[i] + " " + arr[low] + " " + arr[high] + ")");

					// increment low index and decrement high index
					low++;
					high--;
				}
			}
		}
	}
}
