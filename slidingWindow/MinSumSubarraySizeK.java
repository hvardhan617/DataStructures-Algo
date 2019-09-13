package slidingWindow;

//https://www.techiedelight.com/find-minimum-sum-subarray-given-size-k/
//fixed window,2 variables for minSumSoFar & currentWindowSum
public class MinSumSubarraySizeK {

	public static void main(String[] args) {
		int[] arr = { 10, 4, 2, 5, 6, 3, 8, 1 };
		int k = 3;

		findSubarray(arr, k);
	}

	private static void findSubarray(int[] arr, int k) {

		// stores sum of element in current window
		int windowSum = 0;
		int minSoFar = Integer.MAX_VALUE;
		// stores ending index of minimum sum sub-array found so far
		int last = 0;
		for (int i = 0; i < arr.length; i++) {

			if (i < k) {
				windowSum = windowSum + arr[i];

			}

			else {
				if (windowSum < minSoFar) {
					minSoFar = windowSum;
					last = i-1;
				}
				if (i >= k) {
					windowSum = windowSum + arr[i] - arr[i - k];
				}

			}

		}

		System.out.printf("Minimum sum sub-array is (%d, %d)", last - k + 1, last);

	}
	
	//https://gist.github.com/Schachte/87d7c0165a584f26b3ad7845f8010387
	 public static int findMaxSumSubarray(int[] arr, int k) {
	        int maxValue = Integer.MIN_VALUE;
	        int currentRunningSum = 0;

	        for (int i = 0; i < arr.length; i++) {
	            currentRunningSum += arr[i];

	            if (i >= k - 1) {
	                maxValue = Math.max(maxValue, currentRunningSum);
	                currentRunningSum -= arr[i - (k - 1)];
	            }
	        }

	        return maxValue;
	    }

}
