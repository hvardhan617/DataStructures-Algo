package searching;

public class FirstBadVersion {

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
}
