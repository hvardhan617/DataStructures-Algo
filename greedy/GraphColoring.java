package greedy;

import java.util.Arrays;

public class GraphColoring {

	// number of colors
	private static int m = 3;
	private static int n = 4;
	private static int x[] = new int[4];
	private static int G[][] = { { 1, 1, 0, 1 }, { 1, 1, 1, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 } };

	public static void main(String args[]) {

		graphColor(0);
	}

	private static void graphColor(int k) {

		for (int c = 1; c <= m; c++) {
			if (isSafe(k, c)) {
				x[k] = c;
				if ((k + 1) < n) {
					graphColor(k + 1);
				} else {
					System.out.println(Arrays.toString(x));
					return;
				}
			}
		}

	}

	private static boolean isSafe(int k, int c) {
		for (int i = 0; i < n; i++) {
			if (G[k][i] == 1 && c == x[i]) {
				return false;
			}
		}
		return true;
	}

}
