package stack.impl;

/**
 * StackImpl represents a fixed-size stack data structure
 * implemented using an integer array.
 *
 * <p>
 * This stack follows the LIFO (Last In First Out) principle.
 * All basic stack operations such as push, pop, peek, size,
 * isEmpty, and isFull run in O(1) time.
 * </p>
 */
public class StackImpl {

    /**
     * Index of the top element in the stack.
     * A value of -1 indicates that the stack is empty.
     */
    private int top = -1;

    /**
     * Array used to store stack elements.
     * The stack has a fixed capacity of 10.
     */
    private int[] stack = new int[10];

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param x the element to be pushed
     * @throws RuntimeException if the stack is full
     */
    private void push(int x) {
        if (isFull()) {
            System.out.println("---stack overflow---");
            return;
        }
        top = top + 1;
        stack[top] = x;
    }

    /**
     * Removes and returns the top element of the stack.
     *
     * @return the popped element, or -1 if the stack is empty
     */
    private int pop() {
        if (isEmpty()) {
            System.out.println("stack is already empty");
            return -1;
        }
        return stack[top--];
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the top element, or -1 if the stack is empty
     */
    private int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return stack[top];
    }

    /**
     * Checks whether the stack is full.
     *
     * @return true if the stack has reached its maximum capacity,
     *         false otherwise
     */
    private boolean isFull() {
        return top == stack.length - 1;
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack contains no elements,
     *         false otherwise
     */
    private boolean isEmpty() {
        return top == -1;
    }

    /**
     * Returns the number of elements currently present in the stack.
     *
     * @return the size of the stack
     */
    private int size() {
        return top + 1;
    }


    /**
     * Main method to test stack operations
     */
    public static void main(String[] args) {

        StackImpl stack = new StackImpl();

        // Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Peek top element
        System.out.println("Top element: " + stack.peek());

        // Current size
        System.out.println("Stack size: " + stack.size());

        // Pop elements
        stack.pop();
        stack.pop();

        // Check after pops
        System.out.println("Top element after pops: " + stack.peek());
        System.out.println("Stack size after pops: " + stack.size());

        // Pop remaining element
        stack.pop();

        // Underflow case
        stack.pop();
    }
}
