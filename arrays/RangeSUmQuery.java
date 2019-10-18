package arrays;

//https://www.geeksforgeeks.org/range-sum-queries-without-updates/
//https://www.youtube.com/watch?v=ZMOFmHBVEcg
//A Simple Solution is to compute sum for every query.

//An Efficient Solution is to precompute prefix sum. 
//Let pre[i] stores sum of elements from arr[0] to arr[i].
//To answer a query (i, j), we return pre[j] – pre[i-1].
public class RangeSUmQuery {

	public static void preCompute(int arr[], int n, int pre[]) {
		pre[0] = arr[0];
		for (int i = 1; i < n; i++)
			pre[i] = arr[i] + pre[i - 1];
	}

	// Returns sum of elements in arr[i..j]
	// It is assumed that i <= j
	public static int rangeSum(int i, int j, int pre[]) {
		if (i == 0)
			return pre[j];

		return pre[j] - pre[i - 1];
	}

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 4, 5 };
		int n = arr.length;

		int pre[] = new int[n];

		preCompute(arr, n, pre);
		System.out.println(rangeSum(1, 3, pre));
		System.out.println(rangeSum(2, 4, pre));

	}
}
