package arrays.hashing.medium;

import java.util.HashMap;

/**
 * This class implements two approaches (Brute Force and Optimized) to solve the
 * "Continuous Subarray Sum" problem (LeetCode 523).
 *
 * <p>Problem Summary:
 * Given an integer array `nums` and an integer `k`, return true if there exists
 * a continuous subarray of size at least 2 whose sum is divisible by `k`.
 *
 * <p>Formally, for some subarray nums[i..j] (where j - i + 1 >= 2), check:
 *      (nums[i] + nums[i+1] + ... + nums[j]) % k == 0
 *
 * <p>Two methods are provided:
 * <ul>
 *     <li><b>Brute Force (O(n²))</b> – Compute all subarray sums
 *     <li><b>Optimized (O(n))</b> – Using Prefix Sum + Remainder HashMap trick
 * </ul>
 *
 * <p>This file also demonstrates the key mathematical insight:
 * If two prefix sums have the same remainder modulo k,
 * then the subarray between them has sum divisible by k.
 */
public class ContinuousSubarraySum {

    public static void main(String[] args) {

        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;

        System.out.println(checkSubarraySumBrute(nums, k));     // true
        System.out.println(checkSubarraySumOptimal(nums, k));   // true
    }

    /**
     * Brute Force approach to check if any continuous subarray of size ≥ 2
     * has sum divisible by k.
     *
     * <p><b>Logic:</b>
     * <ul>
     *     <li>Fix the starting index i
     *     <li>Extend the subarray by increasing j (j = i+1 ensures size ≥ 2)
     *     <li>Keep a running sum
     *     <li>If k == 0 → Only sum == 0 is valid (since only 0 is a multiple of 0)
     *     <li>Else check sum % k == 0
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(n²)
     * <br><b>Space Complexity:</b> O(1)
     */
    public static boolean checkSubarraySumBrute(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {

            int sum = nums[i];

            // j starts from i+1 → ensures subarray length ≥ 2
            for (int j = i + 1; j < nums.length; j++) {

                sum += nums[j];

                // Special case: k == 0
                if (k == 0) {
                    if (sum == 0) return true;
                }
                else {
                    if (sum % k == 0) return true;
                }
            }
        }
        return false;
    }

    /**
     * Optimized solution using Prefix Sum + Remainder HashMap (O(n)).
     *
     * <p><b>Key Intuition:</b>
     * If prefixSum[j] % k == prefixSum[i] % k and (j - i ≥ 2),
     * then the subarray nums[i+1..j] has sum divisible by k.
     *
     * <p><b>Algorithm:</b>
     * <ol>
     *     <li>Compute running prefix sum
     *     <li>Compute remainder mod k (special handling if k == 0)
     *     <li>If this remainder was seen before:
     *          check if distance ≥ 2 → valid subarray
     *     <li>Else store the FIRST index where this remainder appears
     * </ol>
     *
     * <p><b>Why store only the FIRST index?</b>
     * Storing earliest occurrence gives the longest possible subarray.
     *
     * <p><b>Time Complexity:</b> O(n)
     * <br><b>Space Complexity:</b> O(n)
     */
    public static boolean checkSubarraySumOptimal(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Important: remainder 0 is considered to appear at index -1
        // This handles cases where subarray starts from index 0
        map.put(0, -1);

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];

            // When k == 0 → we cannot use modulo operator
            int mod = (k == 0) ? sum : sum % k;

            // If the remainder was seen before
            if (map.containsKey(mod)) {

                // Ensure subarray size ≥ 2
                if (i - map.get(mod) >= 2) {
                    return true;
                }

            } else {
                // Store first occurrence only
                map.put(mod, i);
            }
        }

        return false;
    }
}
