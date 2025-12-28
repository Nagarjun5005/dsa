package stack.impl;

import java.util.Stack;

/**
 * QueueUsingStackCostlyPush represents a Queue
 * implemented using two Stacks.
 *
 * <p>
 * This implementation makes the enqueue (push) operation costly
 * so that dequeue (pop) and peek operations run in O(1) time.
 * </p>
 *
 * <p>
 * FIFO (First In First Out) behavior is achieved by rearranging
 * elements during each enqueue operation.
 * </p>
 */
public class QueueUsingStackCostlyPush {

    /**
     * Primary stack that always maintains queue order.
     * The top of this stack represents the front of the queue.
     */
    private Stack<Integer> stack1 = new Stack<>();

    /**
     * Temporary helper stack used during enqueue.
     */
    private Stack<Integer> stack2 = new Stack<>();

    /**
     * Inserts an element into the queue.
     *
     * <p>
     * Steps:
     * <ol>
     *   <li>Move all elements from stack1 to stack2</li>
     *   <li>Push new element into stack1</li>
     *   <li>Move all elements back from stack2 to stack1</li>
     * </ol>
     * </p>
     *
     * @param x the element to be added to the queue
     */
    public void enqueue(int x) {

        // Move all elements to helper stack
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        // Push new element
        stack1.push(x);

        // Move elements back to stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    /**
     * Removes and returns the front element of the queue.
     *
     * @return removed element, or -1 if queue is empty
     */
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow");
            return -1;
        }
        return stack1.pop();
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
        return stack1.peek();
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return true if the queue has no elements
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
