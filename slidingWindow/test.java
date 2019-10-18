package slidingWindow;

import java.util.HashSet;
import java.util.Scanner;

public class test {

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int testCases = s.nextInt();
		for (int i = 0; i < testCases; i++) {
			int length = s.nextInt();
			s.nextLine();
			String input = s.nextLine();
			System.out.println(count(input));
		}
		System.out.println(count("abedug"));
	}

	private static int count(String string) {

		char[] a = string.toCharArray();
		int win_start = 0, win_end = 0, k = 0, len = 0, count = 0;
		Boolean is_vowel = null;

		HashSet<Character> set = new HashSet<>();
		set.add('a');
		set.add('e');
		set.add('i');
		set.add('o');
		set.add('u');

		if (set.contains(a[0])) {
			is_vowel = true;
		} else {
			is_vowel = false;
		}

		for (int i = 0; i < a.length; i++) {

			if (k <= 1) {
				if (is_vowel) {
					if (set.contains(a[i])) {
						win_end = win_end + 1;
						is_vowel = false;

						if (k == 1) {
							count = count + 1;
							len = 1;

						}
						k = k + 1;
					} else {
						win_start = win_end;
						k = 1;

//						continue;
					}

				} else if (!is_vowel) {
					if (!set.contains(a[i])) {
						win_end = win_end + 1;
						is_vowel = true;

						if (k == 1) {
							count = count + 1;
							len = 1;

						}
						k = k + 1;
					} else {
						win_start = win_start + 1;

						k = 1;
//						continue;
					}
				}

			} else {
				if (is_vowel) {
					if (set.contains(a[i])) {
						len = len + 1;
						count = count + len;

						is_vowel = false;
					} else {
						len = 0;
						k = 1;
						win_start = win_end;
//						while (win_start <= win_end && set.contains(a[win_end-1])) {
//							win_start = win_start + 1;
//						}
					}

				} else if (!is_vowel) {
					if (!set.contains(a[i])) {
						len = len + 1;
						count = count + len;

						is_vowel = true;
					} else {
						len = 0;
						k = 1;
						win_start = win_end;
					}

				}
			}
		}
		return count;
	}

}
