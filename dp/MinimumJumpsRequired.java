package dp;

import java.util.Arrays;

//https://www.youtube.com/watch?v=cETfFsSTGJI
//similar to LIS - single array (no 2 d matrix)
public class MinimumJumpsRequired {

	// Find minimum jumps required to reach the destination(Memoization)
	public static int findMinJumps(int[] arr, int i, int n, int lookup[]) {
		// base case: destination is reached
		if (i == n - 1) {
			return 0;
		}

		// base case: array index out of bound or destination is
		// unreachable from source
		if (i >= n || arr[i] == 0) {
			return Integer.MAX_VALUE;
		}

		// if the sub-problem is seen before
		if (lookup[i] != 0) {
			return lookup[i];
		}

		// find the minimum jumps required to reach the destination by considering
		// the minimum of all elements reachable from arr[i]
		int min_jumps = Integer.MAX_VALUE;
		for (int j = i + 1; j <= i + arr[i]; j++) {
			int cost = findMinJumps(arr, j, n, lookup);
			if (cost != Integer.MAX_VALUE) {
				min_jumps = Math.min(min_jumps, cost + 1);
			}
		}

		// save sub-problem solution and return minimum jumps required
		return (lookup[i] = min_jumps);
	}

	// Find minimum jumps required to reach the destination
	public static int findMinJumps(int[] arr) {
		// get length of the array
		int n = arr.length;

		// base case: destination is unreachable from source
		if (arr[0] == 0) {
			return Integer.MAX_VALUE;
		}

		// lookup[i] stores min jumps required to reach arr[i] from source arr[0]
		int[] lookup = new int[n];
		Arrays.fill(lookup, Integer.MAX_VALUE);

		// destination is same as source
		lookup[0] = 0;

		// do for every position
		for (int i = 0; i < n; i++) {
			// find the minimum jumps required to reach the destination by
			// considering the minimum from each position reachable from arr[i]
			for (int j = 1; (i + j < n) && j <= Math.min(n - 1, arr[i]); j++) {
				lookup[i + j] = (lookup[i + j] != Integer.MAX_VALUE) ? Math.min(lookup[i + j], lookup[i] + 1)
						: (lookup[i] + 1);
			}
		}

		// lookup[n-1] would have the result since arr[n-1] is the destination
		return lookup[n - 1];
	}

	public static void main(String[] args) {
		int[] arr = { 1, 3, 6, 1, 0, 9 };

		// create an auxiliary array for storing solution to the sub-problems and
		// initialized with 0
		int[] lookup = new int[arr.length];

		System.out.println(
				"Minimum jumps required to reach the destination are " + findMinJumps(arr, 0, arr.length, lookup));
	}

	public int minJump(int arr[], int result[]) {

		int[] jump = new int[arr.length];
		jump[0] = 0;
		for (int i = 1; i < arr.length; i++) {
			jump[i] = Integer.MAX_VALUE - 1;
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] + j >= i) {
					if (jump[i] > jump[j] + 1) {
						result[i] = j;
						jump[i] = jump[j] + 1;
					}
				}
			}
		}

		return jump[jump.length - 1];
	}

	// https://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-solution/
	static int minJumps(int arr[]) {
		if (arr.length <= 1)
			return 0;

		// Return -1 if not possible to jump
		if (arr[0] == 0)
			return -1;

		// initialization
		int maxReach = arr[0];
		int step = arr[0];
		int jump = 1;

		// Start traversing array
		for (int i = 1; i < arr.length; i++) {
			// Check if we have reached the end of the array
			if (i == arr.length - 1)
				return jump;

			// updating maxReach
			maxReach = Math.max(maxReach, i + arr[i]);

			// we use a step to get to the current index
			step--;

			// If no further steps left
			if (step == 0) {
				// we must have used a jump
				jump++;

				// Check if the current index/position or lesser index
				// is the maximum reach point from the previous indexes
				if (i >= maxReach)
					return -1;

				// re-initialize the steps to the amount
				// of steps to reach maxReach from position i.
				step = maxReach - i;
			}
		}

		return -1;
	}

}
