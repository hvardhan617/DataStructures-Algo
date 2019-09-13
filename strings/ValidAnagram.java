package strings;

import java.util.Arrays;

//https://www.youtube.com/watch?v=oHwDqfz1LGw&list=PLi9RQVmJD2fZGdIX-y3-X37YUAWxh6GqH&index=2
// an anagram is just rearranging characters in a string n forming another string
// We cud use a hashmap of freq count of characters
//here we used hashing in an array
public class ValidAnagram {

	public static void main(String args[]) {
		String s1 = "anagram";
		String s2 = "nagaram";

		System.out.println(isValidAnagram(s1, s2));

	}

	private static boolean isValidAnagram(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		} else {
			// array of all possible characters to store freq count of that character
			// if at the end of iteration, if the array contains all zeros,it is valid
			// anagram
			int counts[] = new int[26];
			for (int i = 0; i < s1.length(); i++) {
				counts[s1.charAt(i) - 'a'] = counts[s1.charAt(i) - 'a'] + 1; // increment freq
				counts[s2.charAt(i) - 'a'] = counts[s2.charAt(i) - 'a'] - 1; // decrement freq
			}

			System.out.println("Counts:::" + Arrays.toString(counts));
			for (int i : counts) {
				if (i != 0) {
					return false;
				}
			}
			return true;
		}
	}

}
