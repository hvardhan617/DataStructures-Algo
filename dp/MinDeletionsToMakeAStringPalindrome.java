package dp;

import java.util.Map;

//https://www.techiedelight.com/find-minimum-number-deletions-convert-string-into-palindrome/
//using memoization (not tabulation)
//https://www.youtube.com/watch?v=lBRtnuxg-gU
public class MinDeletionsToMakeAStringPalindrome {

	// Function to find out the minimum number of deletions required to
	// convert a given String X[i..j] into a palindrome
	// if last and 1st char are same, no deletion required.
	// if mismatch ( 1 deletion(u can delete either of the 2 characters)s +
	// min(T[i+1,y],T[i,y-1]))
	// O(2^n)
	public static int minDeletions(String X, int i, int j) {
		// base condition
		if (i >= j) {
			return 0;
		}

		// if last character of the String is same as the first character
		if (X.charAt(i) == X.charAt(j)) {
			return minDeletions(X, i + 1, j - 1);
		}

		// else if last character of String is different to the first character

		// 1. Remove last character & recur for the remaining substring
		// 2. Remove first character & recur for the remaining substring

		// return 1 (for remove operation) + minimum of the two values

		return 1 + Math.min(minDeletions(X, i, j - 1), minDeletions(X, i + 1, j));
	}

	public static void main(String[] args) {
		String X = "ACBCDBAA";
		int n = X.length();

		System.out.print("The minimum number of deletions required are " + minDeletions(X, 0, n - 1));
	}

	// Function to find out the minimum number of deletions required to
	// convert a given String X[i..j] into a palindrome
	public static int minDeletionsDP(String X, int i, int j, Map<String, Integer> lookup) {
		// base condition
		if (i >= j) {
			return 0;
		}

		// construct a unique map key from dynamic elements of the input
		String key = i + "|" + j;

		// if sub-problem is seen for the first time, solve it and
		// store its result in a map
		if (!lookup.containsKey(key)) {
			// if last character of the String is same as the first character
			if (X.charAt(i) == X.charAt(j)) {
				lookup.put(key, minDeletionsDP(X, i + 1, j - 1, lookup));
			} else {
				// if last character of String is different to the first character

				// 1. Remove last character & recur for the remaining substring
				// 2. Remove first character & recur for the remaining substring

				// return 1 (for remove operation) + minimum of the two values

				int res = 1 + Math.min(minDeletionsDP(X, i, j - 1, lookup), minDeletionsDP(X, i + 1, j, lookup));
				lookup.put(key, res);
			}
		}

		// return the subproblem solution from the map
		return lookup.get(key);
	}
}
