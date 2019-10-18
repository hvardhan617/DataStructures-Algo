package trees;

import java.util.concurrent.atomic.AtomicInteger;

//https://www.techiedelight.com/count-subtrees-bst-whose-nodes-within-range/
//perform postorder traversal (bottomUp) and check if the left n right subtree are within the range along with node itself,increment the count
//O9n) & O(h)
public class SubtreesInARange {

	// Data structure for BST node
	static class Node {
		int data;
		Node left, right;

		Node(int data) {
			this.data = data;
			this.left = this.right = null;
		}
	};

	// Recursive function to insert an key into BST
	public static Node insert(Node root, int key) {
		// if the root is null, create a new node an return it
		if (root == null) {
			return new Node(key);
		}

		// if given key is less than the root node, recur for left subtree
		if (key < root.data) {
			root.left = insert(root.left, key);
		}
		// else recur for right subtree
		else {
			root.right = insert(root.right, key);
		}

		// return root node
		return root;
	}

	// Function to count subtrees in a BST whose nodes lies within a given range
	// It returns true if whole subtree rooted at given node is within range
	public static boolean findSubTrees(Node root, int low, int high, AtomicInteger count) {
		// base case
		if (root == null) {
			return true;
		}

		// increment the subtree count by 1 and return true if the root node,
		// left and right subtree are within the range
		if (findSubTrees(root.left, low, high, count) && findSubTrees(root.right, low, high, count)
				&& (root.data >= low && root.data <= high)) {
			count.incrementAndGet();
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		// input range
		int low = 5, high = 20;

		// BST keys to construct BST shown in the diagram
		int[] keys = { 15, 25, 20, 22, 30, 18, 10, 8, 9, 12, 6 };

		// construct BST
		Node root = null;
		for (int key : keys) {
			root = insert(root, key);
		}

		// use AtomicInteger as Integer is passed by value in Java
		AtomicInteger count = new AtomicInteger(0);

		// get subtrees count
		findSubTrees(root, low, high, count);
		System.out.print("The number of subtrees are: " + count);
	}
}
