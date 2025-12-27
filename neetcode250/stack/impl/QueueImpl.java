package stack.impl;

/**
 * QueueImpl represents a fixed-size circular queue
 * implemented using an integer array.
 *
 * <p>
 * This queue follows the FIFO (First In First Out) principle.
 * Elements are added at the rear (end) and removed from the
 * front (start).
 * </p>
 *
 * <p>
 * Circular behavior is achieved using modulo arithmetic,
 * allowing reuse of freed space and avoiding memory wastage
 * seen in a linear queue.
 * </p>
 *
 * <p>
 * All operations (enqueue, dequeue, peek) run in O(1) time.
 * </p>
 */
public class QueueImpl {

    /**
     * Array to store queue elements.
     * Fixed capacity of 5.
     */
    int[] queue = new int[5];

    /**
     * Index pointing to the front element of the queue.
     * A value of -1 indicates the queue is empty.
     */
    int start = -1;

    /**
     * Index pointing to the rear element of the queue.
     * A value of -1 indicates the queue is empty.
     */
    int end = -1;

    /**
     * Tracks the current number of elements in the queue.
     */
    int currSize = 0;

    /**
     * Inserts an element into the queue.
     *
     * <p>
     * The element is added at the rear of the queue.
     * If the queue is full, insertion is not allowed.
     * </p>
     *
     * @param x the element to be inserted
     */
    private void push(int x) {
        if (isFull()) {
            System.out.println("Queue Overflow");
            return;
        }

        // If queue is empty, initialize start and end
        if (isEmpty()) {
            start = 0;
            end = 0;
        } else {
            // Move end circularly
            end = (end + 1) % queue.length;
        }

        queue[end] = x;
        currSize++;
    }

    /**
     * Removes and returns the front element of the queue.
     *
     * <p>
     * The element is removed from the front of the queue.
     * If the queue is empty, removal is not possible.
     * </p>
     *
     * @return the removed element, or -1 if the queue is empty
     */
    private int pop() {
        if (isEmpty()) {
            System.out.println("Queue Underflow");
            return -1;
        }

        int element = queue[start];

        // If only one element was present, reset queue
        if (currSize == 1) {
            start = -1;
            end = -1;
        } else {
            // Move start circularly
            start = (start + 1) % queue.length;
        }

        currSize--;
        return element;
    }

    /**
     * Returns the front element of the queue without removing it.
     *
     * @return the front element, or -1 if the queue is empty
     */
    private int top() {
        if (isEmpty()) {
            System.out.println("No elements to peek");
            return -1;
        }
        return queue[start];
    }

    /**
     * Checks whether the queue is full.
     *
     * @return true if the queue has reached its maximum capacity,
     *         false otherwise
     */
    private boolean isFull() {
        return currSize == queue.length;
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return true if the queue contains no elements,
     *         false otherwise
     */
    private boolean isEmpty() {
        return currSize == 0;
    }
}
