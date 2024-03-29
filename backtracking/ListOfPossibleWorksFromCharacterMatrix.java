package backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://www.techiedelight.com/generate-list-of-possible-words-from-a-character-matrix/
//Solve it using DFS recursive ( backtracking)
//Since TC is exponential we can use a trie for this.
public class ListOfPossibleWorksFromCharacterMatrix {

	// M x N matrix
	private static int M, N;

	// Below arrays details all 8 possible movements from a cell
	// (top, right, bottom, left and 4 diagonal moves)
	public static int[] row = { -1, -1, -1, 0, 1, 0, 1, 1 };
	public static int[] col = { -1, 1, 0, -1, -1, 1, 0, 1 };

	// Function to check if it is safe to go to cell (x, y) from current cell.
	// The function returns false if (x, y) is not valid matrix coordinates
	// or cell (x, y) is already processed
	public static boolean isSafe(int x, int y, boolean[][] processed) {
		return (x >= 0 && x < M) && (y >= 0 && y < N) && !processed[x][y];
	}

	// A recursive function to generate all possible words in a boggle
	public static void searchBoggle(char[][] board, Set<String> words, boolean[][] processed, int i, int j,
			String path) {
		// mark current node as processed
		processed[i][j] = true;

		// update the path with the current character and insert it into the set
		path = path + board[i][j];
		words.add(path);

		// check for all 8 possible movements from the current cell
		for (int k = 0; k < 8; k++) {
			// skip if cell is invalid or it is already processed
			if (isSafe(i + row[k], j + col[k], processed)) {
				searchBoggle(board, words, processed, i + row[k], j + col[k], path);
			}
		}

		// mark current node as unprocessed
		processed[i][j] = false;
	}

	// Function to search for given set of words in a boggle
	public static void searchBoggle(char[][] board, List<String> input) {
		// construct a boolean matrix to store whether a cell is processed or not
		boolean[][] processed = new boolean[M][N];

		// construct a set to store all possible words constructed from the matrix
		Set<String> words = new HashSet<>();

		// generate all possible words in a boggle
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				// consider each character as a starting point and run DFS
				searchBoggle(board, words, processed, i, j, "");
			}
		}

		// for each word in the input list, check whether it is present in the set
		for (String word : input) {
			if (words.contains(word)) {
				System.out.println(word);
			}
		}
	}

	public static void main(String[] args) {
		char[][] board = { { 'M', 'S', 'E' }, { 'R', 'A', 'T' }, { 'L', 'O', 'N' } };

		M = board.length;
		N = board[0].length;

		List<String> words = Arrays.asList("STAR", "NOTE", "SAND", "STONE");
		searchBoggle(board, words);
	}
}
