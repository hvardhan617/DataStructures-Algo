package trees;

import trees.BST.Node;

public class InOrderSuccessorPredecessor {

	// https://www.techiedelight.com/find-inorder-predecessor-given-key-bst/
	// A nodes inorder predecessor is its left subtrees rightmost child
	// O(h) where h is height of tree.
	public static Node inOrderPredecessor(Node root, int data) {
		if (root == null)
			return null;
		Node curr = searchNode(root, data);
		// if node has left subtree,its predecessor would be highest node in left
		// subtree
		if (curr.left != null) {
			Node temp = curr.left;
			while (temp.right != null) {
				temp = temp.right;
			}
			return temp;
		}
		// if it has right subtree or leaf node, we need to figure out its
		// ancestor(top-down from
		// root)
		else {
			Node ancestor = root;
			Node predecessor = null;

			while (ancestor != curr) {
				// update predecessor only if it becomes part of right subtree
				if (curr.data <= ancestor.data) {
					ancestor = ancestor.left;
				} else {
					predecessor = ancestor;
					ancestor = ancestor.right;
				}
			}
			return predecessor;
		}
	}

	// https://www.youtube.com/watch?v=5cPbNCrdotA
	// we could use inorder traversal but it would be expensive.
	public static Node getInorderSuccessor(Node root, int data) {
		if (root == null) {
			return null;
		}
		Node curr = searchNode(root, data);
		// CASE 1: if the node has a right subtree,inorder successor would be the left
		// most node
		// in the right subtree
		if (curr.right != null) {
			Node temp = curr.right;
			while (temp.left != null) {
				temp = temp.left;
			}
			return temp;
		}
		// CASE 2: if the node doesnt have a right subtree
		else {
			Node ancestor = root;
			Node successor = null;

			while (ancestor != curr) {
				// if node falls in left subtree
				if (curr.data <= ancestor.data) {
					successor = ancestor;
					ancestor = ancestor.left;
				} else {
					ancestor = ancestor.right;
				}
			}

			return successor;
		}

	}

	// Iterative function to find in-order predecessor for given key in a BST
	// O(n) where h is height of tree.
	public static Node findPredecessor(Node root, int key) {
		Node prec = null;

		while (true) {
			// if given key is less than the root node, visit left subtree
			if (key < root.data) {
				root = root.left;
			}

			// if given key is more than the root node, visit right subtree
			else if (key > root.data) {
				// update predecessor to current node before visiting
				// right subtree
				prec = root;
				root = root.right;
			}

			// if node with key's value is found, predecessor is maximum
			// value node in its left subtree (if any)
			else {
				if (root.left != null) {
					prec = findMaximum(root.left);
				}
				break;
			}

			// if key doesn't exist in binary tree
			if (root == null)
				return null;
		}

		// return predecessor if any
		return prec;
	}

	// Helper function to find maximum value node in given BST
	static Node findMaximum(Node root) {
		while (root.right != null) {
			root = root.right;
		}

		return root;
	}

	private static Node searchNode(Node root, int data) {
		// TODO Auto-generated method stub
		return null;
	}
}
