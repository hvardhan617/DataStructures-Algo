package dp;

//https://www.youtube.com/watch?v=3ZDZ-N0EPV0
//https://www.techiedelight.com/check-string-matches-with-wildcard-pattern/
//using tabulation
// true or false - if a given string is following the given pattern.
public class WildcardPatternMatching {

	public boolean isMatch(String s, String p) {
		char[] str = s.toCharArray();
		char[] pattern = p.toCharArray();

		// use stack
		// replace multiple * with one *
		// e.g a**b***c --> a*b*c
		int writeIndex = 0;
		boolean isFirst = true;
		for (int i = 0; i < pattern.length; i++) {
			if (pattern[i] == '*') {
				if (isFirst) {
					pattern[writeIndex++] = pattern[i];
					isFirst = false;
				}
			} else {
				pattern[writeIndex++] = pattern[i];
				isFirst = true;
			}
		}

		boolean T[][] = new boolean[str.length + 1][writeIndex + 1];

		if (writeIndex > 0 && pattern[0] == '*') {
			T[0][1] = true;
		}

		T[0][0] = true;

		for (int i = 1; i < T.length; i++) {
			for (int j = 1; j < T[0].length; j++) {
				// if character match or ?,copy diagonal element
				if (pattern[j - 1] == '?' || str[i - 1] == pattern[j - 1]) {
					T[i][j] = T[i - 1][j - 1];
				}
				// top(if * included) or left(if * isnt included) if true
				else if (pattern[j - 1] == '*') {
					T[i][j] = T[i - 1][j] || T[i][j - 1];
				}
			}
		}

		return T[str.length][writeIndex];
	}

	public static void main(String args[]) {
		WildcardPatternMatching wcm = new WildcardPatternMatching();
		System.out.println(wcm.isMatch("xbylmz", "x?y*z"));
	}
}
