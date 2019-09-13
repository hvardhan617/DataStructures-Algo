package heaps;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://www.techiedelight.com/first-k-non-repeating-characters-string/
public class FirstKNonRepeatingChars {

	private static class Pair {
		private int count;
		private int index;

		Pair(int count, int index) {
			this.count = count;
			this.index = index;
		}

		public int getCount() {
			return count;
		}

		public int getIndex() {
			return index;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	// Function to find the first k non-repeating character in
	// the string by doing only one traversal of it
	public static void firstKNonRepeating(String str, int k) {
		// map to store character count and the index of its
		// last occurrence in the string
		Map<Character, Pair> map = new HashMap<>();

		for (int i = 0; i < str.length(); i++) {
			if (!map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), new Pair(1, i));
			} else {
				Pair pair = map.get(str.charAt(i));
				pair.setCount(pair.getCount() + 1);
				pair.setIndex(i);
			}
		}

		// create an empty min-heap
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// traverse the map and push index of all characters
		// having count of 1 into the min-heap
		for (Map.Entry<Character, Pair> entry : map.entrySet()) {
			int count = entry.getValue().getCount();
			int index = entry.getValue().getIndex();

			if (count == 1) {
				pq.add(index);
			}
		}

		// pop top k keys from the min-heap
		while (k-- > 0 && !pq.isEmpty()) {
			// extract the minimum node from the min-heap
			int min_index = pq.poll();
			System.out.print(str.charAt(min_index) + " ");
		}
	}

	public static void main(String[] args) {
		String str = "ABCDBAGHCHFAC";
		int k = 3;

		firstKNonRepeating(str, k);
	}
}
