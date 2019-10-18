package arrays;

import java.util.ArrayList;

//https://www.geeksforgeeks.org/union-and-intersection-of-two-sorted-arrays-2/
//O(m+n) using mergesort technique
//O(m+n)logn+O(m+n)logm for unsorted arrays
public class UnionIntersectionArrays {

	public static void main(String args[]) {
		int arr1[] = { 1, 2, 4, 5, 6 };
		int arr2[] = { 2, 3, 5, 7 };
		int m = arr1.length;
		int n = arr2.length;
		unionIntersection(arr1, arr2, m, n);
	}

	private static void unionIntersection(int[] a, int[] b, int m, int n) {

		int x = 0, y = 0;
		ArrayList<Integer> unionList = new ArrayList<>();
		ArrayList<Integer> intersectionList = new ArrayList<>();
		for (int i = 0; i < a.length + b.length; i++) {

			if (x == a.length) {
				if (y == b.length) {
					break;
				}
				unionList.add(b[y]);

				y = y + 1;

			} else if (y == b.length) {
				if (x == b.length) {
					break;
				}
				unionList.add(a[x]);

				x = x + 1;

			} else if (a[x] < b[y]) {
				unionList.add(a[x]);
				x = x + 1;
			} else if (a[x] > b[y]) {
				unionList.add(b[y]);
				y = y + 1;
			} else if (a[x] == b[y]) {
				unionList.add(b[y]);
				intersectionList.add(b[y]);
				y = y + 1;
				x = x + 1;
			}
		}

		System.out.println("Union list:::" + unionList.toString());
		System.out.println("Intersection list:::" + intersectionList.toString());

	}
}
