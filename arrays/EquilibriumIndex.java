package arrays;

//https://www.techiedelight.com/find-equilibrium-index-array/
public class EquilibriumIndex {

	// Function to find equilibrium index of an array
	public static void equilibriumIndex(int A[]) {
		// left[i] stores sum of elements of sub-array A[0..i-1]
		int left[] = new int[A.length];

		left[0] = 0;

		// traverse array from left to right
		for (int i = 1; i < A.length; i++) {
			left[i] = left[i - 1] + A[i - 1];
		}

		// right stores sum of elements of sub-array A[i+1..n)
		int right = 0;

		// traverse array from right to left
		for (int i = A.length - 1; i >= 0; i--) {
			/*
			 * if sum of elements of sub-array A[0..i-1] is equal to the sum of elements of
			 * sub-array A[i+1..n) i.e. (A[0] + .. + A[i-1]) = (A[i+1] + A[i+2] + .. +
			 * A[n-1])
			 */

			if (left[i] == right) {
				System.out.println("Equilibrium Index found at " + i);
			}

			// new right = A[i] + (A[i+1] + A[i+2] + .. + A[n-1])
			right += A[i];
		}
	}

	// Program to find the equilibrium index of an array
	public static void main(String[] args) {
		int[] A = { 0, -3, 5, -4, -2, 3, 1, 0 };

		equilibriumIndex(A);
	}

}
