package stack;

import java.util.Stack;

//https://www.techiedelight.com/find-elements-array-greater-than-elements-right/
public class LeaderInArray {
	// Function to print all elements which are greater than all
	// elements present to its right
	public static void find(int[] arr) {
		// create an empty stack
		Stack<Integer> stk = new Stack<>();

		// do for each element
		for (int i = 0; i < arr.length; i++) {
			// pop all the elements that are less than the current element
			while (!stk.isEmpty() && stk.peek() < arr[i]) {
				stk.pop();
			}

			// push current element into the stack
			stk.push(arr[i]);
		}

		// print all elements in stack
		while (!stk.isEmpty()) {
			System.out.print(stk.pop() + " ");
		}
	}

	public static void main(String[] args) {
		int[] arr = { 10, 4, 6, 3, 5 };
		find(arr);
	}
}
