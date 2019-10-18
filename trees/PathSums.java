package trees;

import java.util.HashMap;
import java.util.Stack;

public class PathSums {

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

	// https://www.geeksforgeeks.org/root-to-leaf-path-sum-equal-to-a-given-number/
	/*
	 * Given a tree and a sum, return true if there is a path from the root down to
	 * a leaf (do a preorder traversal), such that adding up all the values along
	 * the path equals the given sum.
	 * 
	 * Strategy: subtract the node value from the sum when recurring down, and check
	 * to see if the sum is 0 when you run out of tree.
	 */

	boolean haspathSum(Node node, int sum) {
		// base case:if we have reached null node and sum == 0, we return true
		if (node == null && sum == 0)
			return true;
		else {
			boolean ans = false;

			/* otherwise check both subtrees */
			int subsum = sum - node.data;
			if (subsum == 0 && node.left == null && node.right == null)
				return true;
			if (node.left != null)
				ans = ans || haspathSum(node.left, subsum);
			if (node.right != null)
				ans = ans || haspathSum(node.right, subsum);
			return ans;
		}
	}

	// https://segmentfault.com/a/1190000016371318
	// root -> leaf , so iterative preorder traversal with stack ( store node & sum)
	// Iterative preorder needs one stack,push root,right & left
	boolean hasPathSumIterative(Node root, int sum) {

		Stack<StackNode> s = new Stack<>();
		s.push(new StackNode(root, sum - root.data));

		while (!s.isEmpty()) {
			StackNode temp = s.pop();

			if (temp.sum == 0 && temp.node.left == null && temp.node.right == null) {
				return true;
			}

			if (temp.node.right != null) {
				s.push(new StackNode(temp.node.right, temp.sum - temp.node.right.data));
			}

			if (temp.node.left != null) {
				s.push(new StackNode(temp.node.left, temp.sum - temp.node.left.data));
			}
		}
		return false;

	}

	// Preorder traversal + parent Map
	void printPathSum(Node root, int sum) {

		HashMap<Node, Node> hm = new HashMap<>();
		Stack<StackNode> s = new Stack<>();
		s.push(new StackNode(root, sum - root.data));
		hm.put(root, null);

		while (!s.isEmpty()) {
			StackNode temp = s.pop();

			// if leaf node is reached with 0 sum
			if (temp.sum == 0 && temp.node.left == null && temp.node.right == null) {
				printPath(temp.node, hm);
			}

			if (temp.node.right != null) {
				s.push(new StackNode(temp.node.right, temp.sum - temp.node.right.data));
				hm.put(temp.node.right, temp.node);
			}

			if (temp.node.left != null) {
				s.push(new StackNode(temp.node.left, temp.sum - temp.node.left.data));
				hm.put(temp.node.left, temp.node);
			}
		}
	}

	private void printPath(Node temp, HashMap<Node, Node> hm) {

		Stack<Node> s = new Stack<>();
		while (temp != null) {
			s.push(temp);
			temp = hm.get(temp);
		}

		while (!s.isEmpty()) {
			System.out.println(s.pop().data);
		}
	}
}
