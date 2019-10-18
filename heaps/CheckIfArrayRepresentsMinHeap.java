package heaps;

//https://www.techiedelight.com/check-given-array-represents-min-heap-not/
//how to find out if an element in an array is an internal node or leaf node by index value.
// internal node if(2*i +2 <= n) must fall within the array length
//leaf node if(2*i +2)>n since it falls outside the size of the array
//https://www.geeksforgeeks.org/how-to-check-if-a-given-array-represents-a-binary-heap/
//check if node value is less than their children value for all internal nodes
public class CheckIfArrayRepresentsMinHeap {

	// Iterative function to check if given array represents
	// Min-Heap or not
	public static boolean checkMinHeap(int[] A) {
		// Start from root and go till the last internal
		// node
		for (int i = 0; i <= (A.length - 2) / 2; i++) {
			// If left child is greater, return false
			if (A[2 * i + 1] > A[i]) {
				return false;
			}

			// If right child is greater, return false
			// check if the index is valid & then access right child index
			if (2 * i + 2 < A.length && A[2 * i + 2] > A[i]) {
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
