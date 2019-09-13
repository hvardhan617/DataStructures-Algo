package searching;

//https://www.youtube.com/watch?v=CFgUQUL7j_c&list=PLi9RQVmJD2fZGdIX-y3-X37YUAWxh6GqH&index=11

//https://www.youtube.com/watch?v=2Y_0_IyZtHM

//INTERESTING!!!!!!!!!!! 	An element is a peak element if it is greater than its neighbours
public class FindPeakElement {

	public static int findUsingBinarySearch(int[] arr) {

		int n = arr.length;

		int start = 0;
		int end = n - 1;

		while (start <= end) {

			int mid = (start + end) / 2;

			// checks for boundary conditions to avoid ArrayOutOfBounds Exception
			if ((mid == 0 || arr[mid - 1] <= arr[mid]) && (mid == n - 1 || arr[mid] >= arr[mid + 1])) {
				return arr[mid];

			}
			// if element downstream is greater than mid, we would wana go to lower indices
			else if (mid > 0 && arr[mid - 1] > arr[mid]) {
				end = mid - 1;

			}
			// else if element upstream is greater than mid, we would wana go to upper
			// indices
			else {
				start = mid + 1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {

		int[] arr = { 2, 3, 4, 7, 5 };
		// int[] arr = {2, 2, 2, 2, 2};
		// int[] arr = {8, 7, 6, 5, 4};
		int result = findUsingBinarySearch(arr);
		System.out.println(result);
	}
}