package trees;

import java.util.HashMap;
import java.util.Stack;

import trees.AllPathsRootToLeaf.Node;

//https://www.geeksforgeeks.org/check-if-a-binary-tree-is-subtree-of-another-binary-tree/
public class CheckIfSubtree {

	public static boolean isSubtree(Node root, Node subtree) {

		Stack<Node> s = new Stack<>();
		s.push(root);

		Stack<Node> s2 = new Stack<>();
		// s2.push(subtree);
		HashMap<Node, Boolean> hm = new HashMap<>();
		hm.put(subtree, true);

		while (!s.isEmpty()) {
			Node temp = s.pop();

			Node temp2 = null;
			if (s2.size() > 0)
				temp2 = s2.pop();
			else {
				temp2 = subtree;
			}

			System.out.println(temp.data + "::::" + temp2.data);
			if (temp.data == temp2.data) {

				hm.put(temp2, true);
				if (temp2.right != null) {
					s2.push(temp2.right);
				}
				if (temp2.left != null) {
					s2.push(temp2.left);
				}

			} else {
				if (!hm.containsKey(temp2))
					hm.put(temp2, false);
			}

			if (temp.right != null) {
				s.push(temp.right);
			}
			if (temp.left != null) {
				s.push(temp.left);
			}

		}

		for (boolean value : hm.values()) {
			if (!value) {
				return false;
			}
		}

		return true;

	}

	public static void main(String args[]) {

		Node root1 = new Node(26);
		root1.right = new Node(3);
		root1.right.right = new Node(3);
		root1.left = new Node(10);
		root1.left.left = new Node(4);
		root1.left.left.right = new Node(30);
		root1.left.right = new Node(6);

		Node root2 = new Node(10);
		root2.right = new Node(6);
		root2.left = new Node(4);
		root2.left.right = new Node(30);

		System.out.println(isSubtree(root1, root2));
	}

}
