package searching;

public class SmallestMissingNumber {

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
		// if element is in the position its supposed to be, go right searching for the
		// element up
		if (a[mid] == mid) {
			return smallestMissing(a, mid + 1, high);
		} else {
			return smallestMissing(a, low, mid - 1);
		}

	}

}
