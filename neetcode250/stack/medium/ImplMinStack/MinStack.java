package stack.medium.ImplMinStack;

import java.util.Stack;

/**
 * MinStack is a stack data structure that supports
 * retrieving the minimum element in constant time.
 *
 * <p>
 * This implementation uses two stacks:
 * <ul>
 *   <li><b>mainStack</b> – stores all pushed elements</li>
 *   <li><b>minStack</b> – stores the minimum element at each level</li>
 * </ul>
 * </p>
 *
 * <p>
 * The minimum element is always available at the top of minStack,
 * allowing getMin() to run in O(1) time.
 * </p>
 *
 * <p>
 * Supported operations:
 * <ul>
 *   <li>push</li>
 *   <li>pop</li>
 *   <li>top</li>
 *   <li>getMin</li>
 * </ul>
 * </p>
 *
 * <p>
 * Time Complexity:
 * <ul>
 *   <li>push → O(1)</li>
 *   <li>pop → O(1)</li>
 *   <li>top → O(1)</li>
 *   <li>getMin → O(1)</li>
 * </ul>
 * </p>
 *
 * <p>
 * Space Complexity: O(n), due to the auxiliary minStack.
 * </p>
 */
public class MinStack {

    /**
     * Main stack that stores all elements.
     */
    private Stack<Integer> mainStack;

    /**
     * Auxiliary stack that stores the minimum value
     * corresponding to each level of the main stack.
     */
    private Stack<Integer> minStack;

    /**
     * Initializes the MinStack.
     * Both stacks start empty.
     */
    public MinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * Pushes an element onto the stack.
     *
     * <p>
     * The element is always pushed onto mainStack.
     * If the element is smaller than or equal to the
     * current minimum, it is also pushed onto minStack.
     * </p>
     *
     * @param x the value to be pushed
     */
    public void push(int x) {
        mainStack.push(x);

        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    /**
     * Removes the top element from the stack.
     *
     * <p>
     * If the removed element is equal to the current
     * minimum, it is also removed from minStack to
     * keep both stacks synchronized.
     * </p>
     */
    public void pop() {
        if (mainStack.isEmpty()) {
            return;
        }

        int popped = mainStack.pop();

        if (popped == minStack.peek()) {
            minStack.pop();
        }
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the top element of mainStack
     */
    public int top() {
        return mainStack.peek();
    }

    /**
     * Retrieves the minimum element in the stack.
     *
     * <p>
     * The minimum value is always available at the
     * top of minStack.
     * </p>
     *
     * @return the minimum element in the stack
     */
    public int getMin() {
        return minStack.peek();
    }

    /**
     * Main method to demonstrate and test MinStack operations.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        MinStack minStackObj = new MinStack();

        minStackObj.push(5);
        minStackObj.push(3);
        minStackObj.push(7);
        minStackObj.push(3);

        System.out.println("Top: " + minStackObj.top());      // 3
        System.out.println("Min: " + minStackObj.getMin());   // 3

        minStackObj.pop(); // removes 3
        System.out.println("Min after pop: " + minStackObj.getMin()); // 3

        minStackObj.pop(); // removes 7
        System.out.println("Min after pop: " + minStackObj.getMin()); // 3

        minStackObj.pop(); // removes 3
        System.out.println("Min after pop: " + minStackObj.getMin()); // 5
    }
}
