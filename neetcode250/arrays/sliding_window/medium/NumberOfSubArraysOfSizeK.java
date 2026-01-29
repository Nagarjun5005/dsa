package arrays.sliding_window.medium;

/**
 * =============================================================
 * Problem: Number of Subarrays of Size K with Average >= Threshold
 * =============================================================
 *
 * Given an integer array arr, an integer k, and an integer threshold,
 * return the number of subarrays of size k whose average is greater
 * than or equal to threshold.
 *
 * -------------------------------------------------------------
 * Sliding Window Pattern Used:
 *
 * Pattern 1: Fixed Window (Size = K)
 *
 * Since the window size is constant, we slide the window one step
 * at a time while maintaining the sum of elements inside the window.
 *
 * -------------------------------------------------------------
 * Approaches Demonstrated:
 *
 * 1) Brute Force
 *    - Compute sum for each window by iterating again
 *
 * 2) Prefix Sum Optimization
 *    - Precompute cumulative sums to get window sum in O(1)
 *
 * 3) Sliding Window (Optimal)
 *    - Maintain a running sum while sliding the window
 *
 * -------------------------------------------------------------
 * Time & Space Complexity:
 *
 * Brute Force:
 *   - Time:  O(n * k)
 *   - Space: O(1)
 *
 * Prefix Sum:
 *   - Time:  O(n)
 *   - Space: O(n)
 *
 * Sliding Window:
 *   - Time:  O(n)
 *   - Space: O(1)
 *
 * =============================================================
 */
public class NumberOfSubArraysOfSizeK {

    /**
     * Driver method to test all three approaches.
     */
    public static void main(String[] args) {
        int nums[] = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;

        int numOfSubArrays = numOfSubArraysBrute(nums, k, threshold);
        System.out.println(numOfSubArrays);

        int numOfSubArraysBetter = numOfSubArraysBetter(nums, k, threshold);
        System.out.println(numOfSubArraysBetter);

        int numOfSubarraysSlidingWindow =
                numOfSubarraysSlidingWindow(nums, k, threshold);
        System.out.println(numOfSubarraysSlidingWindow);
    }

    /**
     * -------------------------------------------------------------
     * Approach 1: Brute Force
     * -------------------------------------------------------------
     *
     * For every subarray of size k:
     *   - Compute its sum using an inner loop
     *   - Check if average >= threshold
     *
     * @param arr input array
     * @param k window size
     * @param threshold minimum average required
     * @return number of valid subarrays
     */
    public static int numOfSubArraysBrute(int[] arr, int k, int threshold) {

        int res = 0, l = 0;

        for (int r = k - 1; r < arr.length; r++) {
            int sum = 0;

            // Calculate sum of current window [l..r]
            for (int i = l; i <= r; i++) {
                sum += arr[i];
            }

            if (sum / k >= threshold) {
                res++;
            }
            l++; // move window
        }
        return res;
    }

    /**
     * -------------------------------------------------------------
     * Approach 2: Prefix Sum Optimization
     * -------------------------------------------------------------
     *
     * Uses a prefix sum array where:
     *   prefixSum[i] = sum of elements from index 0 to i-1
     *
     * Sum of window [left..right] can be computed in O(1):
     *   sum = prefixSum[right + 1] - prefixSum[left]
     *
     * @param arr input array
     * @param k window size
     * @param threshold minimum average required
     * @return number of valid subarrays
     */
    public static int numOfSubArraysBetter(int[] arr, int k, int threshold) {

        int[] prefixSum = new int[arr.length + 1];

        // Build prefix sum array
        for (int i = 0; i < arr.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }

        int res = 0;
        int left = 0;

        for (int right = k - 1; right < arr.length; right++) {

            int sum = prefixSum[right + 1] - prefixSum[left];

            if (sum / k >= threshold) {
                res++;
            }
            left++;
        }
        return res;
    }

    /**
     * -------------------------------------------------------------
     * Approach 3: Sliding Window (Optimal)
     * -------------------------------------------------------------
     *
     * Maintains a running sum of the current window of size k.
     *
     * Algorithm:
     *   1) Pre-calculate sum of first (k - 1) elements
     *   2) Slide window by:
     *      - Adding the next element
     *      - Checking average
     *      - Removing the leftmost element
     *
     * Sliding Window Invariant:
     *   - Window size is always k
     *   - curSum always represents the sum of the current window
     *
     * @param arr input array
     * @param k window size
     * @param threshold minimum average required
     * @return number of valid subarrays
     */
    public static int numOfSubarraysSlidingWindow(int[] arr, int k, int threshold) {

        int res = 0;
        int curSum = 0;

        // Build sum of first (k - 1) elements
        for (int i = 0; i < k - 1; i++) {
            curSum += arr[i];
        }

        // Slide the window
        for (int L = 0; L <= arr.length - k; L++) {

            // Add rightmost element
            curSum += arr[L + k - 1];

            // Check average condition
            if ((curSum / k) >= threshold) {
                res++;
            }

            // Remove leftmost element
            curSum -= arr[L];
        }
        return res;
    }
}
