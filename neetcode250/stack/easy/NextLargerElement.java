package stack.easy;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem: Next Larger Element (Single Array)
 *
 * For each element in the array, find the first element to its right
 * that is strictly greater than it.
 * If no such element exists, return -1 for that position.
 *
 * This class demonstrates:
 * 1. Brute-force approach (O(n^2))
 * 2. Optimal stack-based approach using a Monotonic Decreasing Stack (O(n))
 */
public class NextLargerElement {

    public static void main(String[] args) {

        int[] nums = {1, 3, 2, 4};

        // Brute-force solution
        System.out.println(Arrays.toString(nextLargerElement(nums)));

        // Optimal stack-based solution
        System.out.println(Arrays.toString(nextLargerElementStack(nums)));
    }

    /**
     * Brute Force Approach
     *
     * For every element, scan elements to its right
     * and stop at the first element greater than it.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * @param arr input array
     * @return array containing next larger elements
     */
    public static int[] nextLargerElement(int[] arr) {

        int[] nge = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {

            // Default if no greater element is found
            nge[i] = -1;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    nge[i] = arr[j];
                    break;
                }
            }
        }
        return nge;
    }

    /**
     * Optimal Stack-Based Approach
     *
     * Uses a Monotonic Decreasing Stack.
     *
     * Key Idea:
     * - Traverse array from right to left
     * - Stack stores potential "next greater" candidates
     * - Smaller or equal elements are removed as they are useless
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param arr input array
     * @return array containing next larger elements
     */
    public static int[] nextLargerElementStack(int[] arr) {

        int[] nge = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        // Traverse from right to left
        for (int i = arr.length - 1; i >= 0; i--) {

            /*
             * Maintain monotonic decreasing stack:
             * Remove all elements smaller than or equal to current
             * because they can never be the next greater element.
             */
            while (!stack.isEmpty() && arr[i] >= stack.peek()) {
                stack.pop();
            }

            /*
             * If stack is empty, no greater element exists to the right.
             * Otherwise, the top of the stack is the next greater element.
             */
            nge[i] = stack.isEmpty() ? -1 : stack.peek();

            /*
             * Push current element onto stack so that it
             * can act as a candidate for elements to the left.
             */
            stack.push(arr[i]);
        }
        return nge;
    }
}
