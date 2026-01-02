package stack.easy;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem: Previous Larger Element
 *
 * For each element in the array, find the nearest element
 * to its LEFT that is strictly greater than the current element.
 * If no such element exists, return -1 for that position.
 *
 * This class provides:
 * 1. A brute-force solution with O(n²) time complexity
 * 2. An optimal stack-based solution using a Monotonic Decreasing Stack with O(n)
 *
 * This problem is closely related to:
 * - Next Greater Element
 * - Previous Smaller Element
 * - Daily Temperatures
 * - Stock Span
 */
public class PreviousLargerElement {

    public static void main(String[] args) {

        int[] nums = {1, 3, 2, 4};

        // Brute-force solution
        System.out.println(Arrays.toString(previousLargerElement(nums)));

        // Optimal stack-based solution
        System.out.println(Arrays.toString(previousLargerElementStack(nums)));
    }

    /**
     * Brute Force Approach
     *
     * For each element, scan elements to its LEFT
     * and return the first element that is greater.
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     *
     * @param arr input array
     * @return array containing previous larger elements
     */
    public static int[] previousLargerElement(int[] arr) {

        int[] nge = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {

            // Default value if no greater element is found on the left
            nge[i] = -1;

            // Scan elements to the left of current index
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    nge[i] = arr[j];
                    break; // nearest greater found
                }
            }
        }
        return nge;
    }

    /**
     * Optimal Stack-Based Approach
     *
     * Uses a Monotonic Decreasing Stack to efficiently
     * compute the previous larger element for each index.
     *
     * Idea:
     * - Traverse the array from LEFT to RIGHT
     * - Stack maintains elements in decreasing order
     * - Remove smaller or equal elements as they cannot be previous larger
     * - Top of stack gives the nearest greater element to the left
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param arr input array
     * @return array containing previous larger elements
     */
    public static int[] previousLargerElementStack(int[] arr) {

        int[] nge = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        // Traverse from left to right (Previous element problems)
        for (int i = 0; i < arr.length; i++) {

            /*
             * Maintain a monotonic decreasing stack:
             * Remove all elements smaller than or equal to current
             * since they cannot act as a previous greater element.
             */
            while (!stack.isEmpty() && arr[i] >= stack.peek()) {
                stack.pop();
            }

            /*
             * If stack is empty, no greater element exists on the left.
             * Otherwise, the top of the stack is the previous greater element.
             */
            nge[i] = stack.isEmpty() ? -1 : stack.peek();

            /*
             * Push current element so it can serve
             * as a candidate for upcoming elements.
             */
            stack.push(arr[i]);
        }
        return nge;
    }
}
