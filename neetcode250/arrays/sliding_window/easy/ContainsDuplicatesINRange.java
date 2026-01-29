package arrays.sliding_window.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * =============================================================
 * Problem: Contains Duplicate II
 * =============================================================
 *
 * Given an integer array nums and an integer k, return true if there
 * exist two distinct indices i and j such that:
 *
 *   1) nums[i] == nums[j]
 *   2) |i - j| <= k
 *
 * Otherwise, return false.
 *
 * -------------------------------------------------------------
 * Key Insight:
 * Only elements within a distance of k from the current index
 * are relevant. Any element older than k distance automatically
 * violates the condition and can be discarded.
 *
 * This class demonstrates:
 *   1) Brute force approach (O(n^2))
 *   2) Optimized sliding window approach using HashSet (O(n))
 *
 * -------------------------------------------------------------
 * Sliding Window Invariant (Optimized Approach):
 * At index i, the window contains elements from indices:
 *
 *      [i - k, i - 1]
 *
 * If a duplicate exists inside this window, the distance condition
 * (|i - j| <= k) is guaranteed.
 *
 * -------------------------------------------------------------
 * Time & Space Complexity:
 *
 * Brute Force:
 *   - Time:  O(n^2)
 *   - Space: O(1)
 *
 * Sliding Window:
 *   - Time:  O(n)
 *   - Space: O(k)
 *
 * =============================================================
 */
public class ContainsDuplicatesINRange {

    /**
     * Driver method to test both implementations.
     */
    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 1};

        boolean containsNearbyDuplicateBrute =
                containsNearbyDuplicateBrute(nums, 3);
        System.out.println(containsNearbyDuplicateBrute);

        boolean containsNearbyDuplicateSlidingWindow =
                containsNearbyDuplicateSlidingWindow(nums, 3);
        System.out.println(containsNearbyDuplicateSlidingWindow);
    }

    /**
     * -------------------------------------------------------------
     * Brute Force Approach
     * -------------------------------------------------------------
     *
     * Compares every pair of elements (i, j) and checks:
     *   - nums[i] == nums[j]
     *   - |i - j| <= k
     *
     * This approach is easy to understand but inefficient for
     * large inputs due to quadratic time complexity.
     *
     * @param nums input array of integers
     * @param k    maximum allowed index distance
     * @return true if a duplicate exists within distance k
     */
    public static boolean containsNearbyDuplicateBrute(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    if (Math.abs(i - j) <= k) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * -------------------------------------------------------------
     * Optimized Approach: Sliding Window + HashSet
     * -------------------------------------------------------------
     *
     * Maintains a sliding window of at most k elements using a HashSet.
     *
     * Algorithm:
     *   - Iterate through the array
     *   - If the current element already exists in the window,
     *     a duplicate within distance k is found
     *   - Add current element to the window
     *   - Remove the element that falls out of the window (index i - k)
     *
     * Why it works:
     *   - The window only contains elements from the last k indices
     *   - Any duplicate found inside the window must satisfy |i - j| <= k
     *
     * @param nums input array of integers
     * @param k    maximum allowed index distance
     * @return true if a duplicate exists within distance k
     */
    public static boolean containsNearbyDuplicateSlidingWindow(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {

            // Check if duplicate exists in current window
            if (window.contains(nums[i])) {
                return true;
            }

            // Add current element to window
            window.add(nums[i]);

            // Maintain window size of at most k
            if (window.size() > k) {
                window.remove(nums[i - k]);
            }
        }
        return false;
    }
}
