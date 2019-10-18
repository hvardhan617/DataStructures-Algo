package trie;

import java.util.HashMap;
import java.util.Map;

//https://www.youtube.com/watch?v=AXjmTQ8LEoI
//https://www.geeksforgeeks.org/trie-insert-and-search/
//Insert and search costs O(key_length) for single key
//Insert and search costs O(key_length * number of keys), 
//however the memory requirements of Trie is O(ALPHABET_SIZE * key_length * N)
//where N is number of keys in Trie. 
//There are efficient representation of trie nodes (e.g. compressed trie, ternary search tree, etc.) to minimize memory requirements of trie.
public class TrieImpl {

	private class TrieNode {
		Map<Character, TrieNode> children;
		boolean endOfWord;

		public TrieNode() {
			children = new HashMap<>();
			endOfWord = false;
		}
	}

	private final TrieNode root;

	public TrieImpl() {
		root = new TrieNode();
	}

	/**
	 * Iterative implementation of insert into trie
	 */
	public void insert(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode node = current.children.get(ch);
			if (node == null) {
				node = new TrieNode();
				current.children.put(ch, node);
			}
			current = node;
		}
		// mark the current nodes endOfWord as true
		current.endOfWord = true;
	}

	/**
	 * Iterative implementation of search into trie.
	 */
	public boolean search(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode node = current.children.get(ch);
			// if node does not exist for given char then return false
			if (node == null) {
				return false;
			}
			current = node;
		}
		// return true of current's endOfWord is true else return false.
		return current.endOfWord;
	}

	/**
	 * Delete word from trie.
	 */
	public void delete(String word) {
		delete(root, word, 0);
	}

	/**
	 * Returns true if parent should delete the mapping
	 */
	private boolean delete(TrieNode current, String word, int index) {
		if (index == word.length()) {
			// when end of word is reached only delete if currrent.endOfWord is true.
			if (!current.endOfWord) {
				return false;
			}
			current.endOfWord = false;
			// if current has no other mapping then return true
			return current.children.size() == 0;
		}
		char ch = word.charAt(index);
		TrieNode node = current.children.get(ch);
		if (node == null) {
			return false;
		}
		boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

		// if true is returned then delete the mapping of character and trienode
		// reference from map.
		if (shouldDeleteCurrentNode) {
			current.children.remove(ch);
			// return true if no mappings are left in the map.
			return current.children.size() == 0;
		}
		return false;
	}
}
