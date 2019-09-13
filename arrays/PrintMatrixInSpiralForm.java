package arrays;

//https://javabypatel.blogspot.com/2016/11/print-matrix-in-spiral-order.html
//https://www.youtube.com/watch?v=TmweBVEL0I0
public class PrintMatrixInSpiralForm {

	public static void main(String[] args) {
		new PrintMatrixInSpiralForm();
	}

	public PrintMatrixInSpiralForm() {
		int[][] matrix = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
				{ 21, 22, 23, 24, 25 } };

		printMatrixInSpiralWay(matrix);
	}

	// for a 2d matrix,matrix.length gives number of rows & matrix[0].length gives
	// num of columns
	private void printMatrixInSpiralWay(int[][] matrix) {

		int rowStart = 0;
		int rowLength = matrix.length - 1;

		int colStart = 0;
		int colLength = matrix[0].length - 1;

		while (rowStart <= rowLength && colStart <= colLength) {

			// go right
			for (int i = rowStart; i <= colLength; i++) {
				System.out.print(matrix[rowStart][i] + " ");
			}

			// go down
			for (int j = rowStart + 1; j <= rowLength; j++) {
				System.out.print(matrix[j][colLength] + " ");
			}

			// go left
			if (rowStart + 1 <= rowLength) {
				for (int k = colLength - 1; k >= colStart; k--) {
					System.out.print(matrix[rowLength][k] + " ");
				}
			}

			// go up
			if (colStart + 1 <= colLength) {
				for (int k = rowLength - 1; k > rowStart; k--) {
					System.out.print(matrix[k][colStart] + " ");
				}
			}

			// starts wud increase n lengths wud decrease
			rowStart++;
			rowLength--;
			colStart++;
			colLength--;
		}
	}
}
