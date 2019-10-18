package trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//Inserting all the words in the trie takes O(MN) time and performing a walk on the trie takes O(M) time, where-

//N = Number of strings
//M = Length of the largest string
//https://www.techiedelight.com/longest-common-prefix-given-set-strings-using-trie/
//iterate and go until the node with more than one child && node is not a leaf node
//A class representing a Trie node
class TrieNode {
	boolean isLeaf = false; // set when node is a leaf node
	Map<Character, TrieNode> character = new HashMap<>();
};

public class LCP {

	// Iterative function to insert a String in TrieNode
	private static void insert(TrieNode head, String str) {
		// start from root node
		TrieNode curr = head;

		for (int i = 0; i < str.length(); i++) {
			// create a new node if path doesn't exists
			if (!curr.character.containsKey(str.charAt(i))) {
				curr.character.put(str.charAt(i), new TrieNode());
			}

			// go to next node
			curr = curr.character.get(str.charAt(i));
		}

		curr.isLeaf = true;
	}

	// Function to find Longest Common Prefix
	public static String findLCP(List<String> dict) {
		// insert all keys into trie
		TrieNode head = new TrieNode();
		for (String s : dict) {
			insert(head, s);
		}

		// traverse the trie and find Longest Common Prefix

		StringBuilder lcp = new StringBuilder();
		TrieNode curr = head;

		// do till we find a leaf node or node has more than 1 children
		while (curr != null && !curr.isLeaf && (curr.character.size() == 1)) {
			// get iterator to only child
			Iterator<Map.Entry<Character, TrieNode>> it = curr.character.entrySet().iterator();

			if (it.hasNext()) {
				Map.Entry<Character, TrieNode> entry = it.next();

				// append current char to LCP
				lcp.append(entry.getKey());

				// update curr pointer to child node
				curr = entry.getValue();
			}

		}

		return lcp.toString();
	}

	public static void main(String[] args) {
		// given set of keys
		List<String> dict = Arrays.asList("code", "coder", "coding", "codable", "codec", "codecs", "coded", "codeless",
				"codependence", "codependency", "codependent", "codependents", "codes", "codesign", "codesigned",
				"codeveloped", "codeveloper", "codex", "codify", "codiscovered", "codrive");

		System.out.println("Longest Common Prefix is " + findLCP(dict));
	}
}
