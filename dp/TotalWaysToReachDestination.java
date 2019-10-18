package dp;

//end goal is to return the total number of ways we could reach the destination cell.
//every cell in tabulation matrix denotes the max number of ways possible to reach the current cell
//(up + bottom) = current cell value
public class TotalWaysToReachDestination {

	// without obstacle & direction allowed in right & bottom only
	// https://www.youtube.com/watch?v=Z9XWbqxyn3E
	public int countPaths(int n, int m) {
		int T[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			T[i][0] = 1;
		}

		for (int i = 0; i < m; i++) {
			T[0][i] = 1;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				T[i][j] = T[i - 1][j] + T[i][j - 1];
			}
		}
		return T[n - 1][m - 1];
	}

	public static void main(String args[]) {
		TotalWaysToReachDestination nop = new TotalWaysToReachDestination();
		System.out.print(nop.countPaths(3, 3));
	}

	public int countPathsRecursive(int n, int m) {
		if (n == 1 || m == 1) {
			return 1;
		}
		return countPathsRecursive(n - 1, m) + countPathsRecursive(n, m - 1);
	}

	// https://www.youtube.com/watch?v=AmRvCR6B5no
	// obstacle given with directions right & bottom
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
			return 0;
		}

		int height = obstacleGrid.length;
		int width = obstacleGrid[0].length;

		int[][] paths = new int[height][width];

		// first column
		for (int i = 0; i < height; i++) {
			// if not obstacle
			if (obstacleGrid[i][0] != 1) {
				paths[i][0] = 1;
			} else {
				// remaining can be ignored once we encounter an obstacle as they are
				// unreachable
				break;
			}
		}

		// first row
		for (int j = 0; j < width; j++) {
			if (obstacleGrid[0][j] != 1) {
				paths[0][j] = 1;
			} else {
				break;
			}
		}

		// for spaces not at first row or first column
		for (int i = 1; i < height; i++) {
			for (int j = 1; j < width; j++) {
				if (obstacleGrid[i][j] != 1) {
					paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
				}
			}
		}

		return paths[height - 1][width - 1];
	}
}
