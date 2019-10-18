package hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://www.techiedelight.com/4-sum-problem/
//map of sum of 2 elements:Pair(i,j)
class Pair {
	public int x, y;

	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class QuadrupletsWithGivenSum {

	// Function to check if Quadruplet exists in an array with given sum
	public static boolean quadTuple(int[] A, int n, int sum) {
		// create an empty map
		// key -> sum of a pair of elements in the array
		// value -> list storing index of every pair having that sum
		Map<Integer, List<Pair>> map = new HashMap<>();

		// consider each element except last element
		for (int i = 0; i < n - 1; i++) {
			// start from i'th element till last element
			for (int j = i + 1; j < n; j++) {
				// calculate remaining sum
				int val = sum - (A[i] + A[j]);

				// if remaining sum is found in the map,
				// we have found a Quadruplet
				if (map.containsKey(val)) {
					// check every pair having sum equal to remaining sum
					for (Pair pair : map.get(val)) {
						int x = pair.x;
						int y = pair.y;

						// if Quadruplet don't overlap, print it and(all 4 indices stores as pairs must
						// be different)
						// return true
						if ((x != i && x != j) && (y != i && y != j)) {
							System.out
									.print("Quadruplet Found (" + A[i] + ", " + A[j] + ", " + A[x] + ", " + A[y] + ")");
							return true;
						}
					}
				}

				// insert current pair into the map

				// null check (Java 8)
				map.putIfAbsent(A[i] + A[j], new ArrayList<>());
				map.get(A[i] + A[j]).add(new Pair(i, j));
			}
//			map.forEach((k, v) -> {
//				System.out.println(k + "::::" + v.x + "::" + v.y);
//			});
		}

		// return false if Quadruplet don't exist
		return false;
	}

	// main function
	public static void main(String[] args) {
		int[] A = { 2, 7, 4, 0, 9, 5, 1, 3 };
		int sum = 20;

		if (!quadTuple(A, A.length, sum)) {
			System.out.print("Quadruplet Don't Exist");
		}
	}

	// Function to print all Quadruplet present in an array with given sum
	// https://www.techiedelight.com/print-all-quadruplets-with-given-sum-4-sum-problem-extended/
	// O(n3)
	public static void quadTuple(int[] A, int sum) {
		// sort the array in ascending order
		Arrays.sort(A);

		// check if Quadruplet is formed by A[i], A[j] and a pair from
		// sub-array A[j+1..n)
		for (int i = 0; i <= A.length - 4; i++) {
			for (int j = i + 1; j <= A.length - 3; j++) {
				// k stores remaining sum
				int k = sum - (A[i] + A[j]);

				// check for sum k in sub-array A[j+1..n)
				int low = j + 1, high = A.length - 1;

				while (low < high) {
					if (A[low] + A[high] < k) {
						low++;
					} else if (A[low] + A[high] > k) {
						high--;
					}
					// Quadruplet with given sum found
					else {
						System.out.println("(" + A[i] + " " + A[j] + " " + A[low] + " " + A[high] + ")");
						low++;
						high--;
					}
				}
			}
		}
	}
}
