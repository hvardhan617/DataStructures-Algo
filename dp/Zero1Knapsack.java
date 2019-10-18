package dp;

//https://www.youtube.com/watch?v=8LusJS5-AGo&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr
//https://www.techiedelight.com/0-1-knapsack-problem/
//every cell in the tabulation gives max value possible with items n capacity at that point in the matrix
//max ( up / current & up & back)
//end goal is to find max value for items within given capacity.
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
		for (int i = 0; i <= v.length; i++) {
			for (int j = 0; j <= W; j++) {
				if (i == 0 || j == 0) {
					T[i][j] = 0;
					continue;
				}
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

		for (int i = 0; i < T.length; i++) {
			for (int j = 0; j < T[0].length; j++) {
				System.out.println(T[i][j]);
			}
		}
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
