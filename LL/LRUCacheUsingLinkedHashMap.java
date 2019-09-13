package LL;

import java.util.LinkedHashMap;
import java.util.Map;

//So far we have seen that we can store entries in the order in which they were last accessed.
//But we can also automatically remove the eldest entries if we have exceeded our maximum number.
//To do that we subclass LinkedHashMap and add a maxEntries field. 
//We also override the removeEldestEntry(Map.Entry<K, V> eldest) method from LinkedHashMap
//to return true when we have reached our maximum size.
public class LRUCacheUsingLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

	private final int maxEntries;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;

	public LRUCacheUsingLinkedHashMap(int initialCapacity, float loadFactor, int maxEntries) {
		super(initialCapacity, loadFactor, true);
		this.maxEntries = maxEntries;
	}

	public LRUCacheUsingLinkedHashMap(int initialCapacity, int maxEntries) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR, maxEntries);
	}

	public LRUCacheUsingLinkedHashMap(int maxEntries) {
		this(DEFAULT_INITIAL_CAPACITY, maxEntries);
	}

	// not very useful constructor
	public LRUCacheUsingLinkedHashMap(Map<? extends K, ? extends V> m, int maxEntries) {
		this(m.size(), maxEntries);
		putAll(m);
	}

	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > maxEntries;
	}

	public static void main(String... args) {
		Map<Integer, String> cache = new LRUCacheUsingLinkedHashMap<>(5);
		for (int i = 0; i < 10; i++) {
			cache.put(i, "hi");
		}
		// entries 0-4 have already been removed
		// entries 5-9 are ordered
		System.out.println("cache = " + cache);

		System.out.println(cache.get(7));
		// entry 7 has moved to the end
		System.out.println("cache = " + cache);

		for (int i = 10; i < 14; i++) {
			cache.put(i, "hi");
		}
		// entries 5,6,8,9 have been removed (eldest entries)
		// entry 7 is at the beginning now
		System.out.println("cache = " + cache);

		cache.put(42, "meaning of life");
		// entry 7 is gone too
		System.out.println("cache = " + cache);
	}
}
