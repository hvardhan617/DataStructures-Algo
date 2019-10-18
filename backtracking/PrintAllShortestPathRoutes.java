package backtracking;

import java.util.ArrayList;
import java.util.List;

//https://www.techiedelight.com/print-all-shortest-routes-rectangular-grid/
public class PrintAllShortestPathRoutes {

	// Recursive function to get all shortest routes in a rectangular grid
		// that start at the cell(i,j) and end at the last cell(M-1,N-1).
		public static void printPaths(int[][] mat, List<Integer> route,
									 int i, int j)
		{
			int M = mat.length;
			int N = mat[0].length;

			// include current cell in route
			route.add(mat[i][j]);

			// if last cell is reached
			if (i == M - 1 && j == N - 1) {
				System.out.println(route);
			}
			else {
				// move down
				if (i + 1 < M)
					printPaths(mat, route, i + 1, j);

				// move right
				if (j + 1 < N)
					printPaths(mat, route, i, j + 1);

				// move diagonally
				if (i + 1 < M && j + 1 < N)
					printPaths(mat, route, i + 1, j + 1);
			}

			// backtrack - remove current cell from route
			route.remove(route.size() - 1);
		}

		// Print all shortest routes in a rectangular grid
		public static void printPaths(int[][] mat)
		{
			// list to store current route
			List<Integer> route = new ArrayList<>();

			// start from the first cell (0, 0)
			printPaths(mat, route, 0, 0);
		}

		public static void main(String[] args)
		{
			int[][] mat =
			{
				{ 1, 2, 3 },
				{ 4, 5, 6 },
				{ 7, 8, 9 }
			};

			printPaths(mat);
		}
}
