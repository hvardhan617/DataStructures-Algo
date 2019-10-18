package trees;

//https://www.techiedelight.com/find-size-largest-bst-in-binary-tree/
//check if a node is a BST by doing bottomup(postorder) traversal.
//a node is BST if its left n right subtrees are BST,
//value at a node must be more than max from left and less than min from right
//O(n)
//https://www.youtube.com/watch?v=4fiDs7CCxkc
public class LargestBSTinBT {

	// Data structure to store a BST node
	static class Node {
		// stores value of this node
		int data;

		// stores left and right child pointer for this node
		Node left, right;

		// Constructor
		Node(int data) {
			this.data = data;
			this.left = this.right = null;
		}
	};

	// Data structure to store information about binary tree rooted under a node
	static class SubTreeInfo {
		// stores the min and the max value in the binary tree rooted under the current
		// node
		// min, max fields are relevant only if isBST flag is true
		int min, max;

		// stores the size of largest BST in binary tree rooted under the current node
		int size;

		// true if binary tree rooted under the current node is a BST
		boolean isBST;

		// Constructor
		SubTreeInfo(int min, int max, int size, boolean isBST) {
			this.min = min;
			this.max = max;
			this.size = size;
			this.isBST = isBST;
		}
	};

	// Recursive function to find the size of the largest BST in a given binary tree
	public static SubTreeInfo findLargestBST(Node root) {
		// Base case: empty tree
		if (root == null) {
			return new SubTreeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
		}

		// Recur for left subtree and right subtrees
		SubTreeInfo left = findLargestBST(root.left);
		SubTreeInfo right = findLargestBST(root.right);

		SubTreeInfo info = null;

		// Check if binary tree rooted under the current root is a BST

		// 1. Left and right subtree are also BST
		// 2. The value of the root node should be more than the largest value
		// in the left subtree
		// 3. The value of the root node should be less than the smallest value
		// in the right subtree
		if (left.isBST && right.isBST && (root.data > left.max && root.data < right.min)) {
			info = new SubTreeInfo(Math.min(root.data, Math.min(left.min, right.min)),
					Math.max(root.data, Math.max(left.max, right.max)), left.size + 1 + right.size, true);
		} else {
			// If binary tree rooted under the current root is not a BST
			// return the size of largest BST in its left and right subtree

			info = new SubTreeInfo(0, 0, Math.max(left.size, right.size), false);
		}

		return info;
	}

	public static void main(String[] args) {
		/*
		 * Construct below binary tree 10 / \ / \ 15 8 / \ / \ / \ / \ 12 20 5 9 / \ / \
		 * \ / \ / \ \ 2 14 4 7 10
		 */

		Node root = new Node(10);

		root.left = new Node(15);
		root.right = new Node(8);

		root.left.left = new Node(12);
		root.left.right = new Node(20);
		root.right.left = new Node(5);
		root.right.right = new Node(9);

		root.left.left.left = new Node(2);
		root.left.left.right = new Node(14);
		root.right.left.left = new Node(4);
		root.right.left.right = new Node(7);

		root.right.right.right = new Node(10);

		System.out.print("The size of the largest BST is " + findLargestBST(root).size);
	}
}
