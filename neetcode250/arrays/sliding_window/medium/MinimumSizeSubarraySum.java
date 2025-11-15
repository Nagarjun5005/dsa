package arrays.sliding_window.medium;

/**
 * Minimum Size Subarray Sum (LeetCode 209)
 *
 * Given an array of positive integers and a target sum,
 * find the minimal length of a contiguous subarray
 * whose sum is greater than or equal to the target.
 * If no such subarray exists, return 0.
 *
 * Example:
 * Input:  [2, 3, 1, 2, 4, 3], target = 7
 * Output: 2   (subarray = [4,3])
 *
 * This file contains:
 * 1. Brute Force Solution — O(n^2) time, O(1) space
 * 2. Optimal Sliding Window Solution — O(n) time, O(1) space
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int arr[] = {2, 3, 1, 2, 4, 3};

        // Brute Force
        // int brute = minimumSizeSubarraySumBrute(arr, 7);
        // System.out.println(brute);

        // Optimal Sliding Window
        int optimal = minimumSizeSubarraySumOptimal(arr, 7);
        System.out.println(optimal);  // Expected: 2
    }

    /**
     * BRUTE FORCE APPROACH
     * ---------------------
     * Try every possible subarray using two loops.
     * For each starting index i, expand j from i to end,
     * keep adding arr[j] and check when sum >= target.
     *
     * Time Complexity:  O(n^2)
     * Space Complexity: O(1)
     *
     * @param arr input array of positive integers
     * @param target required sum
     * @return minimum subarray size whose sum >= target, else 0
     */
    public static int minimumSizeSubarraySumBrute(int arr[], int target) {
        int minLength = Integer.MAX_VALUE;
        int n = arr.length;

        // Outer loop → start index of subarray
        for (int i = 0; i < n; i++) {
            int sum = 0;

            // Inner loop → end index of subarray
            for (int j = i; j < n; j++) {
                sum += arr[j]; // keep adding elements inside the window

                // As soon as sum reaches target, update result
                if (sum >= target) {
                    minLength = Math.min(minLength, j - i + 1);
                    break; // breaking improves average case
                }
            }
        }
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }

    /**
     * OPTIMAL SLIDING WINDOW APPROACH
     * --------------------------------
     * This works because all numbers are positive.
     *
     * The idea:
     * 1. Expand window by moving 'right' pointer.
     * 2. Keep adding arr[right] to the window sum.
     * 3. Once sum >= target:
     *      - record window length
     *      - shrink window by moving 'left'
     *      - subtract arr[left] from sum
     * 4. Continue until right reaches end.
     *
     * Why O(n)?
     * Because each pointer (left & right) moves forward
     * at most 'n' steps → total operations = 2n.
     *
     * Time Complexity:  O(n)
     * Space Complexity: O(1)
     *
     * @param arr input array of positive integers
     * @param target required sum
     * @return minimum subarray size whose sum >= target, else 0
     */
    public static int minimumSizeSubarraySumOptimal(int arr[], int target) {
        int res = Integer.MAX_VALUE;
        int n = arr.length;
        int left = 0;       // start of sliding window
        int sum = 0;        // running sum of current window

        // Expand window by moving right pointer
        for (int right = 0; right < n; right++) {
            sum += arr[right];

            // Shrink window while it satisfies condition sum >= target
            while (sum >= target) {
                // Update minimum window size
                res = Math.min(res, right - left + 1);

                // Remove left element to shrink the window
                sum -= arr[left];
                left++;
            }
        }

        return (res == Integer.MAX_VALUE) ? 0 : res;
    }
}
