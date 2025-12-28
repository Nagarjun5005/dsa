package stack.impl;
import java.util.Stack;

/**
 * QueueUsingStack represents a Queue data structure
 * implemented using two Stacks.
 *
 * <p>
 * This queue follows FIFO (First In First Out) behavior
 * while internally using LIFO (Last In First Out) stacks.
 * </p>
 *
 * <p>
 * Enqueue operation is efficient (O(1)),
 * while dequeue and peek operations are costly (O(n)).
 * </p>
 */
public class QueueUsingStackCostlyPop {

    /**
     * Primary stack used for enqueue operations.
     */
    private Stack<Integer> stack1 = new Stack<>();

    /**
     * Auxiliary stack used during dequeue and peek operations.
     */
    private Stack<Integer> stack2 = new Stack<>();

    /**
     * Inserts an element into the queue.
     *
     * <p>
     * The element is pushed directly onto stack1.
     * </p>
     *
     * @param x the element to be added
     */
    public void enqueue(int x) {
        stack1.push(x);
    }

    /**
     * Removes and returns the front element of the queue.
     *
     * <p>
     * All elements are moved from stack1 to stack2,
     * the top of stack2 is removed (front of queue),
     * and remaining elements are moved back.
     * </p>
     *
     * @return removed element, or -1 if queue is empty
     */
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow");
            return -1;
        }

        // Move elements to stack2
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        int removed = stack2.pop();

        // Move elements back to stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }

        return removed;
    }

    /**
     * Returns the front element of the queue
     * without removing it.
     *
     * @return front element, or -1 if queue is empty
     */
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }

        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        int front = stack2.peek();

        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }

        return front;
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return true if the queue has no elements,
     *         false otherwise
     */
    public boolean isEmpty() {
        return stack1.isEmpty();
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return queue size
     */
    public int size() {
        return stack1.size();
    }
}
