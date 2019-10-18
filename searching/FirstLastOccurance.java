package searching;

//O(logn)
public class FirstLastOccurance {

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
		FirstLastOccurance bs = new FirstLastOccurance();
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
