package strings;

import java.util.HashMap;
import java.util.Map;

//https://www.techiedelight.com/find-first-non-repeating-character-string-one-traversal/

//Pair class
class Pair<U, V> {
	public U first; // first field of a Pair
	public V second; // second field of a Pair

	// Constructs a new Pair with specified values
	private Pair(U first, V second) {
		this.first = first;
		this.second = second;
	}

	// Factory method for creating a Typed Pair immutable instance
	public static <U, V> Pair<U, V> of(U a, V b) {
		// calls private constructor
		return new Pair<>(a, b);
	}
}

//store char:<count,lastOccurance> map,
//iterate the map and find character with count 1 and min Index in map
public class FirstNonRepeatingCharacterInSIngleTraversal {

	// Function to find the first non-repeating character in
	// the String by doing only one traversal of it
	public static int nonRepeatingChar(char[] chars, int n) {
		// map to store character count and the index of its
		// last occurrence in the String
		Map<Character, Pair<Integer, Integer>> map = new HashMap<>();

		for (int i = 0; i < n; i++) {
			if (!map.containsKey(chars[i])) {
				map.put(chars[i], Pair.of(0, 0));
			}

			map.get(chars[i]).first++;
			map.get(chars[i]).second = i;
		}

		// stores index of first non-repeating character
		int min_index = n;

		// traverse map and find character having count 1 and
		// having minimum index in the String
		for (Map.Entry<Character, Pair<Integer, Integer>> entry : map.entrySet()) {
			int count = entry.getValue().first;
			int firstIndex = entry.getValue().second;

			if (count == 1 && firstIndex < min_index) {
				min_index = firstIndex;
			}
		}

		return min_index;
	}

	public static void main(String[] args) {
		String str = "ABCDBAGHC";
		int n = str.length();

		int index = nonRepeatingChar(str.toCharArray(), n);
		if (index != n) {
			System.out.println("The first non-repeating character in the String is " + str.charAt(index));
		}
	}
}
