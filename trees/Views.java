package trees;

import java.util.LinkedList;
import java.util.Queue;

import trees.BST.Node;

public class Views {

	// https://www.techiedelight.com/print-corner-nodes-every-level-binary-tree/
	// mix of leftview and right view
	public static void printCornerNodes(Node root) {
		if (root == null) {
			return;
		}
		boolean nullReached = false;
		int lastBeforeNull;
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		q.add(null);
		nullReached = true;
		lastBeforeNull = root.data;

		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (nullReached) {

				System.out.println("::::" + temp.data + "::::" + lastBeforeNull);
			}
			if (temp == null && !q.isEmpty()) {

				q.add(null);
				nullReached = true;

			} else {
				if (temp != null) {
					// else add the children of extracted node.
					if (temp.left != null) {
						q.add(temp.left);
						lastBeforeNull = temp.left.data;
					}
					if (temp.right != null) {
						q.add(temp.right);
						lastBeforeNull = temp.right.data;
					}
					nullReached = false;

				}
			}
		}
		q.clear();
	}

	// main function
	public static void main(String[] args) {
		/*
		 * Construct below tree 1 / \ / \ 2 3 / / \ / / \ 4 5 6 / / \ \ / / \ \ 7 8 9 10
		 */

		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.right.left = new Node(5);
		root.right.right = new Node(6);
		root.left.left.left = new Node(7);
		root.right.left.left = new Node(8);
		root.right.left.right = new Node(9);
		root.right.right.right = new Node(10);

		printCornerNodes(root);
	}

}
