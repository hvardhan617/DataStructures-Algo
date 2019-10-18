package trees;

import java.util.ArrayList;
import java.util.List;

//O(n)
public class PrintKSumPathsTopDown {

	// binary tree node
	static class Node {
		int data;
		Node left, right;

		Node(int x) {
			data = x;
			left = right = null;
		}
	};

	static List<Integer> path = new ArrayList<Integer>();

	// https://www.geeksforgeeks.org/print-k-sum-paths-binary-tree/
	// https://www.youtube.com/watch?v=7oL8kDCk1OI
	// https://www.youtube.com/watch?v=CnDF4TQEkzA&t=333s
	// preorder traversal of the given tree. We also need a container (vector) to
	// keep track of the path that led to that node.
	// This function prints all paths that have sum k
	static void printKPathUtil(Node root, int k) {

		// empty node
		if (root == null)
			return;

		// add current node to the path
		path.add(root.data);

		// check if there's any k sum path
		// in the left sub-tree.
		printKPathUtil(root.left, k);

		// check if there's any k sum path
		// in the right sub-tree.
		printKPathUtil(root.right, k);

		// check if there's any k sum path that
		// terminates at this node
		// Traverse the entire path as
		// there can be negative elements too
		int f = 0;
//			System.out.println(path.toString());
		for (int j = path.size() - 1; j >= 0; j--) {
			f += path.get(j);

			// If path sum is k, print the path
			if (f == k)
				printPath(path, j);
		}

		// Remove the current element from the path
		path.remove(path.size() - 1);
	}

	private static void printPath(List<Integer> path, int i) {
		for (int j = i; j < path.size(); j++) {
			System.out.print(path.get(j) + " ");
			System.out.println();
		}
	}

	// Driver code
	public static void main(String args[]) {
		Node root = new Node(1);
		root.left = new Node(3);
		root.left.left = new Node(2);
		root.left.right = new Node(1);
		root.left.right.left = new Node(1);
		root.right = new Node(-1);
		root.right.left = new Node(4);
		root.right.left.left = new Node(1);
		root.right.left.right = new Node(2);
		root.right.right = new Node(5);
		root.right.right.right = new Node(2);

		int k = 5;
		printKPathUtil(root, k);
	}
}
