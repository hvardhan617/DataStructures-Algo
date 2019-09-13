package queue;

import java.util.NoSuchElementException;

public class Queue {

	private static int rear = -1, front = -1;
	private static final int MAX = 10;
	private int a[] = new int[MAX];

	public void enqueue(int data) {
		if (isFull()) {
			System.out.println("Queue is FULL...Enqueue not possible");
			return;

		} else if (isEmpty()) {
			front = rear = 0;
			a[rear] = data;

		} else {
			rear = rear + 1;
			a[rear] = data;
		}

	}

	public int dequeue() {
		
		int poppedElement=-1;
		if (isEmpty()) {
			System.out.println("Queue is Empty...Dequeue not possible");
			
			} else if(front==rear){
				poppedElement=a[front];
				front=rear=-1;
			}
		 else {
			 poppedElement=a[front];
			front=front+1;
		}
		return poppedElement;
	}

	public int peek() {
		if (!isEmpty())
			return a[rear];
		else {
			throw new NoSuchElementException("Underflow Exception");
		}
	}

	public boolean isFull() {
		if (rear == MAX - 1)
			return true;
		else
			return false;

	}

	public boolean isEmpty() {
		if ((front == -1) && (rear == -1))
			return true;
		else
			return false;

	}

	public static void main(String args[]) {
		Queue q = new Queue();
		q.enqueue(13);
		q.enqueue(1343);
		q.enqueue(3);
		q.enqueue(999);
		q.enqueue(23453);
		q.enqueue(93);

		System.out.println(q.dequeue());
	}

}

