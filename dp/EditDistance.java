package dp;

//https://www.youtube.com/watch?v=We3YDTzNXEk&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=6
//https://www.youtube.com/watch?v=MiqoA-yF-0M
//Levenstein distance(diagonal - replace,left- delete,up-insert)
public class EditDistance {

	/**
	 * Uses bottom up DP to find the edit distance
	 */
	public int dynamicEditDistance(char[] str1, char[] str2) {
		int temp[][] = new int[str1.length + 1][str2.length + 1];

		for (int i = 0; i < temp[0].length; i++) {
			temp[0][i] = i;
		}

		for (int i = 0; i < temp.length; i++) {
			temp[i][0] = i;
		}

		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				if (str1[i - 1] == str2[j - 1]) {
					// copy diagonal element if its a match,coz we wudnt need that as part of edit
					// distance,we cant add 1
					temp[i][j] = temp[i - 1][j - 1];
				} else {

					// min of delete(left),insert(up) & replace(diagonal) + 1
					temp[i][j] = 1 + min(temp[i - 1][j - 1], temp[i - 1][j], temp[i][j - 1]);
				}
			}
		}
		printActualEdits(temp, str1, str2);
		return temp[str1.length][str2.length];

	}

	/**
	 * Prints the actual edits which needs to be done.
	 */
	public void printActualEdits(int T[][], char[] str1, char[] str2) {
		int i = T.length - 1;
		int j = T[0].length - 1;
		while (true) {
			if (i == 0 || j == 0) {
				break;
			}
			if (str1[i - 1] == str2[j - 1]) {
				i = i - 1;
				j = j - 1;
			} else if (T[i][j] == T[i - 1][j - 1] + 1) {
				System.out.println("Edit " + str2[j - 1] + " in string2 to " + str1[i - 1] + " in string1");
				i = i - 1;
				j = j - 1;
			} else if (T[i][j] == T[i - 1][j] + 1) {
				System.out.println("Delete in string1 " + str1[i - 1]);
				i = i - 1;
			} else if (T[i][j] == T[i][j - 1] + 1) {
				System.out.println("Delete in string2 " + str2[j - 1]);
				j = j - 1;
			} else {
				throw new IllegalArgumentException("Some wrong with given data");
			}

		}
	}

	private int min(int a, int b, int c) {
		int l = Math.min(a, b);
		return Math.min(l, c);
	}

	public static void main(String args[]) {
		String str1 = "azced";
		String str2 = "abcdef";
		EditDistance editDistance = new EditDistance();
		int result = editDistance.dynamicEditDistance(str1.toCharArray(), str2.toCharArray());
		System.out.print(result);
	}
}
