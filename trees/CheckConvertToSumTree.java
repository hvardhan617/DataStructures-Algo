package trees;

import trees.BST.Node;

//O(n) O(h) for call stack
public class CheckConvertToSumTree {

	// https://www.techiedelight.com/check-given-binary-tree-sum-tree-not/
	// use post order traversal(bottom up) and check if subtrees sum is the value of
	// root
	// Recursive function to check if given binary tree is a sum tree or not

	// top down traversal wudnt work if we check if value at a node is equal to sum
	// of its children , in a sum tree node value is sum of left & right subtrees
	// and not just its children
	// 1) If the node is a leaf node then sum of subtree rooted with this node is
	// equal to value of this node.
	// 2) If the node is not a leaf node then sum of subtree rooted with this node
	// is twice the value of this node (Assuming that the tree rooted with this node
	// is SumTree).
	public static int isSumTree(Node root) {
		// base case: empty tree
		if (root == null) {
			return 0;
		}

		// special case: leaf node
		// returned by leaf node
		if (root.left == null && root.right == null) {
			return root.data;
		}

		// if root's value is equal to sum of all elements present in its
		// left and right subtree
		// returned by non leaf nodes
		if (root.data == isSumTree(root.left) + isSumTree(root.right)) {
			return 2 * root.data;
		}

		return Integer.MIN_VALUE;
	}

	// https://www.techiedelight.com/inplace-convert-a-tree-sum-tree/
	// Recursive function to in-place convert the given binary tree to its
	// sum tree by traversing the tree in post-order manner
	// Convert a given tree to a tree where every node contains sum of
	// values of nodes in left and right subtrees in the original tree
	int toSumTree(Node node) {
		// Base case
		if (node == null)
			return 0;

		// Store the old value
		int old_val = node.data;

		// Recursively call for left and right subtrees and store the sum
		// as new value of this node
		node.data = toSumTree(node.left) + toSumTree(node.right);

		// Return the sum of values of nodes in left and right subtrees
		// and old_value of this node
		return node.data + old_val;
	}
}
