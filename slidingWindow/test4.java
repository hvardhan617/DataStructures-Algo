package slidingWindow;

import java.util.HashSet;
import java.util.Scanner;

public class test4 {

	public static void main(String args[]) {
//		Scanner s = new Scanner(System.in);
//		int testCases = s.nextInt();
//		for (int i = 0; i < testCases; i++) {
//			int length = s.nextInt();
//			s.nextLine();
//			String input = s.nextLine();
//			System.out.println(count(input));
//		}
		System.out.println(count("rgbrbbrgbgbrgrgb"));
	}

	private static int count(String string) {

		char[] a = string.toCharArray();
		int win_start = 0, win_end = 0, k = 0, len = 0, count = 0;
		Boolean red = false, green = false, blue = false;

		if (a[0] == 'r') {
			red = true;
		} else if (a[0] == 'g') {
			green = true;
		} else if (a[0] == 'b') {
			blue = true;
		}

		for (int i = 0; i < a.length; i++) {

			if (k <= 2) {
				if (red) {
					if (a[i] == 'r') {
						win_end = win_end + 1;
						green = true;
						red = false;

						if (k == 2) {
							count = count + 1;
							len = 1;

						}
						k = k + 1;
					} else {
						win_end = win_end + 1;
						win_start = win_end;
						k = 1;
						red = false;
						if (a[i] == 'r') {
							green = true;

						} else if (a[i] == 'g') {
							blue = true;
						} else if (a[i] == 'b') {
							red = true;
						}

					}

				} else if (green) {
					if (a[i] == 'g') {
						win_end = win_end + 1;
						blue = true;
						green = false;
						if (k == 2) {
							count = count + 1;
							len = 1;

						}
						k = k + 1;
					} else {
						win_end = win_end + 1;
						win_start = win_end;
						k = 1;
						green = false;
						if (a[i] == 'r') {
							green = true;
						} else if (a[i] == 'g') {
							blue = true;
						} else if (a[i] == 'b') {
							red = true;
						}

					}
				} else if (blue) {
					if (a[i] == 'b') {
						win_end = win_end + 1;
						red = true;
						blue = false;
						if (k == 2) {
							count = count + 1;
							len = 1;

						}
						k = k + 1;
					} else {
						win_end = win_end + 1;
						win_start = win_end;
						k = 1;
						blue = false;
						if (a[i] == 'r') {
							green = true;
						} else if (a[i] == 'g') {
							blue = true;
						} else if (a[i] == 'b') {
							red = true;
						}
					}
				}
			} else {
				if (red) {
					if (a[i] == 'r') {
						len = len + 1;
						count = count + len;

						green = true;
						red = false;
					} else {
						len = 0;
						k = 1;
						win_end = win_end + 1;
						win_start = win_end;
						red = false;
						if (a[i] == 'r') {
							green = true;
						} else if (a[i] == 'g') {
							blue = true;
						} else if (a[i] == 'b') {
							red = true;
						}

					}

				} else if (green) {
					if (a[i] == 'g') {
						len = len + 1;
						count = count + len;

						blue = true;
						green = false;
					} else {
						len = 0;
						k = 1;
						win_end = win_end + 1;
						win_start = win_end;
						green = false;
						if (a[i] == 'r') {
							green = true;
						} else if (a[i] == 'g') {
							blue = true;
						} else if (a[i] == 'b') {
							red = true;
						}
					}

				} else if (blue) {
					if (a[i] == 'b') {
						len = len + 1;
						count = count + len;

						red = true;
						blue = false;
					} else {
						len = 0;
						k = 1;
						win_end = win_end + 1;
						win_start = win_end;
						blue = false;
						if (a[i] == 'r') {
							green = true;
						} else if (a[i] == 'g') {
							blue = true;
						} else if (a[i] == 'b') {
							red = true;
						}
					}

				}

			}
		}
		return count;
	}

}
