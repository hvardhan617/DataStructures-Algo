package heaps;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

//https://www.techiedelight.com/check-binary-tree-is-min-heap/
//TO check structural property - do a level order traversal and check that no non empty child is encountered once u have seen an empty child
//to check heap property - check for each node if it is smaller than its children

//Data structure to store a Binary Tree node

public class CheckBTIsMinHeap {

	static class Node {
		int data;
		Node left, right;

		Node(int data) {
			this.data = data;
			this.left = this.right = null;
		}
	};

	// Function to check if a given binary tree is a min heap or not
	public static boolean isHeap(Node root) {
		// create an empty queue and enqueue root node
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(root);

		// take a boolean flag which becomes true when an empty left or right
		// child is seen for a node
		boolean nonEmptyChildSeen = false;

		// run till queue is not empty
		while (!queue.isEmpty()) {
			// process front node in the queue
			Node curr = queue.poll();

			// left child is non-empty
			if (curr.left != null) {
				// if either heap-property is violated
				if (nonEmptyChildSeen || curr.left.data <= curr.data) {
					return false;
				}

				// enqueue left child
				queue.add(curr.left);
			}
			// left child is empty
			else {
				nonEmptyChildSeen = true;
			}

			// right child is non-empty
			if (curr.right != null) {
				// if either heap-property is violated
				if (nonEmptyChildSeen || curr.right.data <= curr.data) {
					return false;
				}

				// enqueue left child
				queue.add(curr.right);
			}
			// right child is empty
			else {
				nonEmptyChildSeen = true;
			}
		}

		// we reach here only when the given binary tree is a min-heap
		return true;
	}

	public static boolean isMinHeap(Node root) {

		Queue<Node> q = new LinkedList<>();
		q.add(root);

		boolean nullSeen = false;
		while (!q.isEmpty()) {
			Node temp = q.poll();

			if (temp.left != null && temp.right != null && temp.data < temp.left.data && temp.data < temp.right.data) {
				if (nullSeen) {
					return false;
				} else {
					q.add(temp.left);
					q.add(temp.right);
				}
			} else if (temp.left != null && temp.right == null && temp.data < temp.left.data) {
				if (nullSeen) {
					return false;
				} else {
					q.add(temp.left);
					nullSeen = true;
				}
			} else if (temp.left == null && temp.right == null) {
				nullSeen = true;
			} else {
				return false;
			}
		}
		return true;

	}

	public static void main(String[] args) {
		/*
		 * Construct below binary tree 2 / \ / \ 3 4 / \ / \ / \ / \ 5 6 8 10
		 */

		Node root = new Node(2);
		root.left = new Node(3);
		root.right = new Node(4);
		root.left.left = new Node(5);
		root.left.right = new Node(6);
		root.right.left = new Node(8);
		root.right.right = new Node(10);

		if (isHeap(root)) {
			System.out.print("Given Binary Tree is a min-Heap");
		} else {
			System.out.print("Given Binary Tree is not a min-Heap");
		}

		System.out.println(isMinHeap(root));
	}
}
