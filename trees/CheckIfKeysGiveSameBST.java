package trees;

//https://www.techiedelight.com/check-given-keys-represents-same-bsts-not-without-building-bst/
//O(n2)
public class CheckIfKeysGiveSameBST {

	// Recursive function to check if X[0..n) and Y[0..n) represent
	// same BSTs or not
	public static boolean isSameBST(int[] X, int[] Y, int n) {
		// if no element is present in the array, return true
		if (n == 0) {
			return true;
		}

		// if 1st element differs in both array (root node key),
		// return false
		if (X[0] != Y[0]) {
			return false;
		}

		// if array contains only one key, return true
		if (n == 1) {
			return true;
		}

		// take four auxiliary arrays of size n-1 each (as maximum
		// keys in left or right subtree can be n-1)
		int[] leftX = new int[n - 1];
		int[] rightX = new int[n - 1];
		int[] leftY = new int[n - 1];
		int[] rightY = new int[n - 1];

		int k = 0, l = 0, m = 0, o = 0;

		// process remaining keys and divide them into two groups
		for (int i = 1; i < n; i++) {
			// leftX[] will contain all elements less than X[0]
			if (X[i] < X[0]) {
				leftX[k++] = X[i];
			}
			// rightX[] will contain all elements more than X[0]
			else {
				rightX[l++] = X[i];
			}

			// leftY[] will contain all elements less than Y[0]
			if (Y[i] < Y[0]) {
				leftY[m++] = Y[i];
			}

			// rightY[] will contain all elements more than Y[0]
			else {
				rightY[o++] = Y[i];
			}
		}

		// return false if size of leftX[] & leftY[] differs i.e.
		// number of nodes in left subtree of both trees differs
		if (k != m) {
			return false;
		}

		// return false if size of rightX[] & rightY[] differs i.e.
		// number of nodes in right subtree of both trees differs
		if (l != o) {
			return false;
		}

		// check left subtree and right subtree
		return isSameBST(leftX, leftY, k) && isSameBST(rightX, rightY, l);
	}

	// Check if given keys represents same BSTs or not without
	// building the BST
	public static void main(String[] args) {
		int[] X = { 15, 25, 20, 22, 30, 18, 10, 8, 9, 12, 6 };
		int[] Y = { 15, 10, 12, 8, 25, 30, 6, 20, 18, 9, 22 };

		if (X.length == Y.length && isSameBST(X, Y, X.length)) {
			System.out.println("Given keys represent same BSTs");
		} else {
			System.out.println("Given keys represent different BSTs");
		}
	}
}
