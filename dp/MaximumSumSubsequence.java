package dp;

//https://www.youtube.com/watch?v=99ssGWhLPUE
//LIS
public class MaximumSumSubsequence {

	public int maxSum(int arr[]) {
		int T[] = new int[arr.length];

		for (int i = 0; i < T.length; i++) {
			T[i] = arr[i];
		}

		for (int i = 1; i < T.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					T[i] = Math.max(T[i], T[j] + arr[i]);
				}
			}
		}

		int max = T[0];
		for (int i = 1; i < T.length; i++) {
			if (T[i] > max) {
				max = T[i];
			}
		}
		return max;
	}

	public static void main(String args[]) {
		MaximumSumSubsequence mss = new MaximumSumSubsequence();
		int arr[] = { 1, 101, 10, 2, 3, 100, 4 };
		int r = mss.maxSum(arr);
		System.out.print(r);
	}
}
