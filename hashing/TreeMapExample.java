package hashing;

import java.util.ArrayList;
import java.util.Collections;
//https://www.javainterviewpoint.com/how-to-sort-hashmap-in-java-by-keys/
//https://www.geeksforgeeks.org/sorting-hashmap-according-key-value-java/
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {
	public static void main(String[] args) {

		Map<Integer, String> unsortedMap = new HashMap<Integer, String>();
		unsortedMap.put(5, "asd");
		unsortedMap.put(1, "cfd");
		unsortedMap.put(7, "gdf");
		unsortedMap.put(55, "qwe");
		unsortedMap.put(66, "weq");
		unsortedMap.put(3, "wer");
		unsortedMap.put(8, "yes");
		unsortedMap.put(93, "nsa");
		unsortedMap.put(50, "tes");
		unsortedMap.put(12, "mds");
		unsortedMap.put(43, "fsa");

		// Print the Elements of the Map before Sorting
		System.out.println("Elements of the HashMap before Sorting");
		printMap(unsortedMap);

		// Create a Treemap of unsortedMap to get it sorted
		Map<Integer, String> sortedMap = new TreeMap<Integer, String>(unsortedMap);

		// Print the Elements of the Map after Sorting
		System.out.println("Elements of the HashMap after Sorting");

		printMap(sortedMap);

	}

	public static void printMap(Map<Integer, String> map) {
		System.out.println("**************************************");
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}
		System.out.println();
	}

//Using TreeMap constructor
	// Function to sort map by Key
	public static void sortbykey(HashMap unsortedMap) {
		// TreeMap to store values of HashMap
		TreeMap<String, Integer> sorted = new TreeMap<>(unsortedMap);

		// Display the TreeMap which is naturally sorted
		for (Map.Entry<String, Integer> entry : sorted.entrySet())
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
	}

	// Using collections.sort() method
	// Function to sort map by Key
	public static void sortbykey2(HashMap unsortedMap) {
		ArrayList<String> sortedKeys = new ArrayList<String>(unsortedMap.keySet());

		Collections.sort(sortedKeys);

		// Display the TreeMap which is naturally sorted
		for (String x : sortedKeys)
			System.out.println("Key = " + x + ", Value = " + unsortedMap.get(x));
	}
}
