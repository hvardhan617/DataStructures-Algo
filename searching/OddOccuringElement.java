package searching;

//https://www.techiedelight.com/find-odd-occurring-element-logn-time/
//xor other solution
public class OddOccuringElement {

	// Recursive function to find an odd occurring element in an array
	// using binary search. This function assumes the input is valid.
	public static int findOddOccuring(int[] arr, int low, int high) {
		// base case
		if (low == high) {
			return low;
		}

		// find the middle index
		int mid = (low + high) / 2;

		// if mid is odd
		if (mid % 2 == 1) {
			// if element before mid is same as mid element, the odd element
			// lies on the right side; otherwise it lies on the left side
			if (arr[mid] == arr[mid - 1])
				return findOddOccuring(arr, mid + 1, high);
			else
				return findOddOccuring(arr, low, mid - 1);
		}

		// mid is even
		else {
			// if element next to mid is same as mid element, the odd element
			// lies on the right side; otherwise it lies on the left side
			if (arr[mid] == arr[mid + 1])
				return findOddOccuring(arr, mid + 2, high);
			else
				return findOddOccuring(arr, low, mid);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 2, 2, 1, 1, 3, 3, 2, 2, 4, 4, 3, 1, 1 };

		int index = findOddOccuring(arr, 0, arr.length - 1);
		System.out.println("The odd occurring element is " + arr[index]);
	}
}
