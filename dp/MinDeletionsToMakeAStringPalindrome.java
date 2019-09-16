package dp;

//https://www.techiedelight.com/find-minimum-number-deletions-convert-string-into-palindrome/
public class MinDeletionsToMakeAStringPalindrome {

	// Function to find out the minimum number of deletions required to
	// convert a given String X[i..j] into a palindrome
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
}
