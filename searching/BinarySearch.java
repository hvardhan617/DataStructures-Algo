package searching;

public class BinarySearch {

	private int iterative(int a[], int key, int low, int high) {

		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (key == a[mid]) {
				return mid;
			} else if (key < a[mid]) {
				high = mid - 1;
			} else if (key > a[mid]) {
				low = mid + 1;
			}
		}
		return -1;

	}

	// no loops in recursive as we ourself are calling d function recursively
	private int recursive(int a[], int key, int low, int high) {

		if (low <= high) {
			int mid = low + ((high - low) / 2);

			if (key == a[mid]) {
				return mid;

			} else if (key < a[mid]) {
				return recursive(a, key, low, mid - 1);
			} else if (key > a[mid]) {
				return recursive(a, key, mid + 1, high);
			}
		}
		return -1;
	}

	// https://www.techiedelight.com/find-first-or-last-occurrence-of-a-given-number-sorted-array/
	public int findFirstOccurance(int a[], int key, int low, int high) {

		int result = -1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			// if key is found, update the result to mid index & continue searching towards
			// left(lower indices)
			if (key == a[mid]) {
				result = mid;
				high = mid - 1;

			} else if (key < a[mid]) {
				high = mid - 1;
			} else if (key > a[mid]) {
				low = mid + 1;
			}
		}
		return result;
	}

	public int findLastOccurance(int a[], int key, int low, int high) {

		int result = -1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			// if key is found, update the result & continue searching towards right(upper
			// indices)
			if (key == a[mid]) {
				result = mid;
				low = mid + 1;

			} else if (key < a[mid]) {
				high = mid - 1;
			} else if (key > a[mid]) {
				low = mid + 1;
			}
		}
		return result;
	}

	// https://www.techiedelight.com/count-occurrences-number-sorted-array-duplicates/
	public int findTotalOccurances(int a[], int key, int low, int high, boolean searchFirst) {

		int result = -1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			// if key is found, update the result & continue searching towards right
			if (key == a[mid]) {
				result = mid;

				// start searching for first occurrence towards left
				if (searchFirst) {
					high = mid - 1;
				}
				// start searching for last occurrence towards right
				else {
					low = mid + 1;
				}

			} else if (key < a[mid]) {
				high = mid - 1;
			} else if (key > a[mid]) {
				low = mid + 1;
			}
		}
		return result;
	}

	// https://www.techiedelight.com/find-smallest-missing-element-sorted-array/
	// distinct array of non negative integers only
	// O(log(n))
	// if every number is present at its index,then it is at the right position.If
	// we encounter a[mid]!=mid,then go to left,if equal then go to right
	public int smallestMissing(int a[], int low, int high) {
		if (low > high) {
			return low;
		}
		int mid = low + (high - low) / 2;
		if (a[mid] == mid) {
			return smallestMissing(a, mid + 1, high);
		} else {
			return smallestMissing(a, low, mid - 1);
		}

	}

	// https://www.techiedelight.com/find-number-1s-sorted-binary-array/
	// Function to find number of 1's in a sorted binary array
	public static int count(int A[], int left, int right) {
		// if last element of the array is 0, no ones can
		// be present in it since it is sorted
		if (A[right] == 0) {
			return 0;
		}

		// if first element of the array is 1, all its elements
		// are ones only since it is sorted
		if (A[left] == 1) {
			return (right - left + 1);
		}

		// divide array into left and right sub-array and recur
		int mid = (left + right) / 2;
		return count(A, left, mid) + count(A, mid + 1, right);
	}

	// https://leetcode.com/articles/first-bad-version/
	// first occurance
	public int firstBadVersion(int n, char[] a) {
		int left = 0;
		int right = n - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if ((a[mid] == 'G')) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return left;
	}

	// https://www.techiedelight.com/find-square-root-using-binary-search-algorithm/
	// Function to find square root of x using binary search algorithm
	// If x is not a perfect square, return floor of the square root
	// the ans must reside within given number & givenNumber/2
	public static int sqrt(int x) {
		// base case
		if (x < 2) {
			return x;
		}

		// to store floor of sqrt(x)
		int result = 0;

		// square root of x cannot be more than x/2 for x > 1
		int start = 1;
		int end = x / 2;

		while (start <= end) {
			// find mid element
			int mid = (start + end) / 2;
			long sqr = mid * mid;

			// return mid if x is a perfect square
			if (sqr == x) {
				return mid;
			}

			// if mid*mid is less than x
			else if (sqr < x) {
				// discard left search space
				start = mid + 1;

				// update result since we need floor
				result = mid;
			}

			// if mid*mid is more than x
			else {
				// discard right search space
				end = mid - 1;
			}
		}

		// return result
		return result;
	}

	// https://www.techiedelight.com/find-missing-term-sequence-ologn-time/
	// Function to find missing term in a sequence
	public static int missingTerm(int[] A) {
		// search space is A[left..right]
		int left = 0, right = A.length - 1;

		// calculate common difference between successive elements
		int diff = (A[A.length - 1] - A[0]) / A.length;

		// run till search space is exhausted
		while (left <= right) {
			// find middle index
			int mid = right - (right - left) / 2;

			// check difference of mid element with its right neighbor
			if (mid + 1 < A.length && A[mid + 1] - A[mid] != diff) {
				return A[mid + 1] - diff;
			}

			// check difference of mid element with its left neighbor
			if (mid - 1 >= 0 && A[mid] - A[mid - 1] != diff) {
				return A[mid - 1] + diff;
			}

			// if missing element lies on left sub-array, then we reduce
			// our search space to right sub-array A[left..mid-1]
			if (A[mid] - A[0] != (mid - 0) * diff) {
				right = mid - 1;
			}

			// if missing element lies on right sub-array, then reduce
			// our search space to right sub-array A[mid+1..right]
			else {
				left = mid + 1;
			}
		}
		return -1;
	}

	// https://www.techiedelight.com/division-two-numbers-using-binary-search-algorithm/
	// Function to perform division of two numbers using
	// Binary Search Algorithm
	public static double divide(double x, double y) {
		// handle divisibility by 0
		if (y == 0) {
			return Double.MAX_VALUE; // return infinity
		}

		// set range for result [left, right]
		// right is set to infinity to handle the case
		// when y < 1, x < result < Double.MAX_VALUE
		double left = 0.0, right = Double.MAX_VALUE;

		// set accuracy of the result
		double precision = 0.001;

		// store sign of the result
		int sign = 1;
		if (x * y < 0) {
			sign = -1;
		}

		// make both input numbers positive
		x = Math.abs(x);
		y = Math.abs(y);

		while (true) {
			// calculate mid
			double mid = left + ((right - left) / 2);

			// if y*mid is almost equal to x, return mid
			if (Math.abs(y * mid - x) <= precision) {
				return mid * sign;
			}

			// if y*mid is less than x, update left to mid
			if (y * mid < x) {
				left = mid;
			} else {
				// if y*mid is more than x, update right to mid
				right = mid;
			}
		}
	}

	// https://www.techiedelight.com/find-floor-ceil-number-sorted-array-recursive/
	// Function to find ceil of x in sorted array A[left..right]
	// i.e. smallest integer greater than or equal to x
	public static int getCeil(int[] A, int left, int right, int x) {
		// search space is A[left..right]

		// if x is less than equal to leftest element in search
		// space, the leftest element is the ceil
		if (x <= A[left]) {
			return A[left];
		}

		// if x is more than rightest element in the search space,
		// its ceil doesn't exists
		if (x > A[right]) {
			return -1;
		}

		// find middle index
		int mid = (left + right) / 2;

		// if x is equal to mid element, it is the ceil
		if (A[mid] == x) {
			return A[mid];
		}

		// if x is more than the mid element, ceil exists in
		// right sub-array A[mid+1..right]
		else if (A[mid] < x) {
			return getCeil(A, mid + 1, right, x);
		}

		// if x is less than the mid element, ceil exists in left
		// sub-array A[left..mid](Note - mid element can also be ceil)
		else {
			return getCeil(A, left, mid, x);
		}
	}

	// Function to find floor of x in sorted array A[left..right]
	// i.e. largest integer less than or equal to x
	public static int getFloor(int[] A, int left, int right, int x) {
		// search space is A[left..right]

		// if x is less than the leftest element in search
		// space, its floor doesn't exists
		if (x < A[left]) {
			return -1;
		}

		// if x is more than equal to the rightest element in
		// the search space, it is the floor
		if (x >= A[right]) {
			return A[right];
		}

		// find middle index
		int mid = (left + right) / 2;

		// this check is placed to handle infinite loop for
		// call getFloor(A, mid, right, x);
		if (mid == left) {
			return A[left];
		}

		// if x is equal to mid element, it is the floor
		if (A[mid] == x) {
			return A[mid];
		}

		// if x is more than the mid element, floor exists in right
		// sub-array A[mid..right](Note - mid element can also be floor)
		else if (A[mid] < x) {
			return getFloor(A, mid, right, x);
		}

		// if x is less than the mid element, floor exists in
		// left sub-array A[left..mid-1]
		else {
			return getFloor(A, left, mid - 1, x);
		}
	}

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

	// https://www.techiedelight.com/find-number-rotations-circularly-sorted-array/
	// Function to find the number of times the array is rotated
	// https://www.youtube.com/watch?v=4qjprDkJrjY
	// noOfRotations = index of min element
	public static int findRotationCount(int[] A) {
		// search space is A[left..right]
		int left = 0;
		int right = A.length - 1;

		// iterate till search space contains at-least one element
		while (left <= right) {
			// if the search space is already sorted, we have
			// found the minimum element (at index left)
			if (A[left] <= A[right]) {
				return left;
			}

			int mid = (left + right) / 2;

			// find next and previous element of the mid element
			// (in circular manner)
			int next = (mid + 1) % A.length;
			int prev = (mid - 1 + A.length) % A.length;

			// if mid element is less than both its next and previous
			// neighbor, then it is the minimum element of the array

			if (A[mid] <= A[next] && A[mid] <= A[prev]) {
				return mid;
			}

			// if A[mid..right] is sorted and mid is not the min element,
			// then pivot element cannot be present in A[mid..right] and
			// we can discard A[mid..right] and search in the left half

			else if (A[mid] <= A[right]) {
				right = mid - 1;
			}

			// if A[left..mid] is sorted then pivot element cannot be
			// present in it and we can discard A[left..mid] and search
			// in the right half

			else if (A[mid] >= A[left]) {
				left = mid + 1;
			}
		}

		// invalid input
		return -1;
	}

	public static void main(String args[]) {
		int a[] = { 2, 5, 5, 5, 6, 6, 8, 9, 9, 9 };
//		int a[] = { 15, 25, 33, 47, 50, 55, 63, 79, 81, 99 };
//		int a[]=new int[10];
//		 for(int i = 0; i < a.length-3; i++) {
//	            a[i] = 1;
//	        }
//		 
//		 System.out.println("Length::"+a.length);
		int low = 0;
		int high = a.length - 1;
		int key = 47;
		BinarySearch bs = new BinarySearch();
		System.out.println(bs.findFirstOccurance(a, 5, low, high));
		System.out.println(bs.findLastOccurance(a, 5, low, high));

		int firstOccuranceIndex = bs.findTotalOccurances(a, 5, low, high, true);
		int lastOccuranceIndex = bs.findTotalOccurances(a, 5, low, high, false);

		int totalOccurances = (lastOccuranceIndex - firstOccuranceIndex) + 1;
		System.out.println("Total occurances::::::" + totalOccurances);

//		System.out.println("Key" + key + "was found at position::::" + bs.iterative(a, key, low, high));
//		System.out.println("Key" + key + "was found at position::::" + bs.recursive(a, key, low, high));
	}

}
