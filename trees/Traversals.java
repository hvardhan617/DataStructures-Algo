package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import trees.BST.Node;

public class Traversals {
	// https://www.geeksforgeeks.org/iterative-boundary-traversal-of-complete-binary-tree/
	// Function to print the nodes of a
	// binary tree in boundary traversal order
	// Left boundary + Leaf nodes(using BFS)+right boundary + reverse right boundary
	// + append all lists
	void boundaryTraversal(Node root) {
		if (root != null) {

			// If there is only 1 node print it
			// and return
			if (root.left == null && root.right == null) {
				System.out.println(root.data);
				return;
			}

			// List to store order of traversed
			// nodes
			List<Node> list = new ArrayList<>();
			list.add(root);

			// Traverse left boundary without root
			// and last node
			Node L = root.left;
			while (L.left != null) {
				list.add(L);
				L = L.left;
			}

			// BFS designed to only include leaf nodes
			Queue<Node> q = new LinkedList<>();
			q.add(root);
			while (!q.isEmpty()) {
				Node temp = q.poll();

				if (temp.left == null && temp.right == null) {
					list.add(temp);
				}
				if (temp.left != null) {
					q.add(temp.left);
				}
				if (temp.right != null) {
					q.add(temp.right);
				}
			}

			// Traverse right boundary without root
			// and last node
			List<Node> list_r = new ArrayList<>();
			Node R = root.right;
			while (R.right != null) {
				list_r.add(R);
				R = R.right;
			}

			System.out.println(list.toString());

			Collections.reverse(list_r);
			list.addAll(list_r);

			return;
		}
	}

	// https://www.techiedelight.com/preorder-tree-traversal-iterative-recursive/
	public void preOrderRecursive(Node root) {
		if (root != null) {
			System.out.println(root.data);
			preOrderRecursive(root.left);
			preOrderRecursive(root.right);
		}
	}

	// DFS-stack (push root , right, left)
	public void preOrderIterative(Node root) {
		Stack<Node> stack = new Stack<>();
		stack.push(root);

		// push root & process both childs of the node completely at each iteration
		while (!stack.empty()) {
			Node current = stack.pop();
			System.out.println("::::::" + current.data);
			if (current.right != null) {
				stack.push(current.right);
			}
			if (current.left != null) {
				stack.push(current.left);
			}
		}
	}

	// https://www.techiedelight.com/postorder-tree-traversal-iterative-recursive/

	public void postOrderRecursive(Node root) {
		if (root != null) {
			postOrderRecursive(root.left);
			postOrderRecursive(root.right);
			System.out.println(root.data);
		}
	}

	// Iterative function to perform post-order traversal of the tree
	// need 2 stacks (push root, left, right)
	public static void postorderIterative(Node root) {
		// create an empty stack and push root node
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);

		// create another stack to store post-order traversal
		Stack<Integer> out = new Stack<>();

		// push root & process both childs of the node completely at each iteration but
		// with another stack
		// run till stack is not empty
		while (!stack.empty()) {
			// we pop a node from the stack and push the data to output stack
			Node curr = stack.pop();
			out.push(curr.data);

			// push left and right child of popped node to the stack
			if (curr.left != null) {
				stack.push(curr.left);
			}

			if (curr.right != null) {
				stack.push(curr.right);
			}
		}

		// print post-order traversal
		while (!out.empty()) {
			System.out.print(out.pop() + " ");
		}
	}

	// https://www.techiedelight.com/inorder-tree-traversal-iterative-recursive/
	public static void inOrderRecursive(Node root) {
		if (root != null) {
			inOrderRecursive(root.left);
			System.out.println(root.data);
			inOrderRecursive(root.right);
		}
	}

	// Careful!!!!
	public void inOrderIterative(Node root) {
		Stack<Node> stack = new Stack<>();
		Node curr = root;

		// process root & only one half of the node at each iteration(if-else)
		while (curr != null && !stack.isEmpty()) {
			if (curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				curr = stack.pop();
				System.out.println("::::" + curr.data);
				curr = curr.right;
			}
		}
	}

	// BFS- queue
	public void levelOrderIterative(Node root) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {

			/*
			 * poll() removes the present head. For more information on poll() visit
			 * http://www.tutorialspoint.com/java/util/linkedlist_poll.htm
			 */
			Node tempNode = queue.poll();
			System.out.print(tempNode.data + " ");

			/* Enqueue left child */
			if (tempNode.left != null) {
				queue.add(tempNode.left);
			}

			/* Enqueue right child */
			if (tempNode.right != null) {
				queue.add(tempNode.right);
			}
		}
	}

}
