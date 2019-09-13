package sorting;

import java.util.Arrays;

//https://www.youtube.com/watch?v=mB5HXBb_HY8
//https://www.youtube.com/watch?v=6pV2IF0fgKY&list=PLDN4rrl48XKpZkf03iYFl-O29szjTrs_O&index=33
public class MergeSort {

	public static void main(String args[]) {

		int a[] = { 99, 43, 1, 56, 20, 3, 88, 50, 19, 13, 100 };

//		MergeSort ms = new MergeSort();
		a = mergeSort(a);
		System.out.println(Arrays.toString(a));

	}

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
			} else if (left[a] < right[b]) {
				c[i] = left[a];
				a = a + 1;
			} else if (left[a] > right[b]) {
				c[i] = right[b];
				b = b + 1;
			}
		}

//		int i = 0, j = 0, k = 0;
//		while (i <= left.length && j <= right.length) {
//			if (left[i] < right[j]) {
//				c[k] = left[i];
//				i = i + 1;
//				k = k + 1;
//			} else {
//				c[k] = right[j];
//				j = j + 1;
//				k = k + 1;
//			}
//		}
//
//		for (; i <= left.length; i++) {
//			c[k] = left[i];
//			k = k + 1;
//			i = i + 1;
//		}
//
//		for (; j <= right.length; j++) {
//			c[k] = right[j];
//			k = k + 1;
//			j = j + 1;
//		}
		return c;
	}

}
