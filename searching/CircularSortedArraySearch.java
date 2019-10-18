package searching;

//Atleast one half is always sorted
//O(logn)
public class CircularSortedArraySearch {

	// https://www.techiedelight.com/search-element-circular-sorted-array/
	// Function to find an element in a circularly sorted array
	// https://www.youtube.com/watch?v=uufaK2uLnSI
	public static int circularArraySearch(int[] A, int x) {
		// search space is A[left..right]
		int left = 0;
		int right = A.length - 1;

		// iterate till search space contains at-least one element
		while (left <= right) {
			// find the mid value in the search space and
			// compares it with key value
			int mid = (left + right) / 2;

			// if key is found, return its index
			if (x == A[mid]) {
				return mid;
			}

			// if right half (A[mid..right]) is sorted and mid is not
			// the key element
			if (A[mid] <= A[right]) {
				// compare key with A[mid] and A[right] to know
				// if it lies in A[mid..right] or not
				if (x > A[mid] && x <= A[right]) {
					// go searching in right sorted half
					left = mid + 1;
				} else {
					right = mid - 1; // go searching left
				}
			}

			// if left half (A[left..mid]) is sorted and mid is not
			// the key element
			else {
				// compare key with A[left] and A[mid] to know
				// if it lies in A[left..mid] or not
				if (x >= A[left] && x < A[mid]) {
					// go searching in left sorted half
					right = mid - 1;
				} else {
					left = mid + 1; // go searching right
				}
			}
		}

		// key not found or invalid input
		return -1;
	}

}
