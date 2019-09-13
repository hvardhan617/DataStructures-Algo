package slidingWindow;

import java.util.HashMap;

//https://www.techiedelight.com/find-longest-substring-given-string-containing-distinct-characters/
public class LongestSubstringWithDistinctCharacters {

	public static void main(String[] args) {
//		String str = "abbcdafeegh";
	//	String str = "findlongestsubstring";
		String str = "abcccccqrstuvwxyz";

		longestSubstr(str);
	}

	private static void longestSubstr(String str) {
		char[] a = str.toCharArray();
		int left = 0;
		int maxSoFar = 0;
		HashMap<Character, Integer> hm = new HashMap<>();
		for (int right = 0; right < a.length; right++) {
			if (!(hm.containsKey(a[right]))) {
				hm.put(a[right], 1);

				maxSoFar = Math.max(maxSoFar, hm.size());
			} else {

				while (a[left] != a[right]) {
					hm.put(a[left], hm.get(a[left]) - 1);
					if (hm.get(a[left]) == 0) {
						hm.remove(a[left]);

					}
					left = left + 1;

				}
				left = left + 1;
			}
		}

		System.out.println(a[left] + "::::::" + maxSoFar);
	}
}
