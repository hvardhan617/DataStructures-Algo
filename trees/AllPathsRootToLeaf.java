package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

//https://www.geeksforgeeks.org/print-root-leaf-path-without-using-recursion/
//iterative preorder as top to bottom (root->leaf)+Stack+parentMap
public class AllPathsRootToLeaf {

	/* A binary tree node */
	static class Node {
		int data;
		Node left, right;

		Node(int data) {
			left = right = null;
			this.data = data;
		}
	};

	static class StackNode {
		Node node;
		int sum;

		StackNode(Node node, int sum) {
			this.node = node;
			this.sum = sum;
		}
	}

	/*
	 * Function to print root to leaf path for a leaf using parent nodes stored in
	 * map
	 */
	public static void printTopToBottomPath(Node curr, HashMap<Node, Node> parentMap) {
		Stack<Node> stk = new Stack<>();

		// start from leaf node and keep on pushing
		// nodes into stack till root node is reached
		while (curr != null) {
			stk.push(curr);
			curr = parentMap.get(curr);
		}

		// Start popping nodes from stack and print them
		while (!stk.isEmpty()) {
			curr = stk.pop();
			System.out.print(curr.data + " ");
		}
		System.out.println();
	}

	/*
	 * An iterative function to do preorder traversal of binary tree and print root
	 * to leaf path without using recursion
	 */
	public static void printRootToLeaf(Node root) {
		// Corner Case
		if (root == null)
			return;

		// Create an empty stack and push root to it
		Stack<Node> nodeStack = new Stack<>();
		nodeStack.push(root);

		// Create a map to store parent pointers of binary
		// tree nodes
		HashMap<Node, Node> parentMap = new HashMap<>();

		// parent of root is NULL
		parentMap.put(root, null);

		/*
		 * Pop all items one by one. Do following for every popped item a) push its
		 * right child and set its parent pointer b) push its left child and set its
		 * parent pointer Note that right child is pushed first so that left is
		 * processed first
		 */
		while (!nodeStack.isEmpty()) {
			// Pop the top item from stack
			Node current = nodeStack.pop();

			// If leaf node encountered, print Top To
			// Bottom path
			if (current.left == null && current.right == null)
				printTopToBottomPath(current, parentMap);

			// Push right & left children of the popped node
			// to stack. Also set their parent pointer in
			// the map
			if (current.right != null) {
				parentMap.put(current.right, current);
				nodeStack.push(current.right);
			}
			if (current.left != null) {
				parentMap.put(current.left, current);
				nodeStack.push(current.left);
			}
		}
	}

	// Function to calculate maximum root-to-leaf sum in a binary tree
	// https://www.techiedelight.com/find-maximum-sum-root-to-leaf-path-binary-tree/
	// try using iterative preorder ,construct parent map and store sum when
	// encountered a leaf node
	public static int MaxrootToLeafSum(Node root) {
		// base case: tree is empty
		if (root == null) {
			return 0;
		}

		// calculate maximum node-to-leaf sum for left child
		int left = MaxrootToLeafSum(root.left);

		// calculate maximum node-to-leaf sum for right child
		int right = MaxrootToLeafSum(root.right);

		// consider maximum sum child
		return (left > right ? left : right) + root.data;
	}

	// https://www.techiedelight.com/print-all-paths-from-root-to-leaf-nodes-binary-tree/
	// https://www.youtube.com/watch?v=zIkDfgFAg60
	// inorder + queue
	static void allPathsFromRootToLeaf(Node root) {
		Queue<Node> s = new LinkedList<>();
		if (root == null)
			return;
		s.add(root);
		inOrderRecursive(root.left);

		// print contents of stack on reqching leaf node
		if (root.left == null && root.right == null) {
			while (!s.isEmpty()) {
				System.out.println(s.poll().data);
			}
		}

		inOrderRecursive(root.right);
		s.poll();
	}

	// https://www.techiedelight.com/inorder-tree-traversal-iterative-recursive/
	public static void inOrderRecursive(Node root) {
		if (root != null) {
			inOrderRecursive(root.left);
			System.out.println(root.data);
			inOrderRecursive(root.right);
		}
	}

	// https://www.techiedelight.com/find-maximum-sum-root-to-leaf-path-binary-tree/
	// Use post order traversal(since it is a bottom Up traversal) & maintain
	// maxSumSoFar
	// Function to print maximum sum root-to-leaf path in a given binary tree
	public static void findMaxSumPath(Node root) {
		int sum = MaxrootToLeafSum(root);
		System.out.println("Maximum sum is " + sum);
		System.out.println("Maximum sum path is: ");

		printPath(root, sum);
	}

	// Function to print root-to-leaf path having given sum in a binary tree
	public static boolean printPath(Node root, int sum) {
		// base case
		if (sum == 0) {
			return true;
		}

		// base case
		if (root == null) {
			return false;
		}

		// recur for left and right subtree with reduced sum
		boolean left = printPath(root.left, sum - root.data);
		boolean right = printPath(root.right, sum - root.data);

		// print current node if it lies on path having given sum
		if (left || right) {
			System.out.print(root.data + " ");
		}

		return left || right;
	}

	// https://www.geeksforgeeks.org/sum-numbers-formed-root-leaf-paths/
	// iterative preorder + parentMap (cant store in list else need to maintain
	// visited boolean array)
	public static int sumOfNumbers(Node root) {

		Stack<Node> s = new Stack<>();
		s.push(root);
		List<Node> list = new ArrayList<>();
		int sum = 0;

		while (!s.isEmpty()) {
			Node temp = s.pop();
			list.add(temp);

			if (temp.left == null && temp.right == null) {
				StringBuilder sb = new StringBuilder();
				for (Node n : list) {
					sb.append(n.data);
					System.out.print(n.data);
				}
				System.out.println();
//				sum = sum + Integer.valueOf(sb.toString());
				list.remove(list.size() - 1);
			}

			if (temp.right != null) {
				s.push(temp.right);
			}
			if (temp.left != null) {
				s.push(temp.left);
			}

		}

		return sum;
	}

	public static void main(String args[]) {
//		Node root = new Node(10);
//		root.left = new Node(8);
//		root.right = new Node(2);
//		root.left.left = new Node(3);
//		root.left.right = new Node(5);
//		root.right.left = new Node(2);
//		printRootToLeaf(root);

		Node root = new Node(6);
		root.left = new Node(3);
		root.right = new Node(5);
		root.right.right = new Node(4);
		root.left.left = new Node(2);
		root.left.right = new Node(5);
		root.left.right.right = new Node(4);
		root.left.right.left = new Node(7);
		System.out.println(sumOfNumbers(root));
	}
}
