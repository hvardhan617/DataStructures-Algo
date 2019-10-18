package LL;

//Careful!! while iterating a LL, temp.next !=null gets u to d last node.
public class LL {

	private static class Node {

		int data;
		Node next;
		Node child; // used for flattening LL

		// Constructor
		Node(int d) {
			data = d;
			next = null;
			child = null;
		}

		Node() {

		}
	}

	public static void print(Node root) {
		if (root == null)
			return;
		else {
			Node temp = root;
			while (temp != null) {
				System.out.println(temp.data);
				temp = temp.next;
			}
		}
	}

	public int count(Node root) {
		int count = 0;
		if (root == null)
			return count;
		else {
			Node temp = root;
			while (temp != null) {
				count = count + 1;
				temp = temp.next;
			}
			return count;
		}

	}

	public Node insertAtHead(Node head, int data) {

		if (head == null) {
			return null;
		}
		Node newNode = new Node(data);
		newNode.next = head;
		head = newNode;
		return head;

	}

	// loop till temp.next
	public Node insertAtTail(Node head, int data) {
		if (head == null) {
			return null;
		}
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = new Node(data);
		return head;

	}

	public Node insertAtPosition(Node head, int data, int position) {
		if (position >= 1) {
			int count = 0;
			Node newNode = new Node(data);
			Node temp = head;
			while (temp != null) {
				count = count + 1;
				if (count == position) {

					newNode.next = temp.next;
					temp.next = newNode;
					break;
				}
				temp = temp.next;
			}
		}
		return head;

	}

	public Node deleteAtHead(Node head) {
		if (head == null) {
			return null;
		}
		Node temp = head.next;
		head.next = null;
		head = temp;
		return head;
	}

	// 2 pointers
	public Node deleteAtTail(Node head) {
		if (head == null) {
			return null;
		}
		Node prev = head;
		Node temp = head.next;
		// loop until last node
		while (temp.next != null) {
			prev = prev.next;
			temp = temp.next;
		}
		prev.next = null;
		return head;
	}

	// 2 pointers
	public Node deleteAtPosition(Node head, int data, int position) {

		if (head == null)
			return null;

		int count = 0;

		Node prev = head;
		Node temp = head.next;
		while (temp != null) {
			count = count + 1;
			if (count == position && data == temp.data) {
				prev.next = temp.next;
				temp.next = null;
			}
			prev = prev.next;
			temp = temp.next;
		}
		return head;
	}

	// https://www.geeksforgeeks.org/reverse-a-linked-list/
	Node reverse(Node head) {
		Node prev = null;
		Node current = head;
		Node next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		head = prev;
		return head;
	}

	// https://www.youtube.com/watch?v=oZuR2-AKkXE&t=558s
	// https://www.techiedelight.com/check-if-linked-list-is-palindrome/
	public boolean checkPalindromicLL(Node head) {

		boolean odd = false;
		if (head == null) {
			return false;
		} else if (head.next == null) {
			return true;
		} else {
			// fast and slow pointers (slow for representing tail of 1st LL & head of second
			// LL, fast for
			// finding out if odd/even nodes)
			Node fast = head;
			Node slow = head;
			Node start_second = null;
			while (true) {
				fast = fast.next.next;

				// even number of nodes
				if (fast == null) {
					start_second = slow.next;
					break;
				}
				// odd number of nodes
				else if (fast.next == null) {
					odd = true;
					start_second = slow.next.next;
					slow = slow.next;
					break;
				}
				slow = slow.next;
			}
			slow.next = null;
			print(start_second);
			Node reversed_second = reverse(start_second);
//			print(reversed_second);
			Node temp = head;
//
//			System.out.println("Nodes are odd:::::" + odd);

			print(head);
			if (odd) {
				while (temp.next.next != null) {
					if (temp.data == reversed_second.data) {
						temp = temp.next;
						reversed_second = reversed_second.next;

					} else {
						return false;
					}

				}
			} else {
				while (temp.next != null) {
					if (temp.data == reversed_second.data) {
						temp = temp.next;
						reversed_second = reversed_second.next;

					} else {
						return false;
					}
				}
			}

			return true;
		}

	}

