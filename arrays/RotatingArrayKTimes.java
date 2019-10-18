package arrays;

import java.util.Arrays;

//https://www.techiedelight.com/right-rotate-an-array-k-times/
public class RotatingArrayKTimes {

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 5, 6, 7 };
		int k = 3;

		rightRotate(A, k);

		System.out.println(Arrays.toString(A));
//		leftRotate(A, 2);
//		System.out.println(Arrays.toString(A));
	}

	private static void rightRotate(int[] a, int k) {
		int b[] = Arrays.copyOf(a, a.length);
		for (int i = 0; i < a.length; i++) {
			a[(i + k) % a.length] = b[(i + k) % a.length];
		}

	}

	private static void leftRotate(int[] a, int k) {
		int b[] = Arrays.copyOf(a, a.length);
		for (int i = 0; i < a.length; i++) {
			a[i] = b[(i + (a.length - k)) % a.length];
		}

	}
}
