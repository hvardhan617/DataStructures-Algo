package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//https://www.geeksforgeeks.org/number-children-given-node-n-ary-tree/
//http://theoryofprogramming.com/2018/01/14/n-ary-tree-k-way-tree-data-structure/
public class NaryTreeNode {

	final String LABEL;
	final int N;
	private final ArrayList<NaryTreeNode> children;

	public NaryTreeNode(String LABEL, int n) {
		this.LABEL = LABEL;
		this.N = n;
		children = new ArrayList<>(n);
	}

	private boolean addChild(NaryTreeNode node) {
		if (children.size() < N) {
			return children.add(node);
		}

		return false;
	}

	public boolean addChild(String label) {
		return addChild(new NaryTreeNode(label, N));
	}

	public ArrayList<NaryTreeNode> getChildren() {
		return new ArrayList<>(children);
	}

	public NaryTreeNode getChild(int index) {
		if (index < children.size()) {
			return children.get(index);
		}

		return null;
	}

	private static void printUtil(NaryTreeNode root) {

		Queue<NaryTreeNode> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			NaryTreeNode temp = q.poll();
			System.out.println(temp.LABEL);

			for (NaryTreeNode child : temp.children)
				q.add(child);
		}

	}

	private static int noOfChildren(NaryTreeNode root, String target) {

		Queue<NaryTreeNode> q = new LinkedList<>();
		Queue<NaryTreeNode> q2 = new LinkedList<>();
		q.add(root);
		int noOfChildren = 0;

		while (!q.isEmpty()) {
			NaryTreeNode temp = q.poll();
//			System.out.println(temp.LABEL);

			if (temp.LABEL == target) {

				while (temp.children.size() > 0) {
					temp = q2.poll();

					for (NaryTreeNode child : temp.children) {
						q2.add(child);
						noOfChildren++;
					}
				}
			} else {
				for (NaryTreeNode child : temp.children)
					q.add(child);
			}
		}
		return noOfChildren;
	}

	public static void main(String[] args) {
		int n = 3;
		NaryTreeNode root = new NaryTreeNode("Matter", n);

		root.addChild("Pure");
		root.getChild(0).addChild("Elements");
		root.getChild(0).getChild(0).addChild("Metals");
		root.getChild(0).getChild(0).addChild("Metalloids");
		root.getChild(0).getChild(0).addChild("Non-metals");
		root.getChild(0).addChild("Compounds");
		root.getChild(0).getChild(1).addChild("Water");
		root.getChild(0).getChild(1).addChild("Carbon dioxide");
		root.getChild(0).getChild(1).addChild("Salt");
		root.getChild(0).getChild(1).addChild("Camphor"); // won't add
		root.addChild("Mixture");
		root.getChild(1).addChild("Homogeneous");
		root.getChild(1).getChild(0).addChild("Air");
		root.getChild(1).getChild(0).addChild("Vinegar");
		root.getChild(1).addChild("Heterogeneous");
		root.getChild(1).getChild(1).addChild("Colloids");
		root.getChild(1).getChild(1).addChild("Suspensions");

		NaryTreeNode.printUtil(root);
		System.out.println(NaryTreeNode.noOfChildren(root, "Matter"));
	}
}
