package stack;

//implementing stack using LL
public class StackUsingLL {

	private static class Node {
		Node next;
		int data;

		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	private static class Stack {
		private Node top;

		Stack() {
			this.top = null;
		}

		// push==insert at front of the list
		public void push(int data) {

			// Allocate new node in heap
			Node newNode = new Node(data);
			// check if heap is full
			if (newNode == null) {
				System.out.println("Heap overflow");
				return;
			} else {
				newNode.next = top;
				top = newNode;
			}

		}

		// remove Node from head/top of list
		public int pop() {
			if (top == null) {
				System.out.println("Stack is empty");
				return -1;
			} else {
				int poppedElement = top.data;
				top = top.next;
				return poppedElement;
			}
		}

		public boolean isEmpty() {
			return top == null;
		}

		public int peek() {
			if (!isEmpty()) {
				return top.data;
			} else {
				System.out.println("Stack empty");
				return -1;
			}
		}
	}

	public static void main(String args[]) {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);

		System.out.println(stack.peek());

		System.out.println(stack.pop());

	}

}
