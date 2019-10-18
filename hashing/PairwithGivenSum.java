package hashing;

import java.util.HashMap;

//O(n)
public class PairwithGivenSum {

	public static void main(String args[]) {
		int a[] = { 2, 3, 7, 11 };
		int b[] = { 20, 0, 0, 11, 7, 9, 0, 22, 36, 0 };
		int target = 9;
		pairWithGivenSum(a, target);

	}

	// https://www.techiedelight.com/find-pair-with-given-sum-array/
	private static void pairWithGivenSum(int[] a, int target) {
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			int diff = target - a[i];
			if (hm.containsKey(diff)) {
				System.out.println("Pair:::::" + hm.get(diff) + diff);
			} else {
				hm.put(a[i], target - a[i]);
			}

		}
	}

}
