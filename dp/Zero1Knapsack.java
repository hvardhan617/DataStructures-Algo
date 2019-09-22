package dp;

//https://www.youtube.com/watch?v=8LusJS5-AGo&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr
//https://www.techiedelight.com/0-1-knapsack-problem/
public class Zero1Knapsack {

	// Input:
	// Values (stored in array v)
	// Weights (stored in array w)
	// Number of distinct items (n)
	// Knapsack capacity (W)
	public static int knapSack(int[] v, int[] w, int W) {
		// T[i][j] stores the maximum value of knapsack having weight
		// less than equal to j with only first i items considered.
		int[][] T = new int[v.length + 1][W + 1];

		// do for ith item
		for (int i = 1; i <= v.length; i++) {
			// consider all weights from 0 to maximum capacity W
			for (int j = 0; j <= W; j++) {
				// don't include ith element if j-w[i-1] is negative
				if (w[i - 1] > j) {
					T[i][j] = T[i - 1][j];
				} else {
					// find maximum value we get by excluding or including
					// the i'th item
					T[i][j] = Integer.max(T[i - 1][j], T[i - 1][j - w[i - 1]] + v[i - 1]);
				}
			}
		}

		// return maximum value
		return T[v.length][W];
	}

	public static void main(String[] args) {
		// Input: set of items each with a weight and a value
		int[] v = { 20, 5, 10, 40, 15, 25 };
		int[] w = { 1, 2, 3, 8, 7, 4 };

		// Knapsack capacity
		int W = 10;

		System.out.println("Knapsack value is " + knapSack(v, w, W));
	}
}
