package stack.impl;

/**
 * QueueUsingLinkedList represents a queue data structure
 * implemented using a singly linked list.
 *
 * <p>
 * Follows FIFO (First In First Out) principle.
 * Enqueue happens at the rear and dequeue happens at the front.
 * </p>
 */
public class QueueUsingLinkedList {

    /**
     * Node represents an element in the queue.
     */
    private class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Points to the front (start) of the queue.
     */
    private Node start;

    /**
     * Points to the rear (end) of the queue.
     */
    private Node end;

    /**
     * Tracks the number of elements in the queue.
     */
    private int size = 0;

    /**
     * Inserts an element at the rear of the queue.
     *
     * @param x element to be added
     */
    private void enqueue(int x) {
        Node newNode = new Node(x);

        if (isEmpty()) {
            start = end = newNode;
        } else {
            end.next = newNode;
            end = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the front element of the queue.
     *
     * @return removed element, or -1 if queue is empty
     */
    private int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow");
            return -1;
        }

        int removed = start.data;
        start = start.next;
        size--;

        if (start == null) {
            end = null;
        }

        return removed;
    }

    /**
     * Returns the front element without removing it.
     *
     * @return front element, or -1 if queue is empty
     */
    private int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return start.data;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return current queue size
     */
    private int size() {
        return size;
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return true if queue is empty, false otherwise
     */
    private boolean isEmpty() {
        return size == 0;
    }
}
