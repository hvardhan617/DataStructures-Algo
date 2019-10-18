package arrays;

//https://www.geeksforgeeks.org/program-print-lower-triangular-upper-triangular-matrix-array/
public class PrintTrianglesInMatrix {

	public static void main(String args[]) {
		int a[][] = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		printTriangles(a);
	}

	private static void printTriangles(int[][] a) {

		int m = a.length; // number of rows
		int n = a[0].length;// number of cols

		int i = 0, j = 0, x = 0, y = 0;
		while (i < m && j < n) {
			for (int k = 0; k <= y; k++) {
				System.out.println(a[x][k]);

			}
			i++;
			j++;
			x++;
			y++;
		}

		i = 0;
		j = 0;
		x = 0;
		y = a[0].length - 1; // cols
		while (i < m && j < n) {
			for (int k = x; k <= y; k++) {
				System.out.println(a[x][k]);

			}
			i++;
			j++;
			x++;

		}

	}
}
