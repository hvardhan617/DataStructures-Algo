package slidingWindow;

import java.util.HashMap;
import java.util.Map;

//https://www.techiedelight.com/count-distinct-elements-every-sub-array-size-k-array/
//https://www.youtube.com/watch?v=MK-NZ4hN7rs
//falls under fixed window length,but using auxiliary DS hashmap to store the frequencies.
public class CountDistinctElementsSubArraySizeK {

	// Function to find the count of distinct elements in every sub-array
	// of size k in the array
	public static void findDistinctCount(int[] A, int k) {
		// map to store frequency of elements in current window of size k
		Map<Integer, Integer> freq = new HashMap<>();

		// maintains count of distinct elements in every sub-array of size k
		int distinct = 0;

		// loop through the array
		for (int i = 0; i < A.length; i++) {
			// ignore first k elements
			if (i >= k) {
				// Remove first element from the sub-array by reducing its
				// frequency in the map
				freq.putIfAbsent(A[i - k], 0);
				freq.put(A[i - k], freq.get(A[i - k]) - 1);

				// reduce distinct count if we're left with 0
				if (freq.get(A[i - k]) == 0) {
					distinct--;
				}
			}

			// add current element to the sub-array by incrementing its
			// count in the map
			freq.putIfAbsent(A[i], 0);
			freq.put(A[i], freq.get(A[i]) + 1);

			// increment distinct count by 1 if element occurs for the first
			// time in current window
			if (freq.get(A[i]) == 1) {
				distinct++;
			}

			// print count of distinct elements in current sub-array
			if (i >= k - 1) {
				System.out.println("The count of distinct elements in the " + "sub-array [" + (i - k + 1) + ", " + i
						+ "]" + " is " + distinct);
			}
		}
	}

	// main function
	public static void main(String[] args) {
		int[] input = { 1, 1, 2, 1, 3 };
		int k = 3;

		findDistinctCount(input, k);
	}
}
