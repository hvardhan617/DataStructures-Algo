package dp;

//https://www.youtube.com/watch?v=s6FhG--P7z0
//given a sum and set of non neg integers, find if sum can be possible by any subset 
//approach is similar to 0/1 knapsack ecept therz no value for an item
//every cell denotes if the sum can be formed wd a subset at that point.
public class SubsetSum {

	public boolean subsetSum(int input[], int total) {

		boolean T[][] = new boolean[input.length + 1][total + 1];
		for (int i = 0; i <= input.length; i++) {
			T[i][0] = true;
		}

		for (int i = 1; i <= input.length; i++) {
			for (int j = 1; j <= total; j++) {
				if (j - input[i - 1] >= 0) {
					T[i][j] = T[i - 1][j] || T[i - 1][j - input[i - 1]];
				} else {
					T[i][j] = T[i - 1][j];
				}
			}
		}
		return T[input.length][total];

	}

	public boolean partition(int arr[]) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}

		if (sum % 2 != 0) {
			return false;
		}
		sum = sum / 2;
		boolean[][] T = new boolean[arr.length + 1][sum + 1];

		for (int i = 0; i <= arr.length; i++) {
			T[i][0] = true;
		}

		for (int i = 1; i <= arr.length; i++) {
			for (int j = 1; j <= sum; j++) {
				if (j - arr[i - 1] >= 0) {
					T[i][j] = T[i - 1][j - arr[i - 1]] || T[i - 1][j];
				} else {
					T[i][j] = T[i - 1][j];
				}
			}
		}
		return T[arr.length][sum];
	}

	public static void main(String args[]) {
		SubsetSum ss = new SubsetSum();
		int arr[] = { 1, 3, 5, 5, 2, 1, 1, 6 };
		System.out.println(ss.partition(arr));

		int arr1[] = { 2, 3, 7, 8 };
		System.out.print(ss.subsetSum(arr1, 11));

	}
}
