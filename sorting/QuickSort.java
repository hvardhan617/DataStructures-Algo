package sorting;

//https://www.techiedelight.com/sort-list-of-objects-using-comparable-java/
//https://www.techiedelight.com/sort-list-of-objects-using-comparator-java/

//https://javarevisited.blogspot.com/2014/08/quicksort-sorting-algorithm-in-java-in-place-example.html
//https://www.techiedelight.com/quicksort/
public class QuickSort {

	// Partition using Lomuto partition scheme
	public static int partition(int[] a, int start, int end) {
		// Pick rightmost element as pivot from the array
		int pivot = a[end];

		// elements less than pivot will be pushed to the left of pIndex
		// elements more than pivot will be pushed to the right of pIndex
		// equal elements can go either way
		int pIndex = start;

		// each time we finds an element less than or equal to pivot,
		// pIndex is incremented and that element would be placed
		// before the pivot.
		for (int i = start; i < end; i++) {
			if (a[i] <= pivot) {
				swap(a, i, pIndex);
				pIndex++;
			}
		}

		// swap pIndex with Pivot
		swap(a, end, pIndex);

		// return pIndex (index of pivot element)
		return pIndex;
	}

	// Quicksort routine
	public static void quickSort(int[] a, int start, int end) {
		// base condition
		if (start >= end) {
			return;
		}

		// rearrange the elements across pivot
		int pivotIndex = partition(a, start, end);

		// recur on sub-array containing elements less than pivot
		quickSort(a, start, pivotIndex - 1);

		// recur on sub-array containing elements more than pivot
		quickSort(a, pivotIndex + 1, end);
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// https://www.techiedelight.com/problems-solved-using-partitioning-logic-quicksort/

	public static void main(String args[]) {
		QuickSort qs = new QuickSort();
//		int a[] = { 9, 0, 0, 0, 1, 2, 54, 8, 4, 333, 20, 19 };
		int a[] = { 1, 0, 0, 0, 1, 0, 1, 1 };
		qs.quickSort(a, 0, a.length - 1);
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i]);
	}

	// Function to sort binary array in linear time
	int partition(int A[], int n) {
		int pivot = 1;
		int j = 0;

		// each time we encounter a 0, j is incremented and
		// 0 is placed before the pivot
		for (int i = 0; i < n; i++) {
			if (A[i] < pivot) {
				swap(A, A[i], A[j]);
				j++;
			}
		}
		return 1;
	}

	//sort positives n negatives in O(N)
	void partition2(int a[], int start, int end) {
		int pIndex = start;

		// each time we find a negative number, pIndex is incremented
		// and that element would be placed before the pivot
		for (int i = start; i <= end; i++) {
			if (a[i] < 0) // pivot is 0
			{
				swap(a, a[i], a[pIndex]);
				pIndex++;
			}
		}
	}

	// Function to move all zeros present in the array to the end
	void partition3(int A[], int n) {
		int j = 0;

		// each time we encounter a non-zero, j is incremented and
		// the element is placed before the pivot
		for (int i = 0; i < n; i++) {
			if (A[i] != 0) // pivot is 0
			{
				swap(A, A[i], A[j]);
				j++;
			}
		}
	}

	// Function to rearrange given array such that it contains positive
	// and negative numbers at alternate positions
	int rearrange(int A[], int size) {
		// partition given array such that all positive elements move
		// to end of the array
		int p = partition(A, size);

		// swap alternate negative element from next available positive
		// element till end of array is reached or all negative or
		// positive elements are exhausted.
		for (int n = 0; (p < size && n < p); p++, n += 2) {
			swap(A, A[n], A[p]);
		}

		return 1;
	}
}
