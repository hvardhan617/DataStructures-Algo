package slidingWindow;

//Interesting
//https://www.youtube.com/watch?v=vtJvbRlHqTA
//https://www.techiedelight.com/find-maximum-product-subarray-given-array/
public class MaxProductSubarray {

	// main function
	public static void main(String[] args) {
		int[] A = { -6, 4, -5, 8, -10, 0, 8 };

		maxProduct(A);
	}

	private static void maxProduct(int[] a) {

		int ans = a[0];
		int currMax = a[0], currMin = a[0], prevMax = a[0], prevMin = a[0];

		for (int i = 1; i < a.length; i++) {
			currMax = Integer.max(Integer.max(prevMax * a[i], prevMin * a[i]), a[i]);
			currMin = Integer.min(Integer.max(prevMax * a[i], prevMin * a[i]), a[i]);

			ans = Integer.max(ans, currMax);
			prevMax = currMax;
			prevMin = currMin;
		}

		System.out.println(ans);

	}
}
