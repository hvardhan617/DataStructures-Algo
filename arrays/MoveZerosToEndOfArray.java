package arrays;

import java.util.Arrays;

//https://www.youtube.com/watch?v=1PEncepEIoE
//https://www.youtube.com/watch?v=T_bJPgKBgSU
//https://www.techiedelight.com/move-zeros-present-array-end/
public class MoveZerosToEndOfArray {

	// move zeros to end of the array
	// count non zeros and copy them to stored index
	private static int[] moveZeros(int a[]) {

		int index = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != 0) {
				a[index] = a[i];
				index = index + 1;
			}
		}

		while (index < a.length) {
			a[index] = 0;
			index = index + 1;
		}

		return a;
	}

	public static void main(String args[]) {
		int a[] = { 2, 3, 7, 11 };
		int b[] = { 20, 0, 0, 11, 7, 9, 0, 22, 36, 0 };

		System.out.println(Arrays.toString(moveZeros(b)));
	}

}
