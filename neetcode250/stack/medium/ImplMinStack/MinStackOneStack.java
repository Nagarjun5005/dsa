package stack.medium.ImplMinStack;

import java.util.Stack;

/**
 * MinStackOneStack is a stack implementation that supports
 * retrieving both minimum and maximum elements in O(1) time.
 *
 * <p>
 * This implementation uses a single stack where each element
 * stores:
 * <ul>
 *   <li>the actual value</li>
 *   <li>the minimum value up to this element</li>
 *   <li>the maximum value up to this element</li>
 * </ul>
 * </p>
 *
 * <p>
 * By storing minSoFar and maxSoFar along with each value,
 * the stack can return min and max values instantly without
 * using auxiliary stacks.
 * </p>
 *
 * <p>
 * All operations run in O(1) time.
 * Space complexity is O(n), where n is the number of elements
 * in the stack.
 * </p>
 */
public class MinStackOneStack {

    /**
     * Stack storing arrays of three integers:
     * [value, minSoFar, maxSoFar]
     */
    private Stack<int[]> stack;

    /**
     * Initializes the stack.
     */
    public MinStackOneStack() {
        stack = new Stack<>();
    }

    /**
     * Pushes a value onto the stack.
     *
     * <p>
     * For each pushed value, the minimum and maximum values
     * up to this point are calculated and stored together.
     * </p>
     *
     * @param val the value to be pushed
     */
    public void push(int val) {
        int currentMin = stack.isEmpty()
                ? val
                : Math.min(val, stack.peek()[1]);

        int currentMax = stack.isEmpty()
                ? val
                : Math.max(val, stack.peek()[2]);

        stack.push(new int[]{val, currentMin, currentMax});
    }

    /**
     * Removes the top element from the stack.
     */
    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return top element of the stack
     */
    public int top() {
        return stack.peek()[0];
    }

    /**
     * Retrieves the minimum element in the stack.
     *
     * @return current minimum value
     */
    public int getMin() {
        return stack.peek()[1];
    }

    /**
     * Retrieves the maximum element in the stack.
     *
     * @return current maximum value
     */
    public int getMax() {
        return stack.peek()[2];
    }


    /**
     * Main method to demonstrate and test MinStack operations.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        MinStackOneStack minStackObj = new MinStackOneStack();

        minStackObj.push(5);
        minStackObj.push(3);
        minStackObj.push(7);
        minStackObj.push(3);

        System.out.println("Max of the stack-->"+minStackObj.getMax());

        System.out.println("Top: " + minStackObj.top());      // 3
        System.out.println("Min: " + minStackObj.getMin());   // 3

        minStackObj.pop(); // removes 3
        System.out.println("Min after pop: " + minStackObj.getMin()); // 3

        minStackObj.pop(); // removes 7
        System.out.println("Min after pop: " + minStackObj.getMin()); // 3

        minStackObj.pop(); // removes 3
        System.out.println("Min after pop: " + minStackObj.getMin()); // 5

        System.out.println("Max of the stack-->"+minStackObj.getMax());

    }
}
