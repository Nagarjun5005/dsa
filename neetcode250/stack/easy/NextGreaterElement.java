package stack.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Problem: Next Greater Element I
 *
 * Given two arrays nums1 and nums2 where nums1 is a subset of nums2,
 * for each element in nums1, find the next greater element to its right
 * in nums2. If no such element exists, return -1 for that position.
 *
 * This class provides:
 * 1. A brute-force solution (O(n^2))
 * 2. An optimal stack-based solution using Monotonic Stack (O(n))
 *
 * This is a classic Stack + HashMap problem and a foundation
 * for many similar problems like:
 * - Daily Temperatures
 * - Stock Span
 * - Next / Previous Greater Element
 */
public class NextGreaterElement {

    public static void main(String[] args) {

        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};

        // Brute force approach
        int[] bruteResult = nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(bruteResult));

        // Stack based optimal approach
        int[] stackResult = nextGreaterElementStack(nums1, nums2);
        System.out.println(Arrays.toString(stackResult));
    }

    /**
     * Brute Force Approach
     *
     * For each element in nums1:
     * 1. Find its index in nums2
     * 2. Scan elements to the right
     * 3. Return the first element greater than it
     *
     * Time Complexity: O(n * m)
     * Space Complexity: O(1)
     *
     * @param nums1 subset array
     * @param nums2 main array
     * @return array containing next greater elements
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int[] nge = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {

            int currentElement = nums1[i];
            int placedAtIndex = -1;

            // Step 1: Find index of currentElement in nums2
            for (int j = 0; j < nums2.length; j++) {
                if (currentElement == nums2[j]) {
                    placedAtIndex = j;
                    break;
                }
            }

            // Step 2: Find the next greater element to the right
            nge[i] = -1; // default if not found
            for (int j = placedAtIndex + 1; j < nums2.length; j++) {
                if (nums2[j] > currentElement) {
                    nge[i] = nums2[j];
                    break;
                }
            }
        }
        return nge;
    }

    /**
     * Optimal Stack-Based Approach (Monotonic Decreasing Stack)
     *
     * Idea:
     * - Traverse nums2 once
     * - Maintain a decreasing stack of elements waiting for their
     *   next greater element
     * - When a greater element is found, resolve all smaller elements
     *   from the stack
     * - Store results in a HashMap for O(1) lookup
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param nums1 subset array
     * @param nums2 main array
     * @return array containing next greater elements
     */
    public static int[] nextGreaterElementStack(int[] nums1, int[] nums2) {

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        /*
         * Step 1: Process nums2 and compute next greater elements
         *
         * Stack stores elements in decreasing order.
         * Whenever we see a larger number, it becomes the
         * next greater element for everything smaller on stack.
         */
        for (int num : nums2) {

            // Resolve next greater elements
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }

            // Current element waits for its next greater
            stack.push(num);
        }

        /*
         * Step 2: Elements left in stack have no greater element
         */
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        /*
         * Step 3: Build result for nums1 using precomputed map
         */
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }
}
