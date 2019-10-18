package slidingWindow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class NaryTree {

	static class NaryTreeNode {
		char data;
		ArrayList<NaryTreeNode> children;
		NaryTreeNode parent;

		public NaryTreeNode(char data, NaryTreeNode parent) {
			this.data = data;
			this.parent = parent;
			children = new ArrayList<>();
		}

	}

	static class StackNode {
		NaryTreeNode node;
		int level;

		StackNode(NaryTreeNode newNode, int level) {
			this.node = newNode;
			this.level = level;
		}
	}

	public static void main(String args[]) {
//		Scanner s = new Scanner(System.in);
//		int testCases = s.nextInt();
//		for (int i = 0; i < testCases; i++) {
//			int length = s.nextInt();
//			s.nextLine();
//			String input = s.nextLine();
//			NaryTreeNode root = createNaryTree(input);
//			levelOrder(root);
//
//		}

		NaryTreeNode root = createNaryTree("[[]][[]]");
		levelOrder(root);

	}

	private static void levelOrder(NaryTreeNode root) {

		Queue<StackNode> q = new LinkedList<>();
		int nodes = 0;

		int maxNodesAtALevel = 0;
		int levelsWithMaxNodes = 0;

		int level = 0;

		q.add(new StackNode(root, 0));
		q.add(null);

		while (!q.isEmpty()) {

			if (maxNodesAtALevel == q.size()) {
				levelsWithMaxNodes = levelsWithMaxNodes + 1;
			}

			maxNodesAtALevel = Math.max(maxNodesAtALevel, q.size());

			StackNode temp = q.poll();

			if (temp == null && !q.isEmpty()) {

				q.add(null);
				level = level + 1;

			} else {
				int i = 0;
				while (temp.node.children.size() > 0) {
					q.add(new StackNode(temp.node.children.get(i), level));
					i = i + 1;
					nodes = nodes + 1;
				}
			}

		}

		System.out.println(nodes + " " + level + " " + maxNodesAtALevel + " " + levelsWithMaxNodes);

	}

	private static boolean addChild(NaryTreeNode node, NaryTreeNode parentNode) {

		if (parentNode != null && parentNode.children != null) {
			return parentNode.children.add(node);
		}

		else
			return false;
	}

	private static NaryTreeNode createNaryTree(String a) {

		Stack<StackNode> s = new Stack<>();
		StackNode temp = null;

		NaryTreeNode root = new NaryTreeNode('r', null);
		s.push(new StackNode(root, 0));

		for (int i = 0; i < a.length(); i++) {

			if (a.charAt(i) == '[') {

				StackNode p = s.peek();
				if (p.node != null) {
					NaryTreeNode node = new NaryTreeNode('[', p.node);
					s.push(new StackNode(node, p.level + 1));
					addChild(node, p.node);
				}

			} else if (a.charAt(i) == ']') {
				temp = s.pop();
				if (temp.node.parent != null) {
					temp.node.data = ']';
				}
			}
		}
		return root;

	}

}
