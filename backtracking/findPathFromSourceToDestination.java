package backtracking;

//https://www.youtube.com/watch?v=0C8BUf_0DB4&t=275s
public class findPathFromSourceToDestination {

	static int path[][] = new int[4][4];
	static int a[][] = { { 1, 1, 0, 1 }, { 1, 0, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 1, 1 } };

	private static boolean isSafe(int mat[][], boolean[][] visited, int x, int y) {
		// if its obstacle or is already visited
		if (mat[x][y] == 0 || visited[x][y] == true)
			return false;

		return true;
	}

	// if not a valid position, return false
	private static boolean isValid(int x, int y) {
		if (x < a.length && y < a[0].length && x >= 0 && y >= 0)
			return true;

		return false;
	}

	static int findPath(int i, int j, int n, boolean visited[][]) {

		// if destination reached
		if (i == n - 1 && j == n - 1) {
			visited[i][j] = true;
			path[i][j] = 1;
			return 1;
		}

		visited[i][j] = true;
		path[i][j] = 1;

		// bottom
		if (isValid(i + 1, j) && isSafe(a, visited, i + 1, j)) {
			if (findPath(i + 1, j, n, visited) == 1)
				return 1;
		}

		// right
		if (isValid(i, j + 1) && isSafe(a, visited, i, j + 1)) {
			if (findPath(i, j + 1, n, visited) == 1)
				return 1;

		}

		visited[i][j] = false;
		path[i][j] = 0; // remove path from solution matrix so that it can be re explored
		return 0;

	}

	static void printPath(int path[][]) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (path[i][j] == 1) {
					System.out.println(i + ":::" + j);
				}
			}
		}
	}

	public static void main(String args[]) {

		boolean visited[][] = new boolean[4][4];
		findPath(0, 0, 4, visited);

		printPath(path);
	}
}
