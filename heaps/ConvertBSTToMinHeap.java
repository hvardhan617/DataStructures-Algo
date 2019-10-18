package heaps;

import java.util.ArrayDeque;
import java.util.Queue;

//https://www.techiedelight.com/convert-binary-search-tree-into-min-heap
//If BST is complete,do inorder traversal ,store keys in queue,do preorder traversal & replace values at nodes with keys from queue
//If BST in not complete,so inorder traversal & construct BST using Level order traversal using keys from queue
//O(n) & O(n)
public class ConvertBSTToMinHeap {

	static class Node {
		int data;
		Node left, right;

		Node(int data) {
			this.data = data;
			this.left = this.right = null;
		}
	};

	// Function to perform inorder traversal of a binary tree and
	// push all nodes in a queue (in encountered order)
	public static void inorder(Node root, Queue<Integer> keys) {
		if (root == null) {
			return;
		}

		inorder(root.left, keys);
		keys.add(root.data);
		inorder(root.right, keys);
	}

	// Function to perform preorder traversal of the binary tree
	// Assign each encountered node with next key from the queue
	public static void preorder(Node root, Queue<Integer> keys) {
		// base case: empty tree
		if (root == null) {
			return;
		}

		// replace root's key value with next key from the queue
		root.data = keys.poll();

		// process left subtree
		preorder(root.left, keys);

		// process right subtree
		preorder(root.right, keys);
	}

	// Function to convert a BST to a min heap
	public static void convert(Node root) {
		// maintain a queue to store inorder traversal of the tree
		Queue<Integer> keys = new ArrayDeque<>();

		// fill the queue in inorder fashion
		inorder(root, keys);

		// traverse tree in preorder fashion and for each encountered node,
		// dequeue a key from the queue and assign it to the node
		preorder(root, keys);
	}

	// Function to construct a complete binary tree from sorted keys in the queue
	public static Node construct(Queue<Integer> keys) {
		// construct a queue to store the parent nodes
		Queue<Node> q = new ArrayDeque<>();

		// initialize root node of the complete binary tree
		Node root = new Node(keys.poll());

		// add root node to the queue
		q.add(root);

		// loop till all keys are processed
		while (!keys.isEmpty()) {
			// Dequeue front node from the queue
			Node parent = q.poll();

			// allocate left child of the parent node with next key
			parent.left = new Node(keys.poll());

			// add left child node to the queue
			q.add(parent.left);

			// if next key is exists
			if (!keys.isEmpty()) {
				// allocate right child of the parent node with next key
				parent.right = new Node(keys.poll());

				// add right child node to the queue
				q.add(parent.right);

			}
		}

		// return the root node of complete binary tree
		return root;
	}

	// Function to convert a BST into a min heap without using
	// any auxiliary space
	public static Node convert2(Node root) {
		// maintain a queue to store inorder traversal of the tree
		Queue<Integer> keys = new ArrayDeque<>();

		// fill the queue in inorder fashion
		inorder(root, keys);

		// construct a complete binary tree from sorted keys in the queue
		root = construct(keys);
		return root;
	}

}
