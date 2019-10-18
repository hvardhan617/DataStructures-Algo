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

	public static void main(String args[]) {
		int a[] = { 2, 5, 5, 5, 6, 6, 8, 9, 9, 9 };

//		System.out.println("Key" + key + "was found at position::::" + bs.iterative(a, key, low, high));
//		System.out.println("Key" + key + "was found at position::::" + bs.recursive(a, key, low, high));
	}

}
