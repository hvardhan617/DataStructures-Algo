package strings;

import java.util.Stack;

//https://www.geeksforgeeks.org/minimum-number-of-bracket-reversals-needed-to-make-an-expression-balanced/
//An Efficient Solution can solve this problem in O(n) time. 
//The idea is to first remove all balanced part of expression. 
//For example, convert “}{{}}{{{” to “}{{{” by removing highlighted part. 
//If we take a closer look, we can notice that, after removing balanced part,
//we always end up with an expression of the form }}…}{{…{, 
//an expression that contains 0 or more number of closing brackets followed by 0 or more numbers of opening brackets.
//How many minimum reversals are required for an expression of the form “}}..}{{..{” ?. 
//Let m be the total number of closing brackets and n be the number of opening brackets.
//We need ⌈m/2⌉ + ⌈n/2⌉ reversals. For example }}}}{{ requires 2+1 reversals.
public class MinReversalsToMakeExpBalanced {

	// Method count minimum reversal for
	// making an expression balanced.
	// Returns -1 if expression cannot be balanced
	static int countMinReversals(String expr) {
		int len = expr.length();

		// length of expression must be even to make
		// it balanced by using reversals.
		if (len % 2 != 0)
			return -1;

		// After this loop, stack contains unbalanced
		// part of expression, i.e., expression of the
		// form "}}..}{{..{"
		Stack<Character> s = new Stack<>();

		for (int i = 0; i < len; i++) {
			char c = expr.charAt(i);
			if (c == '}' && !s.empty()) {
				if (s.peek() == '{')
					s.pop();
				else
					s.push(c);
			} else
				s.push(c);
		}

		// Length of the reduced expression
		// red_len = (m+n)
//		int red_len = s.size();

		// count opening brackets at the end of
		// stack
//		int n = 0;
//		while (!s.empty() && s.peek() == '{') {
//			s.pop();
//			n++;
//		}
//
//		// return ceil(m/2) + ceil(n/2) which is
//		// actually equal to (m+n)/2 + n%2 when
//		// m+n is even.
//		return (red_len / 2 + n % 2);
		int m = 0, n = 0;
		while (!s.isEmpty()) {
			Character c = s.pop();
			if (c == '{') {
				m++;
			} else if (c == '}') {
				n++;
			}
		}

		return m / 2 + n / 2;
	}

	// Driver method
	public static void main(String[] args) {
		String expr = "}}{{";

		System.out.println(countMinReversals(expr));
	}

}
