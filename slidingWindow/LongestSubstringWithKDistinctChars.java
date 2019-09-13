package slidingWindow;

import java.util.HashMap;
import java.util.Map;

//https://www.techiedelight.com/find-longest-substring-containing-k-distinct-characters/
//dynamic window,2 pointers & visited HM.
public class LongestSubstringWithKDistinctChars {

	// main function
	public static void main(String[] args) {
		String str = "abcbdbdbbdcdabd";
		int k = 2;

		longestSubstr(str, k);

	}

	// https://gist.github.com/Schachte/87d7c0165a584f26b3ad7845f8010387
	// https://www.youtube.com/watch?v=MK-NZ4hN7rs
	private static void longestSubstr(String str, int k) {

		int windowStart = 0, maxLength = 0;
		Map<Character, Integer> charFrequencyMap = new HashMap<>();
		for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
			char rightChar = str.charAt(windowEnd);
			charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);

			while (charFrequencyMap.size() > k) {
				char leftChar = str.charAt(windowStart);
				charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
				if (charFrequencyMap.get(leftChar) == 0) {
					charFrequencyMap.remove(leftChar);
				}
				windowStart++;
			}
			maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
		}
	}
}
