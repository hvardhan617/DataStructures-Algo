package hashing;

import java.util.HashMap;
import java.util.Map;

//https://www.techiedelight.com/find-triplet-given-with-given-sum/
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
}
