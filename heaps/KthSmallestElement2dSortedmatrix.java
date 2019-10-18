package heaps;

import java.util.PriorityQueue;

//https://www.geeksforgeeks.org/kth-smallest-element-in-a-row-wise-and-column-wise-sorted-2d-array-set-1/
//https://stackoverflow.com/questions/15179536/kth-smallest-element-in-sorted-matrix
//O(n + kLogn) time.
public class KthSmallestElement2dSortedmatrix {

	static class Node implements Comparable {
		int row;
		int column;
		int value;

		Node(int value, int row, int col) {
			this.value = value;
			this.row = row;
			this.column = col;
		}

		@Override
		public int compareTo(Object o) {
			Node node = (Node) o;
			return this.value - node.value;
		}
	}

	// driver program to test above function
	public static void main(String args[]) {
		int mat[][] = { { 10, 20, 30, 40 }, { 15, 25, 35, 45 }, { 25, 29, 37, 48 }, { 32, 33, 39, 50 }, };
		System.out.println(kthSmallest(mat, 4, 7));

	}

	private static int kthSmallest(int[][] mat, int size, int k) {

		PriorityQueue<Node> p = new PriorityQueue<Node>(size);

		// add elements of first row
		for (int i = 0; i < mat[0].length; i++) {
			p.add(new Node(mat[0][i], 0, i));
			System.out.println(p.size());
		}

		// Replace root with the next element from same column and min-heapify the root.
		for (int i = 0; i < k - 1; i++) {
			Node min = p.poll();
			if (checkValid(min.row, min.column, size)) {
				p.add(new Node(mat[min.row + 1][min.column], min.row + 1, min.column));
			}

		}

		// Return the last extracted root.

		return p.poll().value;
	}

	private static boolean checkValid(int row, int column, int size) {
		if (row < size && row >= 0 && column < size && column >= 0)
			return true;

		return false;
	}
}
