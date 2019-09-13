package BFS;

//https://www.youtube.com/watch?v=CLvNe-8-6s8
public class NumberOfIslands {

	public static void main(String args[]) {
		int mat[][] = { { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 } };

		System.out.println(noOfIslands(mat));
	}

	private static int noOfIslands(int[][] mat) {
		System.out.println("Columns & rows::::;" + mat.length + mat[0].length);
		if (mat == null)
			return 0;
		int islands = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j] == 1) {
					islands = islands + 1;
					changeIslandToWater(mat, i, j);
				}

			}
		}

		return islands;
	}

	private static void changeIslandToWater(int[][] mat, int i, int j) {
		if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length || mat[i][j] == 0)
			return;

		// mark it as visited and make it water
		mat[i][j] = 0;
		changeIslandToWater(mat, i + 1, j);// up
		changeIslandToWater(mat, i - 1, j);// down
		changeIslandToWater(mat, i, j + 1);// right
		changeIslandToWater(mat, i, j - 1);// left

	}
}
