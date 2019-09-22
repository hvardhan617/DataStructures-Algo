package arrays;

//https://javabypatel.blogspot.com/2016/11/print-matrix-in-spiral-order.html
//https://www.youtube.com/watch?v=TmweBVEL0I0
//https://www.youtube.com/watch?v=siKFOI8PNKM
public class PrintMatrixInSpiralForm {

	public static void main(String[] args) {
		new PrintMatrixInSpiralForm();
	}

	public PrintMatrixInSpiralForm() {
		int[][] matrix = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
				{ 21, 22, 23, 24, 25 } };

		printMatrixInSpiralWay(matrix, 5, 5);
	}

	// for a 2d matrix,matrix.length gives number of rows & matrix[0].length gives
	// num of columns
	private void printMatrixInSpiralWay(int[][] matrix, int m, int n) {

		int top = 0;
		int bottom = m - 1;
		int left = 0;
		int right = n - 1;
		int dir = 0;
		while (top <= bottom && left <= right) {
			if (dir == 0) {
				for (int i = left; i <= right; i++) {
					System.out.println(matrix[top][i]);
				}
				top = top + 1;
				dir = 1;
			} else if (dir == 1) {
				for (int i = top; i <= bottom; i++) {
					System.out.println(matrix[i][right]);
				}
				right = right - 1;
				dir = 2;
			} else if (dir == 2) {
				for (int i = right; i >= left; i--) {
					System.out.println(matrix[bottom][i]);
				}
				bottom = bottom - 1;
				dir = 3;
			} else if (dir == 3) {
				for (int i = bottom; i >= top; i--) {
					System.out.println(matrix[i][left]);
				}
				left = left + 1;
				dir = 0;
			}

//			dir = (dir + 1) % 4;
		}
	}
}
