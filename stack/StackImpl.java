package stack;

public class StackImpl {

	private static int top = -1;
	private static final int MAX = 10;
	private int a[] = new int[MAX];

	// check if full() while pushing
	public void push(int data) {
		if (!isFull()) {
			top = top + 1;
			a[top] = data;
		}

	}

	// check if empty while popping
	public int pop() {
		int poppedElement = -1;
		if (!isEmpty()) {
			poppedElement = a[top];
			top = top - 1;

		}
		return poppedElement;
	}

	public int top() {
		return a[top];
	}

	public boolean isFull() {
		if (top == MAX - 1)
			return true;
		else
			return false;
	}

	public boolean isEmpty() {
		if (top == -1)
			return true;
		else
			return false;
	}

	public static void main(String args[]) {

		StackImpl s = new StackImpl();
		s.push(23);
		s.push(15);
		s.push(3);
		s.push(22343);
		s.push(999);
		s.push(90);
		s.push(9);
		s.push(399);
		s.push(499);
		s.push(599);
		System.out.println(s.isFull());
//		System.out.println(s.pop());
//		System.out.println(s.top());

	}

}
