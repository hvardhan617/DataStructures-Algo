package arrays;

import java.util.stream.IntStream;

//https://www.techiedelight.com/find-duplicate-element-limited-range-array/
public class FindDuplicateElementInLimitedRangeArray {

	// Function to find a duplicate element in a limited range array
	public static int findDuplicate(int[] A) {
		int xor = 0;

		// take xor of all array elements
		for (int i = 0; i < A.length; i++) {
			xor ^= A[i];
		}

		// take xor of numbers from 1 to n-1
		for (int i = 1; i <= A.length - 1; i++) {
			xor ^= i;
		}

		// same elements will cancel out each other as a ^ a = 0,
		// 0 ^ 0 = 0 and a ^ 0 = a

		// xor will contain the missing number
		return xor;
	}
	
	public static int findDuplicate2(int[] A)
	{
		int actual_sum = IntStream.of(A).sum();
		int expected_sum = A.length * (A.length - 1) / 2;

		return actual_sum - expected_sum;
	}

	// Function to find a duplicate element in a limited range array
		public static int findDuplicate3(int[] A)
		{
			// create an visited array of size n+1
			// we can also use map instead of visited array
			boolean visited[] = new boolean[A.length + 1];

			// for each element of the array mark it as visited and
			// return the element if it is seen before
			for (int i = 0; i < A.length; i++)
			{
				// if element is seen before
				if (visited[A[i]]) {
					return A[i];
				}

				// mark element as visited
				visited[A[i]] = true;
			}

			// no duplicate found
			return -1;
		}

	// main function
	public static void main(String[] args) {
		// input array contains n numbers between [1 to n - 1]
		// with one duplicate, where n = A.length
		int[] A = { 1, 2, 3, 4, 4 };

		System.out.println("Duplicate element is " + findDuplicate(A));
	}
}
