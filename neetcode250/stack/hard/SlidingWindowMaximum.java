package stack.hard;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * SLIDING WINDOW MAXIMUM
 * =====================
 *
 * Problem Statement:
 * ------------------
 * Given an integer array `nums` and an integer `k`,
 * return an array of the maximum values in every
 * contiguous subarray (sliding window) of size `k`.
 *
 *
 * Example:
 * --------
 * Input:
 *   nums = [1,3,-1,-3,5,3,6,7]
 *   k = 3
 *
 * Output:
 *   [3, 3, 5, 5, 6, 7]
 *
 *
 * APPROACHES IMPLEMENTED:
 * ----------------------
 * 1. Brute Force
 * 2. Optimized Monotonic Deque (often called "stack" approach)
 *
 *
 * KEY OBSERVATION:
 * ----------------
 * Brute force works but is too slow for large inputs.
 * The optimal solution uses a MONOTONIC DEQUE to achieve O(n).
 *
 *
 * PATTERN USED:
 * -------------
 * Sliding Window + Monotonic Deque (Decreasing Order)
 *
 *
 * @author Charu
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {

        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};

        // Brute force solution
        int[] brute = maxSlidingWindowBrute(nums, 3);
        System.out.println(Arrays.toString(brute));

        // Optimized monotonic deque solution
        int[] optimized = maxSlidingWindowStack(nums, 3);
        System.out.println(Arrays.toString(optimized));
    }

    /**
     * APPROACH 1: Brute Force
     * ----------------------
     *
     * Idea:
     * -----
     * - For every window of size k:
     *     - Traverse the k elements
     *     - Find the maximum
     *
     * Time Complexity:
     * ----------------
     * O(n * k)
     *
     * Space Complexity:
     * -----------------
     * O(1) (excluding output array)
     *
     * Limitations:
     * ------------
     * - Too slow for large inputs (n up to 10^5)
     * - Will cause TLE on LeetCode
     */
    public static int[] maxSlidingWindowBrute(int[] nums, int k) {

        int[] result = new int[nums.length - k + 1];

        // Iterate over each window
        for (int i = 0; i <= nums.length - k; i++) {

            int max = nums[i];

            // Find max in current window
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }

            result[i] = max;
        }

        return result;
    }

    /**
     * APPROACH 2: Monotonic Deque (Optimized)
     * ---------------------------------------
     *
     * Core Idea:
     * ----------
     * Maintain a deque of INDICES such that:
     * - Values are in decreasing order
     * - Front of deque always holds the index of the maximum element
     *
     * Why indices (not values)?
     * -------------------------
     * - To easily remove elements that go out of the sliding window
     *
     *
     * DEQUE OPERATIONS:
     * -----------------
     * - Remove from FRONT:
     *     → when an index goes out of the window
     *
     * - Remove from BACK:
     *     → when current element is greater than elements at the back
     *       (they will never become maximum again)
     *
     * - Insert at BACK:
     *     → current index
     *
     *
     * Time Complexity:
     * ----------------
     * O(n)
     *
     * Space Complexity:
     * -----------------
     * O(k) for deque
     *
     * This is the INTERVIEW-EXPECTED solution.
     */
    public static int[] maxSlidingWindowStack(int[] nums, int k) {

        int n = nums.length;
        int[] result = new int[n - k + 1];

        // Deque stores indices of elements
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < n; i++) {

            // 1️⃣ Remove indices that are out of the current window
            // Window range: [i - k + 1, i]
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // 2️⃣ Remove smaller elements from the back
            // They cannot be maximum if current element is larger
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }

            // 3️⃣ Add current index at the back
            dq.offerLast(i);

            // 4️⃣ Record maximum once the first window is formed
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peekFirst()];
            }
        }

        return result;
    }
}
