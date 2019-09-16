package hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

//https://www.techiedelight.com/find-sub-array-with-0-sum/
//subarray means contiguous subsequence
//https://www.youtube.com/watch?v=aQdZ6hK5Ius
//https://www.geeksforgeeks.org/print-all-subarrays-with-0-sum/
//insert sum as key n index as value,whenever 0 sum is encountered,print the subarray range
public class SubArraysWithZeroSum {

	// main function
	public static void main(String[] args) {
		int[] A = { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 };
		int[] B = { 3, 4, 0 };
//		printallSubarraysWithZeroSum(A);
		printallSubarrays(A);
		// System.out.println(checkForZeroSumSubarray(B));

	}

	private static void printallSubarraysWithZeroSum(int[] a) {

		for (int i = 0; i < a.length; i++) {
			int sum = 0;

			for (int j = i + 1; j < a.length; j++) {
				sum = sum + a[j];

				// if sum is seen before, we have found a subarray with 0 sum
				if (sum == 0) {
					System.out.println("Subarray [" + i + ".." + j + "]");
				}
			}
		}
	}

	// Utility function to insert <key, value> into the Multimap
	// value is index
	private static <K, V> void insert(Map<K, List<V>> hashMap, K key, V value) {
		// if the key is seen for the first time, initialize the list
		if (!hashMap.containsKey(key)) {
			hashMap.put(key, new ArrayList<>());
		}

		hashMap.get(key).add(value);

	}

	// Function to print all sub-arrays with 0 sum present
	// in the given array
	public static void printallSubarrays(int[] A) {
		// create an empty Multi-map to store ending index of all
		// sub-arrays having same sum
		Map<Integer, List<Integer>> hashMap = new HashMap<>();

		// insert (0, -1) pair into the map to handle the case when
		// sub-array with 0 sum starts from index -1
		insert(hashMap, 0, -1);

		int sum = 0;

		// traverse the given array
		for (int i = 0; i < A.length; i++) {
			// sum of elements so far
			sum += A[i];

			// if sum is seen before, there exists at-least one
			// sub-array with 0 sum
			if (hashMap.containsKey(sum)) {
				List<Integer> list = hashMap.get(sum);

				// find all sub-arrays with same sum
				for (Integer value : list) {
					System.out.println("Subarray [" + (value + 1) + ".." + i + "]");
				}
			}

			// insert (sum so far, current index) pair into the Multi-map
			insert(hashMap, sum, i);
			hashMap.forEach((k, v) -> System.out.println("key:::" + k + ":" + v));
		}
	}

	private static Boolean checkForZeroSumSubarray(int a[]) {
		HashSet<Integer> set = new HashSet<>();
		int sum = 0;
		set.add(sum);

		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i];

			if (set.contains(sum)) {
				return true;
			}
		}

		return false;
	}

}
