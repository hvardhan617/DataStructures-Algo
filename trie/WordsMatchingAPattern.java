package trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//https://www.techiedelight.com/find-all-words-matching-pattern-dictionary/
//O(m*n) m-max word length , n - no of words , O(n*n) space
//create trie nodes for only Upper case letters and store the words in leaf nodes
//A Trie node
class TrieNodes {
	// each node stores a map to its child nodes
	Map<Character, TrieNodes> map = new HashMap<>();

	// true when node is a leaf node
	boolean isLeaf = false;

	// collection to store complete list of words in the leaf node
	Set<String> word = new HashSet<>();
};

//insert only upper case chars with words added to a hashset at leaf nodes
public class WordsMatchingAPattern {

	// Function to insert a String in trie
	public static TrieNodes insert(TrieNodes head, String word) {
		if (head == null) {
			head = new TrieNodes();
		}

		// start from head node
		TrieNodes curr = head;
		for (char c : word.toCharArray()) {
			// insert only uppercase characters
			if (Character.isUpperCase(c)) {
				// create a new node if path doesn't exists
				if (!curr.map.containsKey(c)) {
					curr.map.put(c, new TrieNodes());
				}

				// go to next node
				curr = curr.map.get(c);
			}
		}

		// mark current node as leaf
		curr.isLeaf = true;

		// push current word into the set associated with leaf node
		(curr.word).add(word);

		return head;
	}

	// Function to print all children of given trie node
	public static void printAllWords(TrieNodes root) {
		// if current node is leaf, print all words associated with it
		if (root.isLeaf) {
			System.out.println(root.word);
		}

		// recur for all children of the root node
		for (Map.Entry<Character, TrieNodes> pair : root.map.entrySet()) {
			TrieNodes child = pair.getValue();
			if (child != null) {
				printAllWords(child);
			}
		}
	}

	// Function to print all words in the CamelCase dictionary which
	// matches with the given pattern
	public static void findAllWords(List<String> dictionary, String pattern) {
		// Trie head node
		TrieNodes head = null;

		// construct trie from the given dictionary
		for (String s : dictionary) {
			head = insert(head, s);
		}

		// search for the given pattern in trie
		TrieNodes curr = head;
		for (char c : pattern.toCharArray()) {
			// move to the child node
			curr = curr.map.get(c);

			// if the given pattern is not found (reached end of path in trie)
			if (curr == null) {
				return;
			}
		}

		// print all words matching given pattern
		printAllWords(curr);
	}

	public static void main(String[] args) {
		List<String> dictionary = null;
		dictionary = Arrays.asList("Hi", "HiTech", "HiTechCity", "Techie", "TechieDelight", "Hello", "HelloWorld",
				"HiTechLab");

		String pattern = "HT";
		findAllWords(dictionary, pattern);
	}

}
