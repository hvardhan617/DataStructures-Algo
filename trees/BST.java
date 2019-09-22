package trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

//PreOrder - needs 1 stack,push root & process both of its children <push root,right,left>
//PostOrder - needs 2 stacks,push root & process both of its children <push root,left,right>
//InOrder - needs 1 stack,push root & process only one child <Push root,push left,next iteration push right>
public class BST {
	private static Map<Integer, List<Integer>> mapVerticalDistance = null;
	private static Map<Integer, Integer> bottomViewMap = new TreeMap<Integer, Integer>(); // maintains keys in sorted
																							// order
	private static Map<Integer, Integer> topViewMap = new TreeMap<Integer, Integer>();// maintains keys in sorted order

	private static class Node {
		int data;
		Node left;
		Node right;
		Node parent;// for LCA

		Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
	}

	private static class QueueNode {
		Node node;
		int hd;

		QueueNode(Node node, int hd) {
			this.node = node;
			this.hd = hd;

		}

	}

	// https://www.techiedelight.com/insertion-in-bst/
	public Node insertRec(Node root, int data) {

		if (root == null) {
			root = new Node(data);
			return root;
		} else if (data <= root.data) {
			root.left = insertRec(root.left, data);
		} else if (data > root.data) {
			root.right = insertRec(root.right, data);
		}
		return root;
	}