	// move once n put fast n slow pointers in loop
	// https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
	// Function that detects loop in the list
	// floyd cycle detection
	void detectAndRemoveLoop(Node node) {

		// If list is empty or has only one node
		// without loop
		if (node == null || node.next == null)
			return;

		Node slow = node, fast = node;

		// Search for loop using slow and fast pointers
		while (slow != null && fast != null && fast.next != null) {

			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast)
				break;
		}

		/*
		 * If loop exists we make slow point to head and fast from the place they met,
		 * we need to reach tail node in loop n make it point to null
		 */
		if (slow == fast) {
			slow = node;
			while (slow.next != fast.next) {
				slow = slow.next;
				fast = fast.next;
			}

			/* since fast->next is the looping point */
			fast.next = null; /* remove loop */
		}
	}

	// Remove loop by counting nodes in loop
	// Function to remove loop
	void removeLoop(Node loop, Node head) {
		Node ptr1 = loop;
		Node ptr2 = loop;

		// Count the number of nodes in loop
		int k = 1, i;
		while (ptr1.next != ptr2) {
			ptr1 = ptr1.next;
			k++;
		}

		// Fix one pointer to head
		ptr1 = head;

		// And the other pointer to k nodes after head
		ptr2 = head;
		for (i = 0; i < k; i++) {
			ptr2 = ptr2.next;
		}

		/*
		 * Move both pointers at the same pace, they will meet at loop starting node
		 */
		while (ptr2 != ptr1) {
			ptr1 = ptr1.next;
			ptr2 = ptr2.next;
		}

		// Get pointer to the last node
		while (ptr2.next != ptr1) {
			ptr2 = ptr2.next;
		}

		/*
		 * Set the next node of the loop ending node to fix the loop
		 */
		ptr2.next = null;
	}

	// 2 temp pointers to 2 heads
	public static Node cloneLL(Node head1) {
		if (head1 == null) {
			return null;
		}
		Node head2 = null;
		head2 = new Node(head1.data);
		Node temp2 = head2;
		Node temp = head1.next;
		while (temp != null) {
			Node newNode = new Node(temp.data);
			temp2.next = newNode;
			temp = temp.next;
			temp2 = temp2.next;
		}
		print(head2);
		return head2;

	}

	// Dutch national flag problem for LLs
	// https://www.techiedelight.com/sort-linked-list-containing-0s-1s-2s/
	// create 3 dummy NODES n point them to original list n combine them
	// AMAZING approach!!!!!
	public static Node sort_0_1_2LL(Node head) {

		if (head == null) {
			return null;
		}
		// for maintaining heads of seperate nodes
		Node dummy_0 = new Node(), dummy_1 = new Node(), dummy_2 = new Node();

		// references for their heads
		Node zero = dummy_0, one = dummy_1, two = dummy_2;
		Node curr = head;

		while (curr != null) {
			if (curr.data == 0) {
				zero.next = curr;
				zero = zero.next;
			} else if (curr.data == 1) {
				one.next = curr;
				one = one.next;
			} else if (curr.data == 2) {
				two.next = curr;
				two = two.next;
			}

			curr = curr.next;
		}

		// combining all 2 lists,check if list with 1 is null,if not point it to 1 or
		// else to 2
		zero.next = (dummy_1.next != null) ? dummy_1.next : dummy_2.next;
		one.next = dummy_2.next;
		two.next = null;

		return dummy_1.next;
	}

	// perform merge sort on a linked list
	public static Node sortLL(Node head) {

		if (head == null || head.next == null) {
			return head;
		}
		Node temp = head; // for tail of 1st LL
		Node slow = head; // for head of 2nd LL
		Node fast = head;

		// for reaching the middle of a linked list to split LLs for mergeSort
		while (fast != null && fast.next != null) {

			temp = slow;
			slow = slow.next;
			fast = fast.next.next;

		}
		temp.next = null;

		Node left = sortLL(head);
		Node right = sortLL(slow);

		return merge(left, right);
	}

	private static Node merge(Node a, Node b) {

		// empty head node
		Node head = new Node(0);
		Node temp = head;
		while (a != null && b != null) {
			if (a.data < b.data) {
				temp.next = a;
				a = a.next;
			} else {
				temp.next = b;
				b = b.next;
			}

			temp = temp.next;
		}

		// if b is exhausted and a is left over
		if (a != null) {
			temp.next = a;
			a = a.next;
		}
		// if a is exhausted and b is left over
		if (b != null) {
			temp.next = b;
			b = b.next;
		}

		// return linked list from head.next as head is taken as dummy node
		return head.next;

	}

	// use the merge function in mergeSort of linked list
	public static Node merge2SortedLL(Node head1, Node head2) {
		Node newHead = new Node(0);
		Node temp = newHead;
		while (head1 != null && head2 != null) {
			if (head1.data <= head2.data) {
				temp.next = head1;
				head1 = head1.next;
			} else if (head1.data > head2.data) {
				temp.next = head2;
				head2 = head2.next;
			}
			temp = temp.next;
		}

		// if b is exhausted and a is left over
		if (head1 != null) {
			temp.next = head1;
			head1 = head1.next;
		}
		// if a is exhausted and b is left over
		if (head2 != null) {
			temp.next = head2;
			head2 = head2.next;
		}

		return newHead.next;

	}

	// tricky!!!Memorize
	// https://www.geeksforgeeks.org/merge-two-sorted-linked-lists-such-that-merged-list-is-in-reverse-order/
	public static Node mergeReverse(Node a, Node b) {

		// if both the nodes are null
		if (a == null && b == null) {
			return null;
		}

		Node res = null;

		while (a != null && b != null) {
			// first save next node and then set it as first node
			if (a.data <= b.data) {
				Node next = a.next;
				a.next = res;
				res = a;
				a = next;
			} else if (a.data > b.data) {
				Node next = b.next;
				b.next = res;
				res = b;
				b = next;
			}

		}

		// if b is exhausted and a is left over
		while (a != null) {
			Node next = a.next;
			a.next = res;
			res = a;
			a = next;
		}
		// if a is exhausted and b is left over
		while (b != null) {
			Node next = b.next;
			b.next = res;
			res = b;
			b = next;
		}

		return res;
	}

	// use child n next pointers
	// https://www.geeksforgeeks.org/flatten-a-linked-list-with-next-and-child-pointers/
	// https://www.techiedelight.com/convert-multilevel-linked-list-singly/?source=post_page---------------------------
	public static Node flattenMultiLevelLL(Node head) {
		Node curr = head;
		Node tail = head;
		Node tmp = null;
		/* Find tail node of first level linked list */
		while (tail != null) {
			tail = tail.next;
		}

		// One by one traverse through all nodes of first level
		// linked list till we reach the tail node
		while (curr != tail) {

			// If current node has a child
			if (curr.child != null) {
				// then append the child at the end of current list
				tail.next = curr.child;

				// and update the tail to new last node,traverse the new child list and make
				// tail point to its tail
				tmp = curr.child;
				while (tmp.next != null) {
					tmp = tmp.next;
				}
				tail = tmp;
			}
			// Change current node
			curr = curr.next;
		}
		return head;
	}

	// https://www.techiedelight.com/reverse-every-k-nodes-of-a-linked-list/?source=post_page---------------------------
	// https://www.geeksforgeeks.org/reverse-linked-list-groups-given-size-set-2/
	// includes reversing a LL
	public static Node groupwiseSwap(Node head, int k) {
		if (head == null) {
			return null;
		}
		int count = 0;
		Node curr = head, prev = null;
		// reverse the LL until count<k if count=0 or count<=k if count=1
		while (curr != null && count < k) {
			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;

			count = count + 1;

		}

		head.next = groupwiseSwap(curr, k);// only diff from reverse
		return prev;

	}

	// https://www.techiedelight.com/find-kth-node-from-the-end-linked-list/?source=post_page---------------------------
	public static Node findKthNodeFromEnd(Node head, int k) {
		if (head == null) {
			return null;
		}
		Node temp = head;
		Node temp2 = head;
		int count = 1;

		// travel k nodes (temp reached kth node from beginning)
		while (temp != null) {
			if (count < k) {
				temp = temp.next;
				count = count + 1;
			} else {
				break;
			}
		}

		// start temp2 from head & temp from kth node
		while (temp.next != null) {
			temp = temp.next;
			temp2 = temp2.next;
		}
		return temp2;
	}

	// https://www.techiedelight.com/add-two-linked-lists-without-using-extra-space/
	// Function to add two lists X and Y
	public Node addLists(Node X, Node Y) {
		// reverse X and Y to access elements from the end
		X = reverse(X);
		Y = reverse(Y);

		return reverse(add(X, Y));
	}

	// Function to add two lists X and Y
	public static Node add(Node X, Node Y) {
		Node head = null;

		// stores last seen node
		Node prevNode = null;

		// initialize carry with 0
		int carry = 0;

		// run till both list are empty
		while (X != null || Y != null) {
			// sum is X's data + Y's data + carry (if any)
			int sum = 0;
			if (X != null) {
				sum += X.data;
			}
			if (Y != null) {
				sum += Y.data;
			}
			sum += carry;

			// if sum of 2-digit number, reduce it and update carry
			carry = sum / 10;
			sum = sum % 10;

			// create a new node with calculated sum
			Node node = new Node(sum);

			// if the output list is empty
			if (head == null) {
				// update prev and head to point to the new node
				prevNode = node;
				head = node;
			} else {
				// add new node to the output list
				prevNode.next = node;

				// update prev node to point to the new node
				prevNode = node;
			}

			// advance X and Y for next iteration of the loop
			if (X != null) {
				X = X.next;
			}

			if (Y != null) {
				Y = Y.next;
			}
		}

		if (carry != 0) {
			prevNode.next = new Node(carry);
		}

		return head;
	}

	// https://www.geeksforgeeks.org/rearrange-a-linked-list-such-that-all-even-and-odd-positioned-nodes-are-together/
	// Rearranges given linked list
	// such that all even positioned
	// nodes are before odd positioned.
	// Returns new head of linked List.
	// https://www.youtube.com/watch?v=C_LA6SOwVTM
	// seperately build even and odd linked lists and then connect
	static Node rearrangeEvenOdd(Node head) {
		// Corner case
		if (head == null)
			return null;

		// Initialize first nodes of even and
		// odd lists
		Node odd = head;
		Node even = head.next;

		// Remember the first node of even list so
		// that we can connect the even list at the
		// end of odd list.
		Node evenFirst = even;

		while (even != null && even.next != null) {
			odd.next = even.next;
			odd = odd.next;
			even.next = odd.next;
			even = even.next;
		}
		odd.next = evenFirst;
		return head;
	}

	// https://www.youtube.com/watch?v=zbQHT7ssPzE
	static Node seperateEvenOdd(Node head) {
		return head;

	}

	// https://www.techiedelight.com/rearrange-linked-list-specific-manner-linear-time/
