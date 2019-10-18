package searching;

//https://www.techiedelight.com/search-nearly-sorted-array-ologn-time/
//https://www.youtube.com/watch?v=xWOoBJUqlbI
//O(logn)
public class SearchInANearlySortedArray {

	// Function to search an element x in nearly sorted array A
	public static int searchElement(int[] A, int x) {
		// search space is A[left..right]
		int left = 0;
		int right = A.length - 1;

		// run till search space is exhausted
		while (left <= right) {
			// find middle index mid and compare A[mid - 1], A[mid]
			// and A[mid + 1] with the key number
			int mid = (left + right) / 2;

			// return mid if middle element is equal to key number
			if (A[mid] == x) {

				return mid;
			}

			// return mid-1 if A[mid - 1] is equal to key number
			else if (mid - 1 >= left && A[mid - 1] == x) {
				return mid - 1;
			}

			// return mid+1 if A[mid + 1] is equal to key number
			else if (mid + 1 <= right && A[mid + 1] == x) {
				return mid + 1;
			}

			// if middle element is less than the key number,
			// reduce search space to right subarray A[mid+2..right]
			else if (x > A[mid]) {
				left = mid + 2;
			}

			// if middle element is greater than the key number, reduce
			// search space to right subarray A[left..mid-2]
			else {
				right = mid - 2;
			}
		}

		// invalid input or number not present in the array
		return -1;
	}

	public static void main(String[] args) {
		int[] A = { 2, 1, 3, 5, 4, 7, 6, 8, 9 };
		int key = 5;

		int index = searchElement(A, key);

		if (index != -1) {
			System.out.println("Element " + key + " found at index " + index);
		} else {
			System.out.println("Element not found in the array");
		}
	}
}
