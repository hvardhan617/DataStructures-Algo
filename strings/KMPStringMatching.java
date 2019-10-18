package strings;

import java.util.Arrays;

//Pattern matching substring search
//how less back can i go and start comparing my string with pattern again in case of a mismatch
//https://www.youtube.com/watch?v=BXCEFAzhxGY&list=PLiQ766zSC5jN42O1DBalnkom5y2LXtnnK&index=14&t=0s
//for precomputing the prefix suffix table by traversing the pattern - O(m) & space - O(m)
//for matching with string - O(n) & O(1)
//total O(m+n) & O(m)
public class KMPStringMatching {

	void KMPSearch(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();

		// create lps[] that will hold the longest
		// suffix which is also a prefix in the pattern
		int lps[] = new int[M];
		int j = 0; // index for pat[]

		// Preprocess the pattern (calculate lps[]
		// array)
		computeLPSArray(pat, M, lps);

		int i = 0; // index for txt[]
		while (i < N) {
			if (pat.charAt(j) == txt.charAt(i)) {
				j++;
				i++;
			}
			if (j == M) {
				System.out.println("Found pattern " + "at index " + (i - j));
				j = lps[j - 1];
			}

			// mismatch after j matches
			else if (i < N && pat.charAt(j) != txt.charAt(i)) {
				// Do not match lps[0..lps[j-1]] characters,
				// they will match anyway
				if (j != 0)
					j = lps[j - 1];
				else
					i = i + 1;
			}
		}
	}

	void computeLPSArray(String pat, int M, int lps[]) {
		// length of the previous longest prefix suffix
		int i = 0;
		int j = 1;
		lps[0] = 0; // lps[0] is always 0

		// the loop calculates lps[i] for i = 1 to M-1
		while (j < M) {
			// if character matches,increment i and update lps[j] as i (index) , then
			// increment j
			if (pat.charAt(j) == pat.charAt(i)) {
				i = i + 1;
				// put index of i at lps[j]
				lps[j] = i;

				j = j + 1;
			} else // (pat[i] != pat[j])
			{
				// This is tricky. Consider the example.
				// AAACAAAA and i = 7. The idea is similar
				// to search step.
				// i becomes value at lps[i-1]
				if (i != 0) {
					i = lps[i - 1];

					// Also, note that we do not increment
					// i here
				} else // if (len == 0) for starting characters until a match is encountered
				{
					lps[j] = i;// i=0;
					j = j + 1;
				}
			}
		}
		System.out.println(Arrays.toString(lps));
	}

	// Driver program to test above function
	public static void main(String args[]) {
		String txt = "ABABDABACDABABCABAB";
		String pat = "ABABCABAB";
		new KMPStringMatching().KMPSearch("AAACAAAA", txt);
	}

}
