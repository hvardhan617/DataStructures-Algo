package heaps;

//https://www.techiedelight.com/check-given-array-represents-min-heap-not/
public class CheckIfArrayRepresentsMinHeap {

	// Iterative function to check if given array represents
	// Min-Heap or not
	public static boolean checkMinHeap(int[] A) {
		// check for all internal nodes that their left child and
		// right child (if present) holds min-heap property or not

		// start with index 0 (root of the heap)
		for (int i = 0; i <= (A.length - 2) / 2; i++) {
			if (A[i] > A[2 * i + 1] || ((2 * i + 2 != A.length) && A[i] > A[2 * i + 2])) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 5, 6 };

		if (checkMinHeap(A)) {
			System.out.println("Given array is a min heap");
		} else {
			System.out.println("Given array is not a min heap");
		}
	}
}
