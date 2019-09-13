package trees;

public class BinaryTree {

	private static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	public static void main(String args[]) {

		Node root = new Node(12);
		root.left = new Node(3);
		root.right = new Node(30);
		root.left.left = new Node(9);
		root.left.right = new Node(59);
		root.right.left = new Node(11);
		root.right.right = new Node(23);
	}

}
