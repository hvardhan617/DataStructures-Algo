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
		// loop untill last node
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

		// Move slow and fast 1 and 2 steps
		// ahead respectively.
		slow = slow.next;
		fast = fast.next.next;

		// Search for loop using slow and fast pointers
		while (fast != null && fast.next != null) {
			if (slow == fast)
				break;

			slow = slow.next;
			fast = fast.next.next;
		}

		/*
		 * If loop exists slow points to head and fast from the place they met, we need
		 * to reach tail node in loop n make it point to null
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

		// 2 references
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
	public static Node groupwiseSwap(Node head, int k) {
		if (head == null) {
			return null;
		}
		int count = 0;
		Node curr = head, prev = null;
		// reverse the LL untill count<k if count=0 or count<=k if count=1
		while (curr != null && count++ < k) {
			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;

		}

		head.next = groupwiseSwap(curr, k);
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

		while (temp != null) {
			if (count < k) {
				temp = temp.next;
				count = count + 1;
			} else {
				break;
			}
		}

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

		System.out.println(list.checkPalindromicLL(h1));
	}

}
