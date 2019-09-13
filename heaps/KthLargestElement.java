package heaps;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//https://www.techiedelight.com/find-kth-largest-element-array/
public class KthLargestElement {

	// Function to find the K'th largest element in the
	// array using min-heap
	// construct min heap of size k,
	public static int FindKthLargest_MinHeap(List<Integer> ints, int k) {
		// create an min-heap using PriorityQueue class and insert
		// first k elements of the array into the heap ,then for each of the remaining
		// elements,if element is greater than root,we replace root
		PriorityQueue<Integer> pq = new PriorityQueue<>(ints.subList(0, k));

		// do for remaining array elements
		for (int i = k; i < ints.size(); i++) {
			// if current element is more than the root of the heap
			if (ints.get(i) > pq.peek()) {
				// replace root with the current element
				pq.poll();
				pq.add(ints.get(i));
			}
		}

		// return the root of min-heap
		return pq.peek();
	}

	// Function to find the K'th largest element in the
	// array using max-heap
	// simply add all elements into the heap of size n and keep polling max value
	// until k-1 index and the result will be at root
	public static int FindKthLargest_MaxHeap(List<Integer> ints, int k) {
		// create an max-heap using PriorityQueue class from all
		// elements in the list
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		// or pass Comparator.reverseOrder()
		pq.addAll(ints);

		// pop from max-heap exactly (k-1) times
		while (--k > 0) {
			pq.poll();
		}

		// return the root of max-heap
		return pq.peek();
	}

	public static void main(String[] args) {
		List<Integer> ints = Arrays.asList(7, 4, 6, 3, 9, 1);
		int k = 2;

		System.out.println("K'th largest element in the array is " + FindKthLargest_MinHeap(ints, k));
	}
}
