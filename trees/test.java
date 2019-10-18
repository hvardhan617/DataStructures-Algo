package trees;

import java.util.HashMap;
import java.util.Scanner;

public class test {

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int testCases = s.nextInt();

//		HashMap<Character, Character> h = new HashMap<>();
//		h.put('r', 'g');
//		h.put('g', 'b');
//		h.put('b', 'r');

		for (int i = 0; i < testCases; i++) {
			int length = s.nextInt();
			s.nextLine();
			String seq = null;
			if (s.hasNextLine())
				seq = s.nextLine();

			System.out.println(countSubarrays(length, seq));
		}
	}

	private static int countSubarrays(int length, String seq) {

		char a[] = seq.toCharArray();
		int start = 0, end = 0;
		int len = 1;
		char prev = a[0];
		int count = 0;
		int z = 1;
		for (int i = 1; i < a.length; i++) {

			if (z < 3) {

				if (prev == 'r') {
					if (a[i] == 'g') {
						end = end + 1;
					}

				} else if (prev == 'g') {
					if (a[i] == 'b') {
						end = end + 1;
					}

				} else {
					if (a[i] == 'r') {
						end = end + 1;
					}
				}

				prev = a[i];
				z = z + 1;
			} else {
				if (prev == 'r') {
					if (a[i] == 'g') {
						end = end + 1;
						len = len + 1;
						count = count + len;

					} else {
						while (start <= end) {
							start = start + 1;

						}
						len = 1;
						z = 0;
					}

				} else if (prev == 'g') {
					if (a[i] == 'b') {
						end = end + 1;
						len = len + 1;
						count = count + len;

					} else {
						while (start <= end) {
							start = start + 1;
						}
						len = 1;
						z = 0;
					}

				} else {
					if (a[i] == 'r') {
						end = end + 1;
						len = len + 1;
						count = count + len;

					} else {
						while (start <= end) {
							start = start + 1;
						}
						len = 1;
						z = 0;
					}
				}
				prev = a[i];
			}
			if (z == 3) {

				if ((a[i - 2] == 'r' && a[i - 1] == 'g' && a[i] == 'b')
						|| (a[i - 2] == 'g' && a[i - 1] == 'b' && a[i] == 'r')
						|| (a[i - 2] == 'b' && a[i - 1] == 'r' && a[i] == 'g')) {
					count = 1;
					len = 1;
					prev = a[i];
					z = z + 1;
				}

			}
		}
		return count;
	}
}
