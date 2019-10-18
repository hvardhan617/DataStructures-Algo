package backtracking;

//https://www.techiedelight.com/generate-permutations-string-java-recursive-iterative/
//O(time taken to traverse the tree to print one permutation * no of permutations) = O(n*n!)
public class PrintAllPermutations {

	// Utility function to swap two characters in a character array
	private static void swap(char[] ch, int i, int j) {
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
	}

	// Recursive function to generate all permutations of a String
	private static void permutations(char[] ch, int currentIndex) {
		if (currentIndex == ch.length - 1) {
			System.out.println(String.valueOf(ch));
		}

		for (int i = currentIndex; i < ch.length; i++) {
			swap(ch, currentIndex, i); // swap initial char with itself and other characters in subsequent loops
			permutations(ch, currentIndex + 1); // fix one char by incrementing currentIndex
			swap(ch, currentIndex, i); // backtrack to get the original string
		}
	}

	// generate all permutations of a String in Java
	public static void main(String[] args) {
		String s = "ABC";
		permutations(s.toCharArray(), 0);
	}
}
