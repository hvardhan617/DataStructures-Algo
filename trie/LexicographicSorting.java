package trie;

import java.util.Arrays;
import java.util.List;

//https://www.techiedelight.com/lexicographic-sorting-given-set-of-keys/
//A class representing a Trie node
class Trie {
	String key; // non-empty when node is a leaf node
	Trie[] character = null;

	// Constructor
	Trie() {
		// Trie supports lowercase English characters (a - z)
		// so character size is 26
		character = new Trie[26];
	}
};

//To print the strings in alphabetical order we have to first insert them in the trie
//and then perform preorder traversal to print in alphabetical order.
//The nodes of trie contain an index[] array which stores the index position of all the strings of arr[] ending at that node. Except for trie’s leaf node all the other nodes have size 0 for the index[] array.
// Worst case occurs when every string is starting with a different character. In that case, it will visit all the nodes of each character of each string. 
//So worst-case time complexity will be the sum of the length of every string i.e. O(|S1| + |S2| + |S3| + … + |Sn|) where |S| is the length of the string.
public class LexicographicSorting {

	// Iterative function to insert a String in Trie
	public static void insert(Trie head, String str) {
		// start from root node
		Trie curr = head;

		for (int i = 0; i < str.length(); i++) {
			// create a new node if path doesn't exists
			if (curr.character[str.charAt(i) - 'a'] == null) {
				curr.character[str.charAt(i) - 'a'] = new Trie();
			}

		
			// go to next node
			curr = curr.character[str.charAt(i) - 'a'];
		}

		// store key in leaf node
		curr.key = str;
	}

	// Function to perform pre-order traversal of given Trie
	public static void preorder(Trie curr) {
		// return false if Trie is empty
		if (curr == null) {
			return;
		}

		for (int i = 0; i < 26; i++) {
			if (curr.character[i] != null) {
				// if leaf node, print key
				if (curr.character[i].key != null) {
					System.out.println(curr.character[i].key);
				}
				preorder(curr.character[i]);
			}
		}
	}

	public static void main(String[] args) {
		// given set of keys
		List<String> dict = Arrays.asList("lexicographic", "sorting", "of", "a", "set", "of", "keys", "can", "be",
				"accomplished", "with", "a", "simple", "trie", "based", "algorithm", "we", "insert", "all", "keys",
				"in", "a", "trie", "output", "all", "keys", "in", "the", "trie", "by", "means", "of", "preorder",
				"traversal", "which", "results", "in", "output", "that", "is", "in", "lexicographically", "increasing",
				"order", "preorder", "traversal", "is", "a", "kind", "of", "depth", "first", "traversal");

		Trie head = new Trie();

		// insert all keys of dictionary into trie
		for (String word : dict) {
			insert(head, word);
		}

		// print keys in lexicographic order
		preorder(head);
	}

	public static void lexialorder(String[] args) {
		String[] words = { "Ruby", "C", "Python", "Java" };
		for (int i = 0; i < 3; ++i) {
			for (int j = i + 1; j < 4; ++j) {
				if (words[i].compareTo(words[j]) > 0) {
					// swap words[i] with words[j[
					String temp = words[i];
					words[i] = words[j];
					words[j] = temp;
				}
			}
		}
		System.out.println("In lexicographical order:");
		for (int i = 0; i < 4; i++) {
			System.out.println(words[i]);
		}
	}
}
