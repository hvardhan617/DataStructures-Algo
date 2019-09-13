package queue;

import java.util.NoSuchElementException;

//https://www.youtube.com/watch?v=23DjZA7AuOY&list=PLsyeobzWxl7oRKwDi7wjrANsbhTX0IK0J&index=15
//https://www.youtube.com/watch?v=8K1rt6v5mJ4&list=PLsyeobzWxl7oRKwDi7wjrANsbhTX0IK0J&index=14
public class CircularQueue {

	private static int rear = 0, front = 0;
	private static final int MAX = 5;
	private int a[] = new int[MAX];
	private static int size = 0;

	public void enqueue(int data) {
		if (isFull()) {
			System.out.println("Queue is FULL...Enqueue not possible");
			return;

		} else {
			System.out.println("Inserting::::" + data);
			rear = (rear + 1) % MAX;
			a[rear] = data;
			size = size + 1;
		}

	}

	public int dequeue() {

		int poppedElement = -1;
		if (isEmpty()) {
			System.out.println("Queue is Empty...Dequeue not possible");

		} else {
			poppedElement = a[front];
			front = (front + 1) % MAX;
			size = size - 1;
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
		return getSize() == MAX;

	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return getSize() == 0;

	}

	public static void main(String args[]) {
		CircularQueue q = new CircularQueue();
		q.enqueue(13);
		q.enqueue(1343);
		q.enqueue(3);
		q.enqueue(999);
		q.enqueue(23453);
		q.enqueue(93);

//		System.out.println(q.dequeue());
	}
}
