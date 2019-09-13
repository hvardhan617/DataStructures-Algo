package slidingWindow;

import java.util.HashMap;

//https://www.techiedelight.com/find-maximum-length-sequence-continuous-ones-sliding-window/
public class MaxLengthSeqContinuous1s {

	// main function
	public static void main(String[] args) {
		int[] A = { 0, 0, 1, 0, 1, 1, 1, 0, 1, 1 };

		findIndexofZero(A);

	}

	private static void findIndexofZero(int[] a) {
		int start = 0, count = 0, index = -1;
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int end = 0; end < a.length; end++) {
			if (a[end] == 1) {

				if (!hm.containsKey(a[end])) {
					hm.put(a[end], 1);
					if (count < 1) {
						count = hm.get(a[end]);
						index = end;
					}
				} else {
					hm.put(a[end], hm.get(a[end]) + 1);
					if (count < hm.get(a[end])) {
						count = hm.get(a[end]);
						index = end;
					}

				}
			} else {
				hm.clear();
				start = start + 1;

			}
		}
		System.out.println(count + "::::::" + index);
	}
}
