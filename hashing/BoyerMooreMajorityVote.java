package hashing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//https://www.techiedelight.com/find-majority-element-in-an-array-boyer-moore-majority-vote-algorithm/
//A majority element appears more than n/2 times in the array
public class BoyerMooreMajorityVote {

	// Function to return majority element present in given array
	public static int majorityElement(int[] A) {
		// create an empty Hash Map
		Map<Integer, Integer> map = new HashMap<>();

		// store each element's frequency in a map
		for (int i = 0; i < A.length; i++) {
			if (map.get(A[i]) == null) {
				map.put(A[i], 0);
			}

			map.put(A[i], map.get(A[i]) + 1);
		}

		// return the element if its count is more than n/2
		Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> pair = it.next();
			if (pair.getValue() > A.length / 2)
				return pair.getKey();

			it.remove(); // avoids ConcurrentModification Exception
		}

		// no majority element is present
		return -1;
	}

	// Function to return majority element present in given array
	public static int majorityElementboyerMorre(int[] A) {
		// m stores majority element if present
		int m = -1;

		// initialize counter i with 0
		int i = 0;

		// do for each element A[j] of the array
		for (int j = 0; j < A.length; j++) {
			// if the counter i becomes 0
			if (i == 0) {
				// set the current candidate to A[j]
				m = A[j];

				// reset the counter to 1
				i = 1;
			}

			// else increment the counter if A[j] is current candidate
			else if (m == A[j]) {
				i++;
			}
			// else decrement the counter if A[j] is not current candidate
			else {
				i--;
			}
		}

		return m;
	}

	public static void main(String[] args) {
		// Assumption - valid input (majority element is present)
		int arr[] = { 1, 8, 7, 4, 1, 2, 2, 2, 2, 2, 2 };

		int res = majorityElementboyerMorre(arr);
		if (res != -1) {
			System.out.println("Majority element is " + res);
		} else {
			System.out.println("Majority element does not exist");
		}
	}
}
