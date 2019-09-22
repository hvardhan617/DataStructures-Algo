package backtracking;

//https://www.youtube.com/watch?v=0C8BUf_0DB4&t=275s
public class findPathFromSourceToDestination {

	static int path[][] = new int[5][5];
	static int a[][] = { {}, {}, {}, {}, {} };

	static int findPath(int i, int j, int n) {
		if (i == n - 1 && j == n - 1) {
			path[i][j] = 1;
			return 1;
		}

		if (a[i][j] == 1) {

			path[i][j] = 1;
			// right
			if (findPath(i, j + 1, n) == 1)
				return 1;
			// bottom
			if (findPath(i + 1, j, n) == 1)
				return 1;

			path[i][j] = 0; // remove path from solution matrix so that it can be re explored
		}
		return 0;
	}

	void printPath(int path[][]) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (path[i][j] == 1) {
					System.out.println(i + ":::" + j);
				}
			}
		}
	}
}
