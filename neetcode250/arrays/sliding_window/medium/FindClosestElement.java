package arrays.sliding_window.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Find K Closest Elements
 *
 * PROBLEM:
 * --------
 * Given a sorted integer array arr, an integer k, and a target value x,
 * return the k closest integers to x in the array.
 *
 * Rules:
 * ------
 * - An element a is closer to x than b if:
 *      |a - x| < |b - x|
 *      OR
 *      |a - x| == |b - x| and a < b
 * - The result must be sorted in ascending order.
 *
 * APPROACH:
 * ---------
 * Sliding Window (Two Pointers)
 *
 * Since the array is already sorted:
 * - Start with a window covering the entire array
 * - Repeatedly remove the element that is farther from x
 * - Continue until the window size becomes exactly k
 *
 * The remaining window contains the k closest elements.
 */
public class FindClosestElement {

    public static void main(String[] args) {

        // Sample input
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;
        int x = 3;

        // Find k closest elements
        List<Integer> closestElements = findClosestElements(arr, k, x);
        System.out.println(closestElements);
    }

    /**
     * Finds k closest elements to x using sliding window.
     *
     * Algorithm:
     * ----------
     * 1. Initialize two pointers:
     *    - left at start of array
     *    - right at end of array
     * 2. While window size > k:
     *    - Compare distance of arr[left] and arr[right] from x
     *    - Remove the element that is farther from x
     *    - If distances are equal, remove the right element
     *      (to keep smaller values as per problem rule)
     * 3. Collect elements from left to right as result
     *
     * Time Complexity:
     * ----------------
     * O(n - k)
     *
     * Space Complexity:
     * -----------------
     * O(1) (excluding output list)
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {

        int left = 0;
        int right = arr.length - 1;

        // Shrink window until its size becomes k
        while (right - left + 1 > k) {

            // Compare distances from x
            if (Math.abs(arr[left] - x) > Math.abs(arr[right] - x)) {
                left++;   // Remove left element
            } else {
                right--;  // Remove right element (tie → remove larger)
            }
        }

        // Build result from remaining window
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}
