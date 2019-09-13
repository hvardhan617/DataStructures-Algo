package heaps;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//https://www.techiedelight.com/merge-m-sorted-lists-variable-length/
//http://www.java2s.com/Tutorials/Java/Java_Collection/0170__Java_Priority_Queues.htm
//https://forum.processing.org/two/discussion/10244/priorityqueue-and-comparable-interface-example

//We can use the Comparable interface implemented in each element as its priority.

//Or we can supply a Comparator object, which will determine the priority order of the elements.

//When adding a new element to a priority queue, it is positioned in the queue based on its priority.
//A class for heap node
class Node implements Comparable {
	// value stores the element
	private int value;

	// i stores the list number of the element
	private int i;

	// index stores column number of the list from which
	// element was taken
	private int index;

	// Constructor
	public Node(int value, int i, int index) {
		this.value = value;
		this.i = i;
		this.index = index;
	}

	public int getValue() {
		return value;
	}

	public int getListNum() {
		return i;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(Object o) {
		Node node = (Node) o;
		return value - node.value;
	}
};

public class MergeKSortedLLs {

	// Function to merge M sorted lists of variable length and
	// print them in ascending order
	public static void printSorted(List<List<Integer>> list) {
		// create an empty min-heap
		PriorityQueue<Node> pq = new PriorityQueue();

		// push first element of each list into the min-heap
		// along with list number and their index in the list
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).size() >= 1) {
				pq.add(new Node(list.get(i).get(0), i, 0));
			}
		}

		// run till min-heap is not empty
		while (!pq.isEmpty()) {
			// extract minimum node from the min-heap
			Node min = pq.poll();

			// print the minimum element
			System.out.print(min.getValue() + " ");

			// take next element from "same" list and insert it into the
			// min-heap
			// check for not index within size of the list
			if (min.getIndex() + 1 < list.get(min.getListNum()).size()) {
				min.setIndex(min.getIndex() + 1);
				min.setValue(list.get(min.getListNum()).get(min.getIndex()));
				pq.add(min);
			}
		}
	}

	public static void main(String[] args) {

		// Input format as List of lists.
		List<List<Integer>> list = Arrays.asList(Arrays.asList(10, 20, 30, 40), Arrays.asList(15, 25, 35),
				Arrays.asList(27, 29, 37, 48, 93), Arrays.asList(32, 33));

		printSorted(list);
	}
}
