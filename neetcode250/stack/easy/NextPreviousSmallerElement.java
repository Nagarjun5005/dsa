package stack.easy;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem: Next Smaller Element & Previous Smaller Element
 *
 * For a given array:
 * 1. Next Smaller Element (NSE):
 *    - Find the nearest element to the RIGHT that is strictly smaller.
 *
 * 2. Previous Smaller Element (PSE):
 *    - Find the nearest element to the LEFT that is strictly smaller.
 *
 * If no such element exists, return -1 for that position.
 *
 * This class demonstrates:
 * - Brute force solutions (O(n^2))
 * - Optimal stack-based solutions using Monotonic Increasing Stack (O(n))
 *
 * These problems are fundamental building blocks for:
 * - Largest Rectangle in Histogram
 * - Trapping Rain Water
 * - Sum of Subarray Minimums
 */
public class NextPreviousSmallerElement {

    public static void main(String[] args) {

        int[] nums = {4, 5, 2, 10, 8};

        System.out.println("Next Smaller (Brute):     " +
                Arrays.toString(nextSmallerBrute(nums)));

        System.out.println("Next Smaller (Stack):     " +
                Arrays.toString(nextSmallerStack(nums)));

        System.out.println("Previous Smaller (Brute): " +
                Arrays.toString(previousSmallerBrute(nums)));

        System.out.println("Previous Smaller (Stack): " +
                Arrays.toString(previousSmallerStack(nums)));
    }

    /* =========================================================
       NEXT SMALLER ELEMENT - BRUTE FORCE
       ========================================================= */

    /**
     * Next Smaller Element - Brute Force
     *
     * For each element, scan elements to its RIGHT
     * and return the first element that is smaller.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * @param arr input array
     * @return array of next smaller elements
     */
    public static int[] nextSmallerBrute(int[] arr) {

        int[] nse = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {

            // Default if no smaller element is found
            nse[i] = -1;

            // Scan elements to the right
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    nse[i] = arr[j];
                    break; // nearest smaller found
                }
            }
        }
        return nse;
    }

    /* =========================================================
       NEXT SMALLER ELEMENT - STACK (OPTIMAL)
       ========================================================= */

    /**
     * Next Smaller Element - Stack Based
     *
     * Uses a Monotonic Increasing Stack.
     *
     * Idea:
     * - Traverse array from RIGHT to LEFT
     * - Remove elements greater than or equal to current
     * - Top of stack gives the next smaller element
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param arr input array
     * @return array of next smaller elements
     */
    public static int[] nextSmallerStack(int[] arr) {

        int[] nse = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        // Traverse from right to left (next element problems)
        for (int i = arr.length - 1; i >= 0; i--) {

            // Maintain monotonic increasing stack
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }

            // Stack top is the next smaller element
            nse[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element
            stack.push(arr[i]);
        }
        return nse;
    }

    /* =========================================================
       PREVIOUS SMALLER ELEMENT - BRUTE FORCE
       ========================================================= */

    /**
     * Previous Smaller Element - Brute Force
     *
     * For each element, scan elements to its LEFT
     * and return the first element that is smaller.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * @param arr input array
     * @return array of previous smaller elements
     */
    public static int[] previousSmallerBrute(int[] arr) {

        int[] pse = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {

            pse[i] = -1; // default

            // Scan left side
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    pse[i] = arr[j];
                    break;
                }
            }
        }
        return pse;
    }

    /* =========================================================
       PREVIOUS SMALLER ELEMENT - STACK (OPTIMAL)
       ========================================================= */

    /**
     * Previous Smaller Element - Stack Based
     *
     * Uses a Monotonic Increasing Stack.
     *
     * Idea:
     * - Traverse array from LEFT to RIGHT
     * - Remove elements greater than or equal to current
     * - Top of stack gives the previous smaller element
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param arr input array
     * @return array of previous smaller elements
     */
    public static int[] previousSmallerStack(int[] arr) {

        int[] pse = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        // Traverse from left to right (previous element problems)
        for (int i = 0; i < arr.length; i++) {

            // Maintain monotonic increasing stack
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }

            // Stack top is the previous smaller element
            pse[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element
            stack.push(arr[i]);
        }
        return pse;
    }
}

