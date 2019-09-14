package sorting;

import java.util.Arrays;

//https://www.techiedelight.com/sort-array-using-one-swap/
public class SortAnSortedArray2ElementsSwapped {

	private static void sortArray(int[] arr) {
		int x = -1, y = -1;
		int prev = arr[0];

		// process each adjacent pair of elements
		for (int i = 1; i < arr.length; i++) {
			// if previous element is greater than the current element
			if (prev > arr[i]) {
				// first occurrence of conflict
				if (x == -1) {
					x = i - 1;
					y = i;
				} else {
					// second occurrence of conflict
					y = i;
				}
			}
			prev = arr[i];
		}

		// swap the elements present at index x and index y
		swap(arr, x, y);
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a = { 3, 5, 6, 9, 8, 7 };

		sortArray(a);
		System.out.println(Arrays.toString(a));
	}

}
