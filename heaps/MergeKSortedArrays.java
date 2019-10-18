package heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

//Props of a Priority Q (https://www.geeksforgeeks.org/priority-queue-class-in-java-2/)
//https://algorithmsandme.com/merge-k-sorted-arrays/
//The complexity of the code to merge k sorted arrays is O(n * k * log k) along with space complexity of O(k)
//https://www.youtube.com/watch?v=ptYUCjfNhJY&list=PLiQ766zSC5jN42O1DBalnkom5y2LXtnnK&index=12&t=0s
public class MergeKSortedArrays {

	private static class HeapNode {

		public int arrayNum; // array number to figure out the next element to be added from which array into
								// the min heap
		public int index; // index of element added from array
		public int value; // value of the element

		public HeapNode(int arrayNum, int index, int value) {
			this.arrayNum = arrayNum;
			this.index = index;
			this.value = value;
		}
	}

	public static int[] mergeKSortedArrays(int[][] arrays) {

		if (arrays == null)
			return null;

		// arrays.length in case of 2d matrix returns number of rows
		// (number of arrays == k)
		PriorityQueue<HeapNode> minHeap = new PriorityQueue<>(arrays.length,
				(HeapNode a, HeapNode b) -> a.value - b.value);

		int size = 0;
		// arrays[i].length gives column length which is number of elements in each
		// array
		// COUNT NUMBER OF ELEMENTS in all the lists to store the final output in a new
		// array
		for (int i = 0; i < arrays.length; i++) {
			size += arrays[i].length;
		}
		int[] result = new int[size]; // k * n

		// add first elements in each array to this heap
		for (int i = 0; i < arrays.length; i++) {
			minHeap.add(new HeapNode(i, 0, arrays[i][0]));
		}

		// Complexity O(n * k * log k)
		for (int i = 0; i < size; i++) {
			// Take the minimum value and put into result
			HeapNode node = minHeap.poll();// returns & removes the min element from the heap

			if (node != null) {
				result[i] = node.value;
				// check for valid index about to be processed in that array
				if (node.index + 1 < arrays[node.arrayNum].length) {
					// Complexity of O(log k)
					minHeap.add(new HeapNode(node.arrayNum, node.index + 1, arrays[node.arrayNum][node.index + 1]));
				}
			}
		}
		return result;
	}

	public static void main(String args[]) {

		// input format 2d array
		int[][] input = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };

		// int[] expectedOutput = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

		int[] output = mergeKSortedArrays(input);

		System.out.println(Arrays.toString(output));
	}

}
