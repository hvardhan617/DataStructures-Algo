package test;

import java.util.Arrays;
import java.util.List;

public class az {

	static void test(int numRows, int numCols, List<List<Integer>> lot) {
		int a[][] = new int[numRows][numCols];

		for (int i = 0; i < lot.size(); i++) {
			List<Integer> list_i = lot.get(i);

			for (int j = 0; j < list_i.size(); j++) {
				a[i][j] = list_i.get(j);
			}
		}

		for(int i=0;i<numRows;i++) {
			for(int j=0;j<numCols;j++) {
				System.out.println(a[i][j]);
			}
		}
		
	}

	public static void main(String args[]) {

		test(3, 3, Arrays.asList(Arrays.asList(1, 0, 0), Arrays.asList(1, 0, 0), Arrays.asList(1, 9, 1)));
	}
}
