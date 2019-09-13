package LL;

public class DLL {

	private static class Node {

		Node next;
		Node prev;
		int data;

		Node(int data) {
			this.data = data;
			this.prev = this.next = null;
		}
	}

	public Node insertAtHead(Node head, int data) {
		if (head == null) {
			return null;
		}
		Node newNode = new Node(data);
		newNode.next = head;
		head.prev = newNode;
		head = newNode;
		return head;
	}

	public Node insertAtTail(Node head, int data) {
		if (head == null) {
			return null;
		}
		Node newNode = new Node(data);
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = newNode;
		newNode.prev = temp;
		return head;
	}

	public Node insertAtPosition(Node head, int data, int position) {
		if (head == null) {
			return null;
		}
		Node newNode = new Node(data);
		Node temp = head;
		int count = 0;
		while (temp != null) {

			count = count + 1;
			if (count == position) {

				Node prev = temp.prev;
				prev.next = newNode;
				newNode.prev = prev;
				newNode.next = temp.next;
				temp.next.prev = newNode;
			}

			temp = temp.next;

		}
		return head;
	}

	public Node deleteAtHead(Node head) {
		if (head == null) {
			return null;
		}

		Node temp = head.next;
		head.next = null;
		head = temp;

		return head;

	}

	public Node deleteAtTail(Node head) {
		if (head == null) {
			return null;
		}
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		Node prev = temp.prev;
		temp.prev = null;
		prev.next = null;
		return head;
	}

	public Node deleteAtPosition(Node head, int position) {
		if (head == null) {
			return null;
		}

		Node temp = head;
		int count = 0;
		while (temp != null) {
			count = count + 1;
			if (count == position) {
				Node prev = temp.prev;
				Node next = temp.next;
				temp.prev = temp.next = null;
				prev.next = next;
				next.prev = prev;
			}
			temp = temp.next;
		}
		return head;
	}

	public void print(Node root) {
		if (root == null)
			return;
		else {
			Node temp = root;
			while (temp != null) {
				System.out.println(temp.data);
				temp = temp.next;
			}
		}
	}

	public Node reverse(Node head) {
		if (head == null) {
			return null;
		}
		if (head.next != null) {
			Node temp = null;
			Node curr = head;
			while (curr != null) {

				temp = curr.prev;
				curr.prev = curr.next;
				curr.next = temp;
				curr = curr.prev;
			}
			if (temp != null) {
				temp = temp.prev;
				head = temp;
			}

		}
		return head;
	}

	public static void main(String args[]) {

		Node head = new Node(10);
		Node n1 = new Node(15);
		Node n2 = new Node(2);
		Node n3 = new Node(199);
		Node n4 = new Node(35);
		Node n5 = new Node(9);
		Node n6 = new Node(150);
		Node n7 = new Node(50);

		head.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;

		n7.prev = n6;
		n6.prev = n5;
		n5.prev = n4;
		n4.prev = n3;
		n3.prev = n2;
		n2.prev = n1;
		n1.prev = head;

		DLL list = new DLL();
		list.print(head);

		list.print(list.insertAtHead(head, 100));
		list.print(list.insertAtPosition(head, 999, 2));
//		Node res = list.insertAtTail(head, 1);
//		list.print(res);
//
//		list.print(list.deleteAtHead(res));
//		list.print(list.deleteAtPosition(head, 999, 2));
//		list.print(list.deleteAtTail(res));

		list.reverse(head);

	}

}
