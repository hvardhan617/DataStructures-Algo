package maze;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

//https://www.techiedelight.com/find-shortest-path-source-destination-matrix-satisfies-given-constraints/
public class SourcetoDestinationWithConstraints {

	// queue node used in BFS
	static class Node {
		// (x, y) represents coordinates of a cell in matrix
		int x, y;

		// maintain a parent node for printing final path
		Node parent;

		Node(int x, int y, Node parent) {
			this.x = x;
			this.y = y;
			this.parent = parent;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ')';
		}
	};

	// N x N matrix
	private static int N;

	// Below arrays details all 4 possible movements from a cell
	private static int[] row = { -1, 0, 0, 1 };
	private static int[] col = { 0, -1, 1, 0 };

	// The function returns false if pt is not a valid position
	private static boolean isValid(int x, int y) {
		return (x >= 0 && x < N) && (y >= 0 && y < N);
	}

	// Find shortest route in the matrix from source cell (x, y) to
	// destination cell (N - 1, N - 1)
	public static Node findPath(int matrix[][], int x, int y) {
		// create a queue and enqueue first node
		Queue<Node> q = new ArrayDeque<>();
		Node src = new Node(x, y, null);
		q.add(src);

		// set to check if matrix cell is visited before or not
		Set<String> visited = new HashSet<>();

		String key = src.x + "," + src.y;
		visited.add(key);

		// run till queue is not empty
		while (!q.isEmpty()) {
			// pop front node from queue and process it
			Node curr = q.poll();
			int i = curr.x, j = curr.y;

			// return if destination is found
			if (i == N - 1 && j == N - 1) {
				return curr;
			}

			// value of current cell
			int n = matrix[i][j];

			// check all 4 possible movements from current cell
			// and recur for each valid movement
			for (int k = 0; k < 4; k++) {
				// get next position coordinates using value of current cell
				x = i + row[k] * n;
				y = j + col[k] * n;

				// check if it is possible to go to next position
				// from current position
				if (isValid(x, y)) {
					// construct next cell node
					Node next = new Node(x, y, curr);

					key = next.x + "," + next.y;

					// if it not visited yet
					if (!visited.contains(key)) {
						// push it into the queue and mark it as visited
						q.add(next);
						visited.add(key);
					}
				}
			}
		}

		// return null if path is not possible
		return null;
	}

	// Utility function to print path from source to destination
	private static int printPath(Node node) {
		if (node == null) {
			return 0;
		}
		int len = printPath(node.parent);
		System.out.print(node + " ");
		return len + 1;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 4, 4, 6, 5, 5, 1, 1, 1, 7, 4 }, { 3, 6, 2, 4, 6, 5, 7, 2, 6, 6 },
				{ 1, 3, 6, 1, 1, 1, 7, 1, 4, 5 }, { 7, 5, 6, 3, 1, 3, 3, 1, 1, 7 }, { 3, 4, 6, 4, 7, 2, 6, 5, 4, 4 },
				{ 3, 2, 5, 1, 2, 5, 1, 2, 3, 4 }, { 4, 2, 2, 2, 5, 2, 3, 7, 7, 3 }, { 7, 2, 4, 3, 5, 2, 2, 3, 6, 3 },
				{ 5, 1, 4, 2, 6, 4, 6, 7, 3, 7 }, { 1, 4, 1, 7, 5, 3, 6, 5, 3, 4 } };

		N = matrix.length;

		// Find a route in the matrix from source cell (0, 0) to
		// destination cell (N - 1, N - 1)
		Node node = findPath(matrix, 0, 0);

		if (node != null) {
			System.out.print("Shortest path is: ");
			int len = printPath(node) - 1;
			System.out.println("\nShortest path length is " + len);
		} else {
			System.out.println("Destination not found");
		}
	}
}
