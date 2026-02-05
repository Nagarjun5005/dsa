package arrays.sliding_window.medium;

import java.util.HashSet;

/**
 * Maximum Erasure Value
 *
 * PROBLEM:
 * --------
 * Given an integer array nums, return the maximum sum of a subarray
 * that contains only unique elements.
 *
 * This class demonstrates:
 * 1. Brute Force approach
 * 2. Optimized Sliding Window approach
 */
public class MaximumErasureValue {

    public static void main(String[] args) {

        // Sample input
        int[] nums = {5, 2, 1, 2, 5, 2, 1, 2, 5};

        // Brute Force solution
        int maximumUniqueSubarrayBruteForce = maximumUniqueSubarrayBruteForce(nums);
        System.out.println(maximumUniqueSubarrayBruteForce);

        // Sliding Window solution
        int maximumUniqueSubarraySlidingWindow = maximumUniqueSubarraySlidingWindow(nums);
        System.out.println(maximumUniqueSubarraySlidingWindow);
    }

    /**
     * BRUTE FORCE APPROACH
     *
     * Idea:
     * -----
     * - Try every possible starting index
     * - Build subarray until a duplicate element appears
     * - Use HashSet to track unique elements
     * - Track sum of the current subarray
     *
     * Time Complexity:
     * ----------------
     * O(n²)
     *
     * Space Complexity:
     * -----------------
     * O(n)
     */
    public static int maximumUniqueSubarrayBruteForce(int[] nums) {

        int maxSum = 0;

        // Outer loop picks starting index
        for (int i = 0; i < nums.length; i++) {

            int currSum = 0;
            HashSet<Integer> set = new HashSet<>();

            // Inner loop extends subarray from index i
            for (int j = i; j < nums.length; j++) {

                // If duplicate found, stop extending
                if (set.contains(nums[j])) {
                    break;
                }

                // Add element to set and update sum
                set.add(nums[j]);
                currSum += nums[j];
            }

            // Update maximum sum found so far
            maxSum = Math.max(currSum, maxSum);
        }

        return maxSum;
    }

    /**
     * SLIDING WINDOW APPROACH (OPTIMIZED)
     *
     * Idea:
     * -----
     * - Use two pointers (left & right)
     * - Maintain a window of unique elements
     * - Expand window using right pointer
     * - Shrink window using left pointer when duplicate is found
     * - Maintain running sum of window elements
     *
     * Time Complexity:
     * ----------------
     * O(n)
     *
     * Space Complexity:
     * -----------------
     * O(n)
     */
    public static int maximumUniqueSubarraySlidingWindow(int[] nums) {

        int left = 0;          // Left boundary of window
        int currSum = 0;       // Sum of current window
        int maxSum = 0;        // Maximum sum found
        HashSet<Integer> set = new HashSet<>(); // Stores unique elements

        // Right pointer expands the window
        for (int right = 0; right < nums.length; right++) {

            // If duplicate found, shrink window from left
            while (set.contains(nums[right])) {
                set.remove(nums[left]);   // Remove left element
                currSum -= nums[left];    // Subtract from sum
                left++;                   // Move left pointer
            }

            // Add current element to window
            set.add(nums[right]);
            currSum += nums[right];

            // Update maximum sum
            maxSum = Math.max(currSum, maxSum);
        }

        return maxSum;
    }
}
