package strings;

//https://www.techiedelight.com/replace-non-overlapping-occurrences-pattern/
public class ReplaceNonOverlappingOccurances {

	// Function to compare two strings S and P and returns true if P is prefix of S
	public static boolean compare(char[] S, int k, char[] P) {
		int i = 0;
		while (i + k < S.length && i < P.length) {
			if (S[i + k] != P[i]) {
				break;
			}
			i++;
		}

		return i == P.length;
	}

	// In-place replace single or multiple occurrences of a pattern by a
	// specified character
	public static String convert(String string, String pattern, char ch) {
		char[] S = string.toCharArray();
		char[] P = pattern.toCharArray();

		int k = 0;

		// do for each character of the String
		for (int i = 0; i < S.length; i++) {
			// compare substring S[i,i+n] with pattern P
			// if match
			if (compare(S, i, P)) {
				// move ahead by the length of the pattern
				i += P.length - 1;

				// replace the substring by the specified character
				S[k++] = ch;
			} else {
				// copy current character to next available position k
				S[k++] = S[i];
			}
		}

		// terminate the resultant string
		return new String(S).substring(0, k);
	}

	// Function to compare two Strings S and P and returns true if P is prefix of S
	public static boolean compare2(char[] S, int k, char[] P) {
		int i = 0;
		while (i + k < S.length && i < P.length) {
			if (S[i + k] != P[i]) {
				break;
			}
			i++;
		}

		return i == P.length;
	}

	// In-place replace single or multiple occurrences of a pattern by a
	// specified character
	public static String convert2(String string, String pattern, char ch) {
		char[] S = string.toCharArray();
		char[] P = pattern.toCharArray();

		int k = 0;

		// do for each character of the String
		for (int i = 0; i < S.length; i++) {
			boolean found = false;

			// compare substring S[i, i+n] with pattern P
			while (compare(S, i, P)) {
				// move ahead by the length of the pattern
				i += P.length;
				found = true;
			}

			// if pattern is found at-least once
			if (found) {
				// replace all consecutive occurrences of the pattern
				// by the specified character
				S[k++] = ch;
			}

			// copy current character to next available position k
			if (i < S.length) {
				S[k++] = S[i];
			}
		}

		// terminate the resultant String
		return new String(S).substring(0, k);
	}

	public static void main(String[] args) {
		// input string, pattern and character
		String string = "ABCABCXABC";
		String pattern = "ABC";
		char ch = '@';

		String s = convert(string, pattern, ch);
		System.out.println(s);
	}
}
