package LL;

import java.util.HashMap;

//When we think about O(1) lookup , obvious data structure comes in our mind is HashMap.
//HashMap provide O(1) insertion and lookup. but HashMap does not has mechanism of tracking which
//entry has been queried recently and which not.You could consider using LinkedHashMap DS in java that retains the access order
//https://medium.com/@krishankantsinghal/my-first-blog-on-medium-583159139237
class Entry {
	int value;
	int key;
	Entry left;
	Entry right;
}

public class LRUCache {

	HashMap<Integer, Entry> hashmap;
	// dummy nodes for marking start and end of LL
	Entry start, end; // to track head & tail of LL
	int LRU_SIZE = 4; // Here i am setting 4 to test the LRU cache
						// implementation, it can make be dynamic

	public LRUCache() {
		hashmap = new HashMap<Integer, Entry>();
	}

	public int getEntry(int key) {
		if (hashmap.containsKey(key)) // Key Already Exist, just update the access order by removing & adding it to
										// the head of the LL
		{
			Entry entry = hashmap.get(key);
			removeNode(entry);
			addAtTop(entry);
			return entry.value;
		}
		return -1;
	}

	public void putEntry(int key, int value) {
		// Key Already Exist, just update the value and remove & move it to top
		if (hashmap.containsKey(key)) {
			Entry entry = hashmap.get(key);
			entry.value = value;
			removeNode(entry);
			addAtTop(entry);
		} else {
			Entry newnode = new Entry();
			newnode.left = null;
			newnode.right = null;
			newnode.value = value;
			newnode.key = key;
			if (hashmap.size() > LRU_SIZE) // We have reached maximum size so need to make room for new element.
			{
				hashmap.remove(end.key);// remove key
				removeNode(end); // remove value for that key
				addAtTop(newnode); // add new node to the top

			} else {
				addAtTop(newnode);
			}

			hashmap.put(key, newnode);
		}
	}

	// do not forget the boundary conditions
	public void addAtTop(Entry node) {
		node.right = start;
		node.left = null;
		// as it is a DLL
		if (start != null)
			start.left = node;
		start = node;
		if (end == null)
			end = start;
	}

	// find out where d node is, n accordingly link the respective left n right
	// nodes
	public void removeNode(Entry node) {

		// if mode is in middle of list,make connection from left->right
		if (node.left != null) {
			node.left.right = node.right;
		}
		// if it is first node
		else {
			start = node.right;
		}

		// if node is in middle of list,make connection from right->left
		// since it is a DLL
		if (node.right != null) {
			node.right.left = node.left;
		}
		// if it is last node
		else {
			end = node.left;
		}
	}

	public static void main(String[] args) throws java.lang.Exception {
		// your code goes here
		LRUCache lrucache = new LRUCache();
		lrucache.putEntry(1, 1);
		lrucache.putEntry(10, 15);
		lrucache.putEntry(15, 10);
		lrucache.putEntry(10, 16);
		lrucache.putEntry(12, 15);
		lrucache.putEntry(18, 10);
		lrucache.putEntry(13, 16);

		System.out.println(lrucache.getEntry(1));
		System.out.println(lrucache.getEntry(10));
		System.out.println(lrucache.getEntry(15));

	}
}