//	rearrangeLL
//	
	// https://www.techiedelight.com/delete-every-n-nodes-linked-list-skipping-m-nodes/
//	deleteNnodesSkippingMnodes
	public static Node deleteNAfterMNodes(Node head, int m, int n) {
		Node temp = head;

		while (true) {

			int i = 1;
			while (i < m && temp != null) {
				temp = temp.next;
				i++;
			}
			Node temp1 = temp;
			int j = 0;
			while (j < n && temp != null) {
				temp = temp.next;
				j++;
			}
			if (temp1 != null && temp != null) {
				temp1.next = temp.next;
				temp = temp.next;
			} else {
				break;
			}

		}
		return head;
	}

	// https://www.geeksforgeeks.org/check-if-a-linked-list-is-circular-linked-list/
	public static boolean checkForCircularLL(Node root) {

		Node temp = root;
		while (temp != null) {
			if (temp.next == root) {
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	// https://www.geeksforgeeks.org/swap-nodes-in-a-linked-list-without-swapping-data/
	public static Node swapLinkWithoutSwappingData(Node head, int a, int b) {
		Node temp = head;

		while (temp.next != null && temp.next.data != a) {
			temp = temp.next;
		}
		Node prev_a = temp;
		Node node_a = temp.next;

		while (temp.next != null && temp.next.data != b) {
			temp = temp.next;
		}

		Node prev_b = temp;
		Node node_b = temp.next;

		// in case of adjacent nodes
		if (node_a.next.data == b) {
			Node temp_b = node_b.next;
			prev_a.next = node_b;
			node_b.next = node_a;
			node_a.next = temp_b;
		} else {

			Node temp_b = node_b.next;
			prev_a.next = node_b;
			node_b.next = node_a.next;
			prev_b.next = node_a;
			node_a.next = temp_b;
		}

		return head;
	}

	// https://www.geeksforgeeks.org/count-rotations-sorted-rotated-linked-list/
	// search for a node whose next is smaller than its value

	// https://www.geeksforgeeks.org/sort-linked-list-already-sorted-absolute-values/
	// O(nlogn) using merge sort
	// O(n) - efficient An important observation is, all negative elements are
	// present in reverse order. So we traverse the list, whenever we find an
	// element that is out of order, we move it to the front of linked list.
	// To sort a linked list by actual values.
	// The list is assumed to be sorted by absolute
	// values.
	Node sortedList(Node head) {
		// Initialize previous and current nodes
		Node prev = head;
		Node curr = head.next;

		// Traverse list
		while (curr != null) {
			// If curr is smaller than prev, then
			// it must be moved to head
			if (curr.data < prev.data) {
				// Detach curr from linked list
				prev.next = curr.next;

				// Move current node to beginning
				curr.next = head;
				head = curr;

				// Update current
				curr = prev;
			}

			// Nothing to do if current element
			// is at right place
			else
				prev = curr;

			// Move current
			curr = curr.next;
		}
		return head;
	}

	// https://www.geeksforgeeks.org/find-length-of-loop-in-linked-list/
	// Returns count of nodes present in loop.
	static int countNodes(Node n) {
		int res = 1;
		Node temp = n;
		while (temp.next != n) {
			res++;
			temp = temp.next;
		}
		return res;
	}

	/*
	 * This function detects and counts loop nodes in the list. If loop is not there
	 * in then returns 0
	 */
	static int countNodesinLoop(Node list) {
		Node slow_p = list, fast_p = list;

		while (slow_p != null && fast_p != null && fast_p.next != null) {
			slow_p = slow_p.next;
			fast_p = fast_p.next.next;

			/*
			 * If slow_p and fast_p meet at some point then there is a loop
			 */
			if (slow_p == fast_p)
				return countNodes(slow_p);
		}

		/* Return 0 to indeciate that ther is no loop */
		return 0;
	}

	// https://www.geeksforgeeks.org/rotate-a-linked-list/
	// To rotate the linked list, we need to change next of kth node to NULL, next
	// of the last node to the previous head node, and finally, change head to
	// (k+1)th node. So we need to get hold of three nodes: kth node, (k+1)th node
	// and last node.
	// Traverse the list from the beginning and stop at kth node. Store pointer to
	// kth node. We can get (k+1)th node using kthNode->next. Keep traversing till
	// the end and store pointer to last node also. Finally, change pointers as
	// stated above.
	public static void rotateAntiClockwise(Node head, int k) {

		if (k == 0)
			return;

		// Let us understand the below code for example k = 4
		// and list = 10->20->30->40->50->60.
		Node current = head;

		// current will either point to kth or NULL after this
		// loop. current will point to node 40 in the above example
		int count = 1;
		while (count < k && current != null) {
			current = current.next;
			count++;
		}

		// If current is NULL, k is greater than or equal to count
		// of nodes in linked list. Don't change the list in this case
		if (current == null)
			return;

		// current points to kth node. Store it in a variable.
		// kthNode points to node 40 in the above example
		Node kthNode = current;

		// current will point to last node after this loop
		// current will point to node 60 in the above example
		while (current.next != null)
			current = current.next;

		// Change next of last node to previous head
		// Next of 60 is now changed to node 10

		current.next = head;

		// Change head to (k+1)th node
		// head is now changed to node 50
		head = kthNode.next;

		// change next of kth node to null
		kthNode.next = null;
	}

	public static void main(String args[]) {
		Node head = new Node(10);
		Node n1 = new Node(15);
		Node n2 = new Node(2);
		Node n3 = new Node(199);
		Node n4 = new Node(35);
		Node n5 = new Node(9);
		Node n6 = new Node(150);
		Node n7 = new Node(50);

		head.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;

		Node head2 = new Node(9);
		Node n1_2 = new Node(22);
		Node n2_2 = new Node(39);
		Node n3_2 = new Node(100);
		Node n4_2 = new Node(101);
		Node n5_2 = new Node(102);

		head2.next = n1_2;
		n1_2.next = n2_2;
		n2_2.next = n3_2;
		n3_2.next = n4_2;
		n4_2.next = n5_2;

		LL list = new LL();
//		list.print(head);

//		list.print(list.insertAtHead(head, 100));
//		list.print(list.insertAtPosition(head, 999, 2));
//		Node res = list.insertAtTail(head, 1);
//		list.print(res);

//		list.print(list.deleteAtHead(res));
//		list.print(list.deleteAtPosition(head, 999, 2));
//		list.print(list.deleteAtTail(res));
//		cloneLL(head);
//		sortLL(head);

		// print(merge2SortedLL(sortLL(head), head2));

//		print(mergeReverse(sortLL(head), head2));
//		print(sort_0_1_2LL(head));
//		System.out.println("5th node from end::::" + findKthNodeFromEnd(head, 5).data);
//		print(groupwiseSwap(head, 3));

		Node h1 = new Node(1);
		h1.next = new Node(2);
		h1.next.next = new Node(3);
		h1.next.next.next = new Node(4);
		h1.next.next.next.next = new Node(3);
		h1.next.next.next.next.next = new Node(2);
		h1.next.next.next.next.next.next = new Node(1);

		Node h2 = new Node(1);
		h2.next = new Node(2);
		h2.next.next = new Node(3);
		h2.next.next.next = new Node(4);
		h2.next.next.next.next = new Node(5);
		h2.next.next.next.next.next = new Node(6);
		h2.next.next.next.next.next.next = new Node(7);
		h2.next.next.next.next.next.next.next = new Node(8);
		h2.next.next.next.next.next.next.next.next = new Node(9);
		h2.next.next.next.next.next.next.next.next.next = new Node(10);

//		System.out.println(list.checkPalindromicLL(h1));
//		h2 = deleteNAfterMNodes(h2, 3, 2);
//		print(h2);

		/* Start with the empty list */
		Node h3 = new Node(1);
		h3.next = new Node(2);
		h3.next.next = new Node(3);
		h3.next.next.next = new Node(4);

//		System.out.print(checkForCircularLL(head) ? "Yes\n" : "No\n");

		// Making linked list circular
		head.next.next.next.next = head;

//		System.out.print(checkForCircularLL(head) ? "Yes\n" : "No\n");

		h2 = swapLinkWithoutSwappingData(h2, 3, 4);
		print(h2);
	}

}
