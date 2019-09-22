package slidingWindow;

//https://www.techiedelight.com/find-subarray-having-given-sum-given-array/
//dynamic window,2 pointers
public class SubArrayWithGivenSUm {

	// main function
	public static void main(String[] args) {
		// array of positive integers
		int[] A = { 2, 6, 0, 9, 7, 3, 1, 4, 1, 10 };
//		int[] A = { 0,5,-7,1,-4,7,6,1,4,1,10 };
//		int[] A = { 1,-4,7,6,1,4 };
		int sum = 35;

		findSubarray(A, sum);
	}

	private static void findSubarray(int[] a, int sum) {
		int windowSum = 0, left = 0, right = 0;

//		while (left < a.length) {
//			while (right < a.length && windowSum < sum) {
//				windowSum = windowSum + a[right];
//				right = right + 1;
//			}
//
//			if (windowSum == sum) {
//				System.out.println("Found subarray at " + a[left] + "to" + a[right-1]);
//				break;
//			}
//
//			windowSum = windowSum - a[left];
//			left = left + 1;
//
//		}

		for (int i = left; i < a.length; i++) {
			while (right < a.length && windowSum < sum) {
				windowSum = windowSum + a[right];
				right = right + 1;
			}

			if (windowSum == sum) {
				System.out.println("Found subarray at " + a[left] + "to" + a[right - 1]);
				break;
			}
			windowSum = windowSum - a[left];
			left = left + 1;
		}
	}
}
