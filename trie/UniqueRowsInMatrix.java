package trie;

import java.util.HashSet;

//https://www.geeksforgeeks.org/print-unique-rows/
//Time complexity: O( ROW x COL )
//Auxiliary Space: O(ROW)
public class UniqueRowsInMatrix {

	// Method 4 (Use HashSet data structure)
	// In this method convert the whole row into a single String and
	// then check it is already present in HashSet or not.
	// If row is present then we will leave it otherwise we will print unique row
	// and add it to HashSet.
	public static void printArray(int arr[][], int row, int col) {

		HashSet<String> set = new HashSet<String>();

		for (int i = 0; i < row; i++) {
			String s = "";

			for (int j = 0; j < col; j++)
				s += String.valueOf(arr[i][j]);

			if (!set.contains(s)) {
				set.add(s);
				System.out.println(s);

			}
		}
	}

// Driver code 
	public static void main(String[] args) {

		int arr[][] = { { 0, 1, 0, 0, 1 }, { 1, 0, 1, 1, 0 }, { 0, 1, 0, 0, 1 }, { 1, 1, 1, 0, 0 } };

		printArray(arr, 4, 5);
	}

}
