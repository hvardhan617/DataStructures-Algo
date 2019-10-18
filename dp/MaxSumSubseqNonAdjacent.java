package dp;

//https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
public class MaxSumSubseqNonAdjacent {

	/*
	 * Function to return max sum such that no two elements are adjacent
	 */
	int FindMaxSum(int arr[], int n) {
		int incl = arr[0];
		int excl = 0;
		int excl_new;
		int i;

		for (i = 1; i < n; i++) {
			/* current max excluding i */
			excl_new = (incl > excl) ? incl : excl;

			/* current max including i */
			incl = excl + arr[i];
			excl = excl_new;
		}

		/* return max of incl and excl */
		return ((incl > excl) ? incl : excl);
	}

	// Driver program to test above functions
	public static void main(String[] args) {
		MaxSumSubseqNonAdjacent sum = new MaxSumSubseqNonAdjacent();
		int arr[] = new int[] { 5, 5, 10, 100, 10, 5 };
		System.out.println(sum.FindMaxSum(arr, arr.length));
	}

	/**
	 * Fast DP solution.
	 */
	public int maxSum(int arr[]) {
		int excl = 0;
		int incl = arr[0];
		for (int i = 1; i < arr.length; i++) {
			int temp = incl;
			incl = Math.max(excl + arr[i], incl);
			excl = temp;
		}
		return incl;
	}
}
