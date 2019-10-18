package slidingWindow;

import java.util.Scanner;

public class test2 {

	public static void main(String args[]) {
//		Scanner s = new Scanner(System.in);
//		int testCases = s.nextInt();
//		for (int i = 0; i < testCases; i++) {
//			int rows = s.nextInt();
//			int cols = s.nextInt();
//			int len = s.nextInt();
//			s.nextLine();
//			String input = s.nextLine();
//
//			printSpiralMatrix(input, rows, cols, len);
//		}
		printSpiralMatrix("abcde", 2, 4, 5);
	}

	private static void printSpiralMatrix(String string, int rows, int cols, int len) {

		char b[] = string.toCharArray();
		char a[][] = new char[rows][cols];
		int top = 0;
		int right = cols - 1;
		int bottom = rows - 1;
		int left = 0;
		int dir = 0;
		int index = 0;
		while (top <= bottom && left <= right) {

			if (dir == 0) {
				for (int i = left; i <= right; i++) {
					a[top][i] = b[index % len];
//					System.out.print(a[top][i]);
					index = index + 1;
//					System.out.print(a[top][i]);

				}
//				System.out.println();
//				if (rows > 1) {
				dir = 1;

//				}

				top = top + 1;
			}

			else if (dir == 1) {
				for (int i = top; i <= bottom; i++) {
					a[i][right] = b[index % len];
					index = index + 1;
//					System.out.println(a[i][right]);
				}
//				if(cols > 1)
				dir = 2;
				right = right - 1;
			}

			else if (dir == 2) {
				for (int i = right; i >= left; i--) {
					a[bottom][i] = b[index % len];
					index = index + 1;
//					System.out.print(a[bottom][i]);
				}

				dir = 3;
				bottom = bottom - 1;

			} else if (dir == 3) {
				for (int i = bottom; i >= top; i--) {
					a[i][left] = b[index % len];
					index = index + 1;
//					System.out.println(a[i][left]);
				}
				dir = 0;
				left = left + 1;
			}

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					System.out.print(a[i][j]);
				}
				System.out.println();
			}

		}
	}
}
