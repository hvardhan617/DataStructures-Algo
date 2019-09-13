package sorting;

import java.util.Arrays;

//https://www.techiedelight.com/segregate-positive-negative-integers-using-mergesort/
public class SegregatePositivesNegatives {

	private static int[] mergeSort(int[] a) {

		if (a.length == 1) {
			return a;
		}

		// Find mid and populate left and right arrays respectively.
		int mid = (int) Math.floor(a.length / 2);
		int left[] = new int[mid];
		int right[] = new int[a.length - mid];

		for (int i = 0; i < a.length; i++) {
			if (i < mid) {
				left[i] = a[i];
			} else {
				right[i - mid] = a[i];
			}
		}

		left = mergeSort(left);
		right = mergeSort(right);
		a = merge(left, right);

		return a;
	}

	private static int[] merge(int[] left, int[] right) {
		int c[] = new int[left.length + right.length];
		int a = 0;
		int b = 0;
		for (int i = 0; i < left.length + right.length; i++) {
			if (a == left.length) {
				c[i] = right[b];
				b = b + 1;
			} else if (b == right.length) {
				c[i] = left[a];
				a = a + 1;
			} else if (left[a] < 0 && right[b] < 0) {
				c[i] = left[a];
				a = a + 1;
			} else if (left[a] > 0 && right[b] > 0) {
				c[i] = left[a];
				a = a + 1;
			} else if (left[a] < 0 && right[b] > 0) {
				c[i] = left[a];
				a = a + 1;
			} else if (left[a] > 0 && right[b] < 0) {
				c[i] = right[b];
				b = b + 1;
			}
		}
		return c;
	}

	public static void main(String args[]) {
		
		int[] a = { 9, -3, 5, -2, -8, -6, 1, 3 };
//		MergeSort ms = new MergeSort();
		a = mergeSort(a);
		System.out.println(Arrays.toString(a));

	}
}
