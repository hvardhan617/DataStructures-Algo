package trees;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import trees.BST.Node;

public class LevelOrderTravesalVariations {

	// https://www.techiedelight.com/find-maximum-width-given-binary-tree/
	// Function to find maximum width of the tree using level order
	// traversal of given binary tree
	public static void maxWidth(Node root) {
		// return if tree is empty
		if (root == null) {
			return;
		}

		// create an empty queue and enqueue root node
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(root);

		// pointer to store current node
		Node curr = null;

		// stores maximum width
		int max = 0;

		// run till queue is not empty
		while (!queue.isEmpty()) {
			// calculate number of nodes in current level
			// This is equal to width of current level
			int width = queue.size();

			// update maximum width if number of nodes in current level
			// is more than maximum width found so far
			if (max < width) {
				max = width;
			}

			// process every node of current level and enqueue their
			// non-empty left and right child to queue
			while (width-- > 0) {
				curr = queue.poll();

				if (curr.left != null) {
					queue.add(curr.left);
				}

				if (curr.right != null) {
					queue.add(curr.right);
				}
			}
		}

		System.out.print("Maximum width is " + max);
	}

	// https://www.geeksforgeeks.org/perfect-binary-tree-specific-level-order-traversal/
	/*
	 * Given a perfect binary tree, print its nodes in specific level order
	 */
	// Approach 1:
	// We can do standard level order traversal here too but instead of printing
	// nodes directly, we have to store nodes in current level in a temporary array
	// or list 1st and then take nodes from alternate ends (left and right) and
	// print nodes. Keep repeating this for all levels.
	// This approach takes more memory than standard traversal.

	// Approach 2:
	// The standard level order traversal idea will slightly change here. Instead of
	// processing ONE node at a time, we will process TWO nodes at a time. And while
	// pushing children into queue, the enqueue order will be: 1st node’s left
	// child, 2nd node’s right child, 1st node’s right child and 2nd node’s left
	// child.
	void printSpecificLevelOrder(Node node) {
		if (node == null)
			return;

		// Let us print root and next level first
		System.out.print(node.data);

		// Since it is perfect Binary Tree, right is not checked
		if (node.left != null)
			System.out.print(" " + node.left.data + " " + node.right.data);

		// Do anything more if there are nodes at next level in
		// given perfect Binary Tree
		if (node.left.left == null)
			return;

		// Create a queue and enqueue left and right children of root
		Queue<Node> q = new LinkedList<Node>();
		q.add(node.left);
		q.add(node.right);

		// We process two nodes at a time, so we need two variables
		// to store two front items of queue
		Node first = null, second = null;

		// traversal loop
		while (!q.isEmpty()) {
			// Pop two items from queue
			first = q.poll();
			second = q.poll();

			// Print children of first and second in reverse order
			System.out.print(" " + first.left.data + " " + second.right.data);
			System.out.print(" " + first.right.data + " " + second.left.data);

			// If first and second have grandchildren, enqueue them
			// in reverse order
			if (first.left.left != null) {
				q.add(first.left);
				q.add(second.right);
				q.add(first.right);
				q.add(second.left);
			}
		}
	}

	// https://www.techiedelight.com/reverse-level-order-traversal-binary-tree/
	// use queue & stack
	// Function to print reverse level order traversal of given binary tree
	public static void reverseLevelOrderTraversal(Node root) {
		if (root == null) {
			return;
		}

		// create an empty queue and enqueue root node
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(root);

		// create an stack to reverse level order nodes
		Deque<Integer> stack = new ArrayDeque<>();

		// pointer to store current node
		Node curr;

		// run till queue is not empty
		while (!queue.isEmpty()) {
			// process each node in queue and enqueue their children
			curr = queue.poll();

			// push current node to stack
			stack.push(curr.data);

			// important - process right node before left node
			if (curr.right != null) {
				queue.add(curr.right);
			}

			if (curr.left != null) {
				queue.add(curr.left);
			}
		}

		// pop all nodes from the stack and print them
		while (!stack.isEmpty()) {
			System.out.print(stack.poll() + " ");
		}
	}

	// Iterative function to print the diagonal elements of given binary tree
	// The idea is to use a queue to store only the left child of current node.
	// After printing the data of current node make the current node to its right
	// child, if present.
	// A delimiter NULL is used to mark the starting of next diagonal.
	// https://www.geeksforgeeks.org/iterative-diagonal-traversal-binary-tree/
	public static void diagonalPrint(Node root) {
		// base case
		if (root == null)
			return;

		// inbuilt queue of Treenode
		Queue<Node> q = new LinkedList<Node>();

		// add root
		q.add(root);

		// add delimiter
		q.add(null);

		while (q.size() > 0) {
			Node temp = q.poll();

			// if current is delimiter then insert another
			// for next diagonal and cout nextline
			if (temp == null) {

				// if queue is empty return
				if (q.size() == 0)
					return;

				// output nextline
				System.out.println();

				// add delimiter again
				q.add(null);
			} else {
				while (temp != null) {
					System.out.print(temp.data + " ");

					// if left child is present
					// add into queue
					if (temp.left != null)
						q.add(temp.left);

					// current equals to right child
					temp = temp.right;
				}
			}
		}
	}
}
