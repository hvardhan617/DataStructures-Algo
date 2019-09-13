package sorting;

//https://www.youtube.com/watch?v=7zuGmKfUt7s
//https://www.youtube.com/watch?v=TTnvXY82dtM&t=163s
//https://www.techiedelight.com/efficiently-sort-array-duplicated-values/?source=post_page---------------------------
//can be used where array has many duplicate values
//It is expected to be given the range in case of counting sort of find the range by iterating and finding min & max in the given array
public class CountingSort {

	void sort(int arr[]) {
		int n = arr.length;

		// The output character array that will have sorted arr
		int output[] = new int[n];

		// Create a count array to store count of individual
		// characters and initialize count array as 0
		int count[] = new int[14];
		for (int i = 0; i < count.length; i++)
			count[i] = 0;

		// store count of each character
		for (int i = 0; i < n; i++) {
			count[arr[i]] = count[arr[i]] + 1;
		}
//		for (int i = 0; i < count.length; ++i)
//			System.out.print(count[i]);

//		// Change count[i] so that count[i] now contains actual
//		// position of this character in output array
		for (int i = 1; i <= count.length - 1; i++) {
			count[i] = count[i - 1] + count[i];
		}

//		for (int i = 0; i < count.length; ++i)
//			System.out.print(count[i]);
//
//		// Build the output character array
		for (int i = 0; i < arr.length; i++) {
			output[count[arr[i]] - 1] = arr[i];
			count[arr[i]] = count[arr[i]] - 1;
		}

		for (int i = 0; i < output.length; ++i)
			System.out.print(output[i]);

		// Copy the output array to arr, so that arr now
		// contains sorted characters
		for (int i = 0; i < n; i++)
			arr[i] = output[i];

	}

	// Driver method
	public static void main(String args[]) {
		CountingSort ob = new CountingSort();
		int arr[] = { 10, 7, 12, 4, 9, 13 };

		ob.sort(arr);

		System.out.print("Sorted character array is ");
		for (int i = 0; i < arr.length; ++i)
			System.out.print(arr[i]);
	}

}
