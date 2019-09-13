package sorting;

import java.util.Random;

//https://javarevisited.blogspot.com/2014/08/quicksort-sorting-algorithm-in-java-in-place-example.html
public class QuickSort {

	/**
	 * * As shown in above image, In each iteration, we will identify a * number
	 * from left side which is greater then the pivot value, and * a number from
	 * right side which is less then the pivot value. Once * search is complete, we
	 * can swap both numbers.
	 */
	private void quickSort(int input[], int low, int high) {
		int i = low;
		int j = high;
		// pivot is element at middle index
		int pivot = input[low + (high - low) / 2];
		while (i <= j) {
			while (input[i] < pivot) {
				i++;
			}
			while (input[j] > pivot) {
				j--;
			}
			if (i <= j) {
				swap(input, i, j);
				i++;
				j--;
			}
		}
		// calls quickSort() method recursively
		// quicksort lower half
		if (low < j) {
			quickSort(input, low, j);
		}
		// quicksort higher half
		if (i < high) {
			quickSort(input, i, high);
		}

	}

	// Choosing a random element as pivot with low & high inclusive
	// https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
	static int getPivot(int low, int high) {
		Random rand = new Random();
		return rand.nextInt((high - low) + 1) + low;
	}

	private void swap(int input[], int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

	public static void main(String args[]) {
		QuickSort qs = new QuickSort();
		int a[] = { 9, 0, 0, 0, 1, 2, 54, 8, 4, 333, 20, 19 };
		qs.quickSort(a, 0, a.length - 1);
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i]);
	}

}
