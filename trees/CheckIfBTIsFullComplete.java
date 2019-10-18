package trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import trees.AllPathsRootToLeaf.Node;

//all levels complete except last similar to heap In the traversal, once a node is found which is NOT a Full Node, all the following nodes must be leaf nodes
//if all nodes have either 0 or 2 nodes - full
//https://www.geeksforgeeks.org/check-whether-binary-tree-complete-not-set-2-recursive-solution/
//https://www.geeksforgeeks.org/check-whether-binary-tree-full-binary-tree-not/
//https://www.geeksforgeeks.org/check-whether-binary-tree-full-binary-tree-not-iterative-approach/
public class CheckIfBTIsFullComplete {

	public static boolean isFull(Node root) {

		Stack<Node> s = new Stack<>();
		s.push(root);

		while (!s.isEmpty()) {
			Node temp = s.pop();

			if ((temp.left != null && temp.right == null) || (temp.right != null && temp.left == null)) {
				return false;
			}

			if (temp.left == null && temp.right == null)
				continue;
			if (temp.left != null && temp.right != null) {
				s.push(temp.right);
				s.push(temp.left);
			}
		}
		return true;

	}

	/*
	 * Given a binary tree, return true if the tree is complete else false
	 */
	static boolean isCompleteBT(Node root) {
		// Base Case: An empty tree is complete Binary Tree
		if (root == null)
			return true;

		// Create an empty queue
		Queue<Node> queue = new LinkedList<>();

		// Create a flag variable which will be set true
		// when a non full node is seen
		boolean flag = false;

		// Do level order traversal using queue.
		queue.add(root);
		while (!queue.isEmpty()) {
			Node temp_node = queue.remove();

			/* Check if left child is present */
			if (temp_node.left != null) {
				// If we have seen a non full node, and we see a node
				// with non-empty left child, then the given tree is not
				// a complete Binary Tree
				if (flag == true)
					return false;

				// Enqueue Left Child
				queue.add(temp_node.left);
			}
			// If this a non-full node, set the flag as true
			else
				flag = true;

			/* Check if right child is present */
			if (temp_node.right != null) {
				// If we have seen a non full node, and we see a node
				// with non-empty right child, then the given tree is not
				// a complete Binary Tree
				if (flag == true)
					return false;

				// Enqueue Right Child
				queue.add(temp_node.right);

			}
			// If this a non-full node, set the flag as true
			else
				flag = true;
		}
		// If we reach here, then the tree is complete Binary Tree
		return true;
	}

	public static void main(String args[]) {

	}

}