	// loop is mandatory in iterative solutions
	public Node insertIterative(Node root, int data) {
		if (root == null) {
			root = new Node(data);
			return root;
		}
		while (true) {

			// check if data has to be placed in left subtree or right subtree
			if (data <= root.data) {
				// check at what left level it has to be placed
				if (root.left == null) {
					root.left = new Node(data);
					return root;
				} else {
					root = root.left;
				}
			} else if (data > root.data) {
				if (root.right == null) {
					root.right = new Node(data);
					return root;
				} else {
					root = root.right;
				}

			}
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
	public void inOrderRecursive(Node root) {
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

	// need to use vertical distance - Iterative approach
	// push each node along with its hd into the queue,construct map of hds as keys
	// with list of nodes in that hd as values
	// https://www.techiedelight.com/vertical-traversal-binary-tree/
	// https://makeinjava.com/vertical-order-traversal-binary-tree-java-recursive-example/
	public static void verticalOrderUsingLevelorder(Node root) {
		if (root == null) {
			return;
		}
		Queue<QueueNode> queue = new LinkedList<QueueNode>();
		QueueNode qn = new QueueNode(root, 0);
		queue.add(qn);

		while (!queue.isEmpty()) {
			List<Integer> list = null;
			// poll() removes as well as return current head
			QueueNode tempNode = queue.poll();
			// check if u already had a list with the hd,if yes add item to the same list
			// else create a new list and add item into the list.
			if (mapVerticalDistance.containsKey(tempNode.hd)) {
				list = mapVerticalDistance.get(tempNode.hd);
			} else {
				list = new ArrayList<Integer>();
			}
			list.add(tempNode.node.data);
			mapVerticalDistance.put(tempNode.hd, list);
			if (tempNode.node.left != null) {
				queue.add(new QueueNode(tempNode.node.left, tempNode.hd - 1));
			}
			if (tempNode.node.right != null) {
				queue.add(new QueueNode(tempNode.node.right, tempNode.hd + 1));
			}
		}

	}

	// Recursive approach
	public static void verticalOrderUsingPreOrder(Node root, int hd) {
		if (root == null) {
			return;
		}

		List<Integer> list = null;
		if (mapVerticalDistance.containsKey(hd)) {
			list = mapVerticalDistance.get(hd);
		} else {
			list = new ArrayList<Integer>();
		}
		list.add(root.data);
		mapVerticalDistance.put(hd, list);
		verticalOrderUsingPreOrder(root.left, hd - 1);
		verticalOrderUsingPreOrder(root.right, hd + 1);
	}

	public static void printVerticalOrder(Node root) {
		if (null == mapVerticalDistance) {
			mapVerticalDistance = new HashMap<Integer, List<Integer>>();
		} else {
			mapVerticalDistance.clear();
		}

//		verticalOrderUsingPreOrder(root, 0);
		verticalOrderUsingLevelorder(root);
		mapVerticalDistance.forEach((key, value) -> System.out.println("Nodes at distance:::" + key + "::::" + value));

	}

	// the moment u encounter null in q,we change null reached to true,so in next
	// iteration we print d node value
	public static void leftViewIterative(Node root) {
		if (root == null)
			return;
		boolean nullReached = false;
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		q.add(null);
		nullReached = true;
		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (nullReached) {
				System.out.println("::::" + temp.data);
			}

			if (temp == null && !q.isEmpty()) {

				q.add(null);
				nullReached = true;

			} else {
				// else add the children of extracted node.
				if (temp.left != null) {
					q.add(temp.left);
				}
				if (temp.right != null) {
					q.add(temp.right);
				}
				nullReached = false;
			}
		}
		q.clear();

	}

	public static void rightViewIterative(Node root) {
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
				System.out.println("::::" + lastBeforeNull);
			}
			if (temp == null && !q.isEmpty()) {

				q.add(null);
				nullReached = true;

			} else {
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
		q.clear();

	}

	// https://www.geeksforgeeks.org/bottom-view-binary-tree/
	// using horizontal distance computed using vertical order traversal
	// instead of list of nodes stored against a hd, we always replace the value
	// with last or latest node
	// poll & put in map
	public static void printBottomView(Node root) {
		if (root == null) {
			return;
		}

		Queue<QueueNode> q = new LinkedList<QueueNode>();
		QueueNode qn = new QueueNode(root, 0);
		q.add(qn);

		while (!q.isEmpty()) {

			QueueNode temp = q.poll();
			bottomViewMap.put(temp.hd, temp.node.data);

			if (temp.node.left != null) {
				q.add(new QueueNode(temp.node.left, temp.hd - 1));

			}
			if (temp.node.right != null) {
				q.add(new QueueNode(temp.node.right, temp.hd + 1));
			}

			System.out.println(q);
		}

		System.out.println(bottomViewMap);
		bottomViewMap.forEach((key, value) -> System.out.println("Bottom View:::::::" + value));

		/*
		 * // Extract the entries of map into a set to traverse // an iterator over
		 * that. Set<Entry<Integer, Integer>> set = map.entrySet();
		 * 
		 * // Make an iterator Iterator<Entry<Integer, Integer>> iterator =
		 * set.iterator();
		 * 
		 * // Traverse the map elements using the iterator. while (iterator.hasNext()) {
		 * Map.Entry<Integer, Integer> me = iterator.next();
		 * System.out.print(me.getValue()+" "); }
		 */
	}

	// https://www.geeksforgeeks.org/print-nodes-top-view-binary-tree/
	public static void topView(Node root) {

		if (root == null) {
			return;
		}

		Queue<QueueNode> q = new LinkedList<QueueNode>();
		q.add(new QueueNode(root, 0));

		while (!q.isEmpty()) {

			QueueNode temp = q.poll();
			// insert it only one time for a horizontal distance and ignore the rest
			// updations on the existing hds.
			if (!topViewMap.containsKey(temp.hd)) {
				topViewMap.put(temp.hd, temp.node.data);
			}

			if (temp.node.left != null) {
				q.add(new QueueNode(temp.node.left, temp.hd - 1));

			}
			if (temp.node.right != null) {
				q.add(new QueueNode(temp.node.right, temp.hd + 1));
			}

			System.out.println(q);
		}

		topViewMap.forEach((key, value) -> System.out.println("Top View:::::::" + value));

	}

	// https://www.geeksforgeeks.org/diameter-of-a-binary-tree-in-on-a-new-method/
	public static void printNodesAtLevel(Node root, int level) {
		if (root == null) {
			return;
		} else {
			int height = 0;
			Queue<Node> q = new LinkedList<>();
			q.add(root);
			q.add(null);

			while (!q.isEmpty()) {

				if (height == level - 1) {
					// System.out.println("Node at level::::" + level + ":::::" + temp.data);
					break;
				}

				Node temp = q.poll();

				if (temp == null && !q.isEmpty()) {

					q.add(null);
					height = height + 1;
					System.out.println("Current Height::::" + height);

				} else {

					if (temp.left != null) {
						q.add(temp.left);
					}
					if (temp.right != null) {
						q.add(temp.right);
					}

				}
			}

			while (!q.isEmpty()) {
				Node temp = q.poll();
				if (temp != null)
					System.out.println(temp.data);
			}
		}
	}

	// recursive
	// https://javabypatel.blogspot.com/2015/12/diameter-of-binary-tree.html
	public static int diameter(Node root) {
		/* base case if tree is empty */
		if (root == null)
			return 0;

		/*
		 * get the height of left and right sub trees(for calculating diameter passing
		 * thru root)
		 */
		int lheight = height(root.left);
		int rheight = height(root.right);

		/*
		 * get the diameter of left and right subtrees(if diameter isnt passing thru
		 * root)
		 */
		int ldiameter = diameter(root.left);
		int rdiameter = diameter(root.right);

		/*
		 * Return max of following three 1) Diameter of left subtree 2) Diameter of
		 * right subtree 3) Height of left subtree + height of right subtree + 1
		 */
		return Math.max(lheight + rheight + 1, Math.max(ldiameter, rdiameter));
	}

	// https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/
	/*
	 * Compute the "maxDepth" of a tree -- the number of nodes along the longest
	 * path from the root node down to the farthest leaf node.
	 */
	static int height(Node node) {
		if (node == null)
			return 0;
		else {
			/* compute the depth of each subtree */
			int leftHeight = height(node.left);
			int rightHeight = height(node.right);

			/* use the larger one */
			int h;
			if (leftHeight > rightHeight)
				h = leftHeight + 1;
			else
				h = rightHeight + 1;
			return h;
		}
	}

	public boolean checkIfIdenticalRecursive(Node x, Node y) {
		// base case & for leaf nodes
		if (x == null && y == null) {
			return true;
		}

		// If only one is empty
		if (x == null || y == null)
			return false;

		return (x != null && y != null) && (x.data == y.data) && checkIfIdenticalRecursive(x.left, y.left)
				&& checkIfIdenticalRecursive(x.right, y.right);
	}

	// https://www.geeksforgeeks.org/check-if-two-trees-are-mirror/
	public static boolean checkMirrorRecursive(Node a, Node b) {
		/* Base case : Both empty */
		if (a == null && b == null)
			return true;

		// If only one is empty
		if (a == null || b == null)
			return false;

		/*
		 * Both non-empty, compare them recursively Note that in recursive calls, we
		 * pass left of one tree and right of other tree
		 */
		return (a != null && b != null) && (a.data == b.data) && checkMirrorRecursive(a.left, b.right)
				&& checkMirrorRecursive(a.right, b.left);
	}

	// https://www.geeksforgeeks.org/iterative-function-check-two-trees-identical/
	// Iterative method to check if trees are identical
	static boolean areIdentical(Node root1, Node root2) {

		// Return true if both trees are empty
		if (root1 == null && root2 == null)
			return true;

		// Return false if one is empty and other is not
		if (root1 == null || root2 == null)
			return false;

		// Create an empty queues for simultaneous traversals
		Queue<Node> q1 = new LinkedList<Node>();
		Queue<Node> q2 = new LinkedList<Node>();

		// Enqueue Roots of trees in respective queues
		q1.add(root1);
		q2.add(root2);

		while (!q1.isEmpty() && !q2.isEmpty()) {
			// Get front nodes and compare them
			Node n1 = q1.poll();
			Node n2 = q2.poll();

			// pop & compare node data
			if (n1.data != n2.data)
				return false;

			/* Enqueue left children of both nodes */
			if (n1.left != null && n2.left != null) {
				q1.add(n1.left);
				q2.add(n2.left);
			}

			// If one left child is empty and other is not
			else if (n1.left != null || n2.left != null)
				return false;

			// Right child code (Similar to left child code)
			if (n1.right != null && n2.right != null) {
				q1.add(n1.right);
				q2.add(n2.right);
			} else if (n1.right != null || n2.right != null)
				return false;
		}

		return true;
	}

	// https://www.geeksforgeeks.org/iterative-method-check-two-trees-mirror/
	// iterative inorder traversal of one tree and iterative reverse inorder
	// traversal of the other tree in parallel.
	// Remember In Iterative inOrder we processed each half in each iterative
	public static boolean checkMirrorIterative(Node root1, Node root2) {

		Stack<Node> st1 = new Stack<Node>();
		Stack<Node> st2 = new Stack<Node>();

		while (true) {
			// iterative inorder traversal of 1st tree and
			// reverse inorder traversal of 2nd tree
			while (root1 != null && root2 != null) {
				// if the corresponding nodes in the two traversal
				// have different data values, then they are not
				// mirrors of each other.
				if (root1.data != root2.data)
					return false;

				st1.push(root1);
				st2.push(root2);
				root1 = root1.left;
				root2 = root2.right;
			}

			// if at any point one root becomes null and
			// the other root is not null, then they are
			// not mirrors. This condition verifies that
			// structures of tree are mirrors of each other.
			if (!(root1 == null && root2 == null))
				return false;

			if (!st1.isEmpty() && !st2.isEmpty()) {
				root1 = st1.pop();
				root2 = st2.pop();

				/*
				 * we have visited the node and its left subtree. Now, it's right subtree's turn
				 */
				root1 = root1.right;

				/*
				 * we have visited the node and its right subtree. Now, it's left subtree's turn
				 */
				root2 = root2.left;
			}

			// both the trees have been completely traversed
			else
				break;
		}
		return false;

	}

	// https://www.techiedelight.com/find-ancestors-of-given-node-binary-tree/
	public static void ancestorsOfNode(Node root, Node target) {

		if (root == null) {
			return;
		}
		if (target == root) {
			System.out.println("No parent found..it is root node");
		}
		HashMap<Node, Node> parentMap = constructParentMap(root);

		boolean i = true;
		while (i) {
			Node targetParent = parentMap.get(target);
			// runs untill root node is encountered
			if (targetParent != target) {
				System.out.println(targetParent.data);
			} else {
				i = false;
			}
			target = targetParent;
		}

	}

	// https://www.geeksforgeeks.org/print-ancestors-of-a-given-binary-tree-node-without-recursion/
	// If we take a closer look at the recursive postorder traversal, we can easily
	// observe that, when recursive function is called for a node, the recursion
	// call stack contains ancestors of the node. So idea is do iterative Postorder
	// traversal and stop the traversal when we reach the desired node.

	public static void ancestorsOfNodeUsingIterativePostOrder(Node root, int key) {
		if (root == null)
			return;

		// Create a stack to hold ancestors
		Stack<Node> st = new Stack<>();

		// Traverse the complete tree in postorder way till we find the key
		// Process both halves of each node
		while (true) {
			// Traverse the left side. While traversing, push the nodes into
			// the stack so that their right subtrees can be traversed later
			while (root != null && root.data != key) {
				st.push(root); // push current node
				root = root.left; // move to next node
			}

			// If the node whose ancestors are to be printed is found,
			// then break the while loop.
			if (root != null && root.data == key)
				break;
			// Check if right sub-tree exists for the node at top
			// If not then pop that node because we don't need this
			// node any more.
			if (st.peek().right == null) {
				root = st.peek();
				st.pop();

				// If the popped node is right child of top, then remove the top
				// as well. Left child of the top must have processed before.
				while (st.empty() == false && st.peek().right == root) {
					root = st.peek();
					st.pop();
				}
			}
			// if stack is not empty then simply set the root as right child
			// of top and start traversing right sub-tree.
			root = st.empty() ? null : st.peek().right;
		}

		// If stack is not empty, print contents of stack
		// Here assumption is that the key is there in tree
		while (!st.empty()) {
			System.out.print(st.peek().data + " ");
			st.pop();
		}

	}

	// https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-tree-set-2-using-parent-pointer/
	public static Node LCA(Node root, Node n1, Node n2) {

		// Create a map to store ancestors of n1
		Map<Node, Boolean> ancestors = new HashMap<Node, Boolean>();

		// Insert n1 and all its ancestors in map
		while (n1 != null) {
			ancestors.put(n1, Boolean.TRUE);
			n1 = n1.parent;
		}

		// Check if n2 or any of its ancestors is in
		// map.
		while (n2 != null) {
			if (ancestors.containsKey(n2) != ancestors.isEmpty())
				return n2;
			n2 = n2.parent;
		}

		return null;
	}

	/*
	 * A utility function to insert a new node with given key in Binary Search Tree
	 */
	Node insert(Node node, int key) {
		/* If the tree is empty, return a new node */
		if (node == null)
			return new Node(key);

		/* Otherwise, recur down the tree */
		if (key < node.data) {
			node.left = insert(node.left, key);
			node.left.parent = node;
		} else if (key > node.data) {
			node.right = insert(node.right, key);
			node.right.parent = node;
		}

		/* return the (unchanged) node pointer */
		return node;
	}

	// We do not add the root element to the queue.Just the children of root are
	// inserted.
	// https://www.geeksforgeeks.org/check-symmetric-binary-tree-iterative-approach/
	public static boolean checkIfSymmetric(Node root) {
		/* This allows adding null elements to the queue */
		Queue<Node> q = new LinkedList<Node>();

		/* Initially, add left and right nodes of root */
		q.add(root.left);
		q.add(root.right);

		while (!q.isEmpty()) {
			/*
			 * remove the front 2 nodes to check for equality
			 */
			Node tempLeft = q.remove();
			Node tempRight = q.remove();

			/*
			 * if both are null, continue and chcek for further elements
			 */
			if (tempLeft == null && tempRight == null)
				continue;

			/* if only one is null---inequality, retun false */
			if ((tempLeft == null && tempRight != null) || (tempLeft != null && tempRight == null))
				return false;

			/*
			 * if both left and right nodes exist, but have different values-- inequality,
			 * return false
			 */
			if (tempLeft.data != tempRight.data)
				return false;

			/*
			 * Note the order of insertion of elements to the queue : 1) left child of left
			 * subtree 2) right child of right subtree 3) right child of left subtree 4)
			 * left child of right subtree
			 */
			q.add(tempLeft.left);
			q.add(tempRight.right);
			q.add(tempLeft.right);
			q.add(tempRight.left);
		}

		/* if the flow reaches here, return true */
		return true;
	}

	// https://algorithms.tutorialhorizon.com/construct-binary-search-tree-from-a-given-preorder-traversal-using-stack-without-recursion/
	public static Node reconstructBSTFromPreOrder(int[] preorder) {
		Stack<Node> s = new Stack<Node>();
		Node root = new Node(preorder[0]);// first node in preOrder is always root
		s.add(root);
		for (int i = 1; i < preorder.length; i++) {
			Node x = null;
			// if element is greater than root that is already there in stack,pop elements
			// from stack & push it as right child of last popped element,else push it as
			// left child of top element in stack

			while (!s.isEmpty() && preorder[i] > s.peek().data) {
				x = s.pop();
			}
			if (x != null) {
				x.right = new Node(preorder[i]);
				s.push(x.right);
			} else {
				s.peek().left = new Node(preorder[i]);
				s.push(s.peek().left);
			}
		}
		return root;
	}

	// https://www.youtube.com/watch?v=LU4fGD-fgJQ
	// https://www.youtube.com/watch?v=TWDigbwxuB4
	public static int checkIfHeightBalanced(Node currentNode) {

		if (currentNode == null) {
			return 0;
		}

		int leftSubtreeHeight = checkIfHeightBalanced(currentNode.left);
		if (leftSubtreeHeight == -1)
			return -1;

		int rightSubtreeHeight = checkIfHeightBalanced(currentNode.right);
		if (rightSubtreeHeight == -1)
			return -1;

		if (Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 1) {
			return -1;
		}

		return (Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1);
	}

	// https://www.youtube.com/watch?v=nPtARJ2cYrg
	// A tree is a directed acyclic connected graph,hence BFS-queue,but we need to
	// convert it
	// into an undirected graph to keep a track of the parent also.SO v need a
	// hashtable

	public static void nodesAtDistancek(Node root, Node startNode, int k) {

		if (startNode == null) {
			return;
		}

		HashMap<Node, Node> parentMap = constructParentMap(root);
//		parentMap.forEach((key, value) -> System.out.println("Parent of:::" + key.data + "is::" + value.data));

		int currentLayer = 0;
		Queue<Node> q = new LinkedList<>();
		// maintain visited nodes similar to BFS in graph/Maze
		HashMap<Node, Boolean> hm = new HashMap<>();
		q.add(startNode);
		q.add(null);
		hm.put(startNode, true);

		while (!q.isEmpty()) {

			if (currentLayer == k) {
				while (!q.isEmpty()) {
					if (q.peek() != null) {
						System.out.println(q.poll().data);
					} else {
						q.remove();
					}

				}
				return;

			}

			Node temp = q.poll();

			if (temp == null && !q.isEmpty()) {

				q.add(null);

				currentLayer = currentLayer + 1;
			} else {
				// if left child isnt null & unvisited
				if (temp.left != null && !hm.containsKey(temp.left)) {
					hm.put(temp.left, true);
					q.add(temp.left);

				}
				// if right child isnt null & unvisited
				if (temp.right != null && !hm.containsKey(temp.right)) {
					hm.put(temp.right, true);
					q.add(temp.right);

				}

				// add parent of current node to queue & mark it as visited
				if (!hm.containsKey(parentMap.get(temp))) {
					hm.put(parentMap.get(temp), true);
					q.add(parentMap.get(temp));

				}

			}

		}

	}

	// using level order traversal
	private static HashMap<Node, Node> constructParentMap(Node root) {
		HashMap<Node, Node> parentMap = new HashMap<>();
		if (root == null) {
			return null;
		}
		parentMap.put(root, root);
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		q.add(null);

		while (!q.isEmpty()) {
			Node temp = q.poll();

			if (temp == null && !q.isEmpty()) {

				q.add(null);

			} else {
				if (temp.left != null) {
					q.add(temp.left);
					parentMap.put(temp.left, temp);
				}
				if (temp.right != null) {
					q.add(temp.right);
					parentMap.put(temp.right, temp);
				}
			}
		}

		parentMap.forEach((key, value) -> System.out.println("Parent of:::" + key.data + "is::" + value));
		return parentMap;
	}

	// https://www.techiedelight.com/build-binary-tree-given-parent-array/
	// Function to build the tree from given parent array
	public static Node createTree(int[] parent) {
		// create an empty map
		Map<Integer, Node> map = new HashMap<>();

		// create n new tree nodes each having value from 0 to n-1
		// and store them in a map
		for (int i = 0; i < parent.length; i++) {
			map.put(i, new Node(i));
		}

		// represents root node of binary tree
		Node root = null;

		// traverse the parent array and build the tree
		for (int i = 0; i < parent.length; i++) {
			// if parent is -1, set root to current node having
			// value i (stored in map[i])
			if (parent[i] == -1) {
				root = map.get(i);
			} else {
				// get parent node for current node
				Node ptr = map.get(parent[i]);

				// if parent's left child is filled, map the node to its right child
				if (ptr.left != null) {
					ptr.right = map.get(i);
				}
				// if parent's left child is empty, map the node to it
				else {
					ptr.left = map.get(i);
				}
			}
		}

		// return root of the constructed tree
		return root;
	}

	// https://www.techiedelight.com/determine-given-binary-tree-is-a-bst-or-not/
	// using inorder traversal and tracking last variable to check if its in sorted
	// order
	public boolean isBST(Node root, Node prev) {

		// base case: empty tree is a BST
		if (root == null) {
			return true;
		}

		// check if left subtree is BST or not
		boolean left = isBST(root.left, prev);

		// value of current node should be more than that of previous node
		if (root.data <= prev.data) {
			return false;
		}

		// update previous node data and check if right subtree is BST or not
		prev.data = root.data;

		return left && isBST(root.right, prev);
	}

	// https://www.techiedelight.com/find-maximum-sum-root-to-leaf-path-binary-tree/
	// Use post order traversal(since it is a bottom Up traversal) & maintain
	// maxSumSoFar
	// Function to print maximum sum root-to-leaf path in a given binary tree
	public static void findMaxSumPath(Node root) {
		int sum = rootToLeafSum(root);
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

	// Function to calculate maximum root-to-leaf sum in a binary tree
	public static int rootToLeafSum(Node root) {
		// base case: tree is empty
		if (root == null) {
			return 0;
		}

		// calculate maximum node-to-leaf sum for left child
		int left = rootToLeafSum(root.left);

		// calculate maximum node-to-leaf sum for right child
		int right = rootToLeafSum(root.right);

		// consider maximum sum child
		return (left > right ? left : right) + root.data;
	}

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

	// https://www.techiedelight.com/place-convert-given-binary-tree-to-doubly-linked-list/
	// do an inorder traversal and for every node insert it at front of LL and then
	// finally reverse the linked list
	public static Node convertBT_DLL(Node root, Node head) {
		// base case: tree is empty
		if (root == null) {
			return head;
		}

		// recursively convert left subtree first
		head = convertBT_DLL(root.left, head);

		// store right child
		Node right = root.right;

		// insert current node in the beginning of DLL
		root.right = head;
		if (head != null) {
			head.left = root;
		}

		head = root;

		// recursively convert right subtree
		return convertBT_DLL(right, head);
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

	// https://www.techiedelight.com/check-given-binary-tree-sum-tree-not/
	// use post order traversal(bottom up) and check if subtrees sum is the value of
	// root
	// Recursive function to check if given binary tree is a sum tree or not

	// top down traversal wudnt work if we check if value at a node is equal to sum
	// of its children , in a sum tree node value is sum of left & right subtrees
	// and not just its children
	// 1) If the node is a leaf node then sum of subtree rooted with this node is
	// equal to value of this node.
	// 2) If the node is not a leaf node then sum of subtree rooted with this node
	// is twice the value of this node (Assuming that the tree rooted with this node
	// is SumTree).
	public static int isSumTree(Node root) {
		// base case: empty tree
		if (root == null) {
			return 0;
		}

		// special case: leaf node
		if (root.left == null && root.right == null) {
			return root.data;
		}

		// if root's value is equal to sum of all elements present in its
		// left and right subtree
		if (root.data == isSumTree(root.left) + isSumTree(root.right)) {
			return 2 * root.data;
		}

		return Integer.MIN_VALUE;
	}
//
//	// https://www.techiedelight.com/inplace-convert-a-tree-sum-tree/
//	convertBT_SumTree(){
//		
//	}
//

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

	// https://www.techiedelight.com/find-triplet-with-given-sum-bst/
	// traverse BST in inorder which gives all nodes in sorted order,now use hashing
	// to find out triplet with given sum - O(n^2)

	// https://www.techiedelight.com/find-pair-with-given-sum-bst/
	// traverse BSTin inorder and use hashing to find the pair -O(n)

	// https://www.techiedelight.com/find-kth-smallest-largest-element-bst/
	// while doing an inorder traversal,maintain the count so if count ==k return
	// that
	// node

	// https://www.techiedelight.com/print-all-paths-from-root-to-leaf-nodes-binary-tree/
	// https://www.youtube.com/watch?v=zIkDfgFAg60
	// inorder + queues
//	allPathsFromRootToLeaf(){
//		
//	}

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

	// https://www.techiedelight.com/find-inorder-predecessor-given-key-bst/
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
		// if it has right subtree
		else {
			Node ancestor = root;
			Node predecessor = null;

			while (ancestor != curr) {
				// if it becomes part of right subtree
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
//
//	public static void main(String args[]) {
//
//		Node root = new Node(12);
//		root.left = new Node(3);
//		root.right = new Node(30);
//		root.left.left = new Node(9);
//		root.left.right = new Node(59);
//		root.right.left = new Node(11);
//		root.right.right = new Node(23);
//		root.left.right.right = new Node(100);
//		root.right.left.right = new Node(89);
//		root.right.right.right = new Node(36);
//
////		printVerticalOrder(root);
////		leftViewIterative(root);
////		rightViewIterative(root);
//		printNodesAtLevel(root, 3);
////		printBottomView(root);
////		height(root);
////		nodesAtDistancek(root, root.left, 2);
////		ancestorsOfNode(root, root.right.right);
////		LCA(root, root.right.right.right, root.right);
//	}

	private static Node searchNode(Node root, int data) {
		// TODO Auto-generated method stub
		return null;
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
