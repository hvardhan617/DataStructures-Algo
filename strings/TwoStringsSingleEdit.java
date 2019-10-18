package strings;

//https://www.techiedelight.com/determine-string-transformed-into-another-string-single-edit/
//u can used edit distance DP but O(M*N)
//O(m+n) if simultaneously traverse both strings and maintain edit count.
public class TwoStringsSingleEdit {

	// Determine if the first String can be transformed into the
	// second String with a single edit operation
	public static boolean checkEditDistance(String first, String second) {
		// store length of both Strings
		int m = first.length();
		int n = second.length();

		// difference between length of both Strings is more than one
		if (Math.abs(m - n) > 1) {
			return false;
		}

		// to keep track of number of edits
		int edits = 0;

		// i and j keep track of index of current characters in the first and
		// second String respectively
		int i = 0, j = 0;

		// loop till either String runs out
		while (i < m && j < n) {
			// if current character of both Strings doesn't match
			if (first.charAt(i) != second.charAt(j)) {
				// when length of the first String is more than the length
				// of the second String, remove current character at
				// index i in the first String

				if (m > n) {
					i++;
				}

				// when length of the first String is less than the length
				// of the second String, add current character at index j
				// in the second String to the first String

				else if (m < n) {
					j++;
				}

				// when length of both Strings are same, replace character
				// present at index i in the first String by character
				// present at index j in the second String

				else {
					i++;
					j++;
				}

				// increment the number of edits
				edits++;
			}

			// if current character of both Strings matches
			else {
				i++;
				j++;
			}
		}

		// remove any extra character left in the first String
		if (i < m) {
			edits++;
		}

		// add any extra character left in the second String to the end of first String
		if (j < n) {
			edits++;
		}

		// return true if number of edits are exactly one; return false otherwise
		return (edits == 1);
	}

	public static void main(String[] args) {
		System.out.println(checkEditDistance("xyz", "xz")); // true
		System.out.println(checkEditDistance("xyz", "xyyz")); // true
		System.out.println(checkEditDistance("xyz", "xyx")); // true
		System.out.println(checkEditDistance("xyz", "xxx")); // false
	}
}
