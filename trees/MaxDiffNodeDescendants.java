package trees;

import java.util.concurrent.atomic.AtomicInteger;

import trees.AllPathsRootToLeaf.Node;

//https://www.techiedelight.com/find-maximum-difference-node-descendants/
//using bottomup (postorder) traversal and calculating diff and maintaining track of max diff so far
public class MaxDiffNodeDescendants {

	// bottom up , get the min node from a nodes left subtree and right subtree
	// Helper function to find maximum difference between a node and its
		// descendants in a binary tree
		public static int maxDifference(Node root, AtomicInteger diff)
		{
			// base case: if tree is empty, return infinity
			if (root == null) {
				return Integer.MAX_VALUE;
			}

			// recur for left and right subtree
			int left = maxDifference(root.left, diff);
			int right = maxDifference(root.right, diff);

			// find maximum difference between current node and its descendants
			int d = root.data - Math.min(left, right);

			// update the maximum difference found so far if required
			diff.set(Math.max(diff.get(), d));

			// in order for difference to be maximum, the function should return
			// minimum value among all nodes in sub-tree rooted at it
			return Math.min(Math.min(left, right), root.data);
		}

		// Find maximum difference between a node and its descendants in a binary tree
		public static int maxDifference(Node root)
		{
			// Using AtomicBoolean as Integer is passed by value in Java
			AtomicInteger diff = new AtomicInteger(Integer.MIN_VALUE);
			maxDifference(root, diff);

			return diff.get();
		}

		// main function
		public static void main(String[] args)
		{
		    /* Construct below tree
			          6
			        /   \
			       /     \
			      3       8
			            /   \
			           /     \
			          2          4
			        /   \
			       /     \
			      1       7
		    */

			Node root = new Node(6);
			root.left = new Node(3);
			root.right = new Node(8);
			root.right.left = new Node(2);
			root.right.right = new Node(4);
			root.right.left.left = new Node(1);
			root.right.left.right = new Node(7);

			System.out.print(maxDifference(root));
		}

}
