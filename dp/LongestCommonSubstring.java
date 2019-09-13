package dp;

//substring/subarray needs to be contiguous,strategy is same as Least common subsequence
//https://www.youtube.com/watch?v=BysNXJHzCEs&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=17
//Here we only care about diagonal backtrack,hence we need not fill the entire matrix unlike longest common subseq
public class LongestCommonSubstring {

	/**
	 * Dynamic way of calculating lcs
	 */
	public int longestCommonSubstring(char str1[], char str2[]) {
		int T[][] = new int[str1.length + 1][str2.length + 1];

		int max = 0;
		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				// if match,consider diagonal + 1
				if (str1[i - 1] == str2[j - 1]) {
					T[i][j] = T[i - 1][j - 1] + 1;
					if (max < T[i][j]) {
						max = T[i][j];
					}
				}
				// else remaining matrix is all 0s.
			}
		}
		return max;
	}

	/**
	 * Recursive way of calculating lcs
	 */
	public int longestCommonSubstringRec(char str1[], char str2[], int pos1, int pos2, boolean checkEqual) {
		if (pos1 == -1 || pos2 == -1) {
			return 0;
		}
		if (checkEqual) {
			if (str1[pos1] == str2[pos2]) {
				return 1 + longestCommonSubstringRec(str1, str2, pos1 - 1, pos2 - 1, true);
			} else {
				return 0;
			}
		}
		int r1 = 0;
		if (str1[pos1] == str2[pos2]) {
			r1 = 1 + longestCommonSubstringRec(str1, str2, pos1 - 1, pos2 - 1, true);
		}
		return Math.max(r1, Math.max(longestCommonSubstringRec(str1, str2, pos1 - 1, pos2, false),
				longestCommonSubstringRec(str1, str2, pos1, pos2 - 1, false)));
	}

	public static void main(String args[]) {
		LongestCommonSubstring lcs = new LongestCommonSubstring();
		char str1[] = "abcdef".toCharArray();
		char str2[] = "zcdemf".toCharArray();
		System.out.println(lcs.longestCommonSubstring(str1, str2));
		System.out.println(lcs.longestCommonSubstringRec(str1, str2, str1.length - 1, str2.length - 1, false));
	}
}
