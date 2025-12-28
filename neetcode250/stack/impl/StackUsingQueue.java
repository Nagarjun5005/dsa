 package stack.impl;
import java.util.LinkedList;
import java.util.Queue;

/**
 * StackUsingQueue represents a Stack data structure
 * implemented using a single Queue.
 *
 * <p>
 * A stack follows the LIFO (Last In First Out) principle,
 * whereas a queue follows the FIFO (First In First Out) principle.
 * This implementation simulates stack behavior using queue operations.
 * </p>
 *
 * <p>
 * The key idea is to rotate the queue after each push operation
 * so that the most recently added element always appears at the front
 * of the queue. This allows pop and peek operations to work in O(1) time.
 * </p>
 *
 * <p>
 * Time Complexity:
 * <ul>
 *   <li>Push: O(n)</li>
 *   <li>Pop: O(1)</li>
 *   <li>Peek: O(1)</li>
 * </ul>
 * </p>
 */
public class StackUsingQueue {

    /**
     * Queue used to store stack elements.
     * The front of the queue represents the top of the stack.
     */
    private Queue<Integer> queue = new LinkedList<>();

    /**
     * Pushes an element onto the stack.
     *
     * <p>
     * The element is first added to the queue.
     * Then, the queue is rotated so that the newly added
     * element moves to the front, maintaining LIFO order.
     * </p>
     *
     * @param x the element to be pushed onto the stack
     */
    public void push(int x) {
        int size = queue.size();
        queue.add(x);

        // Rotate the queue to bring the new element to the front
        for (int i = 0; i < size; i++) {
            queue.add(queue.remove());
        }
    }

    /**
     * Removes and returns the top element of the stack.
     *
     * <p>
     * Since the most recently pushed element is always
     * at the front of the queue, removal is straightforward.
     * </p>
     *
     * @return the popped element, or -1 if the stack is empty
     */
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return queue.remove();
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the top element, or -1 if the stack is empty
     */
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return queue.peek();
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack contains no elements,
     *         false otherwise
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return current size of the stack
     */
    public int size() {
        return queue.size();
    }
}
