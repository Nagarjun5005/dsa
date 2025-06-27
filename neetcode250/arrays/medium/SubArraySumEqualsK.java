package neetcode250.arrays.medium;

import java.util.HashMap;

/**
 * Problem: Subarray Sum Equals K
 *
 * Goal: Find the total number of continuous subarrays whose sum equals k.
 *
 * Approach:
 * 1. Brute Force: Try all possible subarrays and check their sums. Time: O(N^2)
 * 2. Optimized: Use Prefix Sum + HashMap to track subarray sums in O(N) time.
 */
public class SubArraySumEqualsK {

    /**
     * Brute Force Approach:
     * - Iterate through all possible subarrays using two nested loops.
     * - For each subarray, calculate the sum and check if it equals k.
     *
     * @param nums Array of integers
     * @param k Target sum
     * @return Total number of subarrays that sum to k
     */
    public static int subArraySumEqualsKBrute(int[] nums, int k) {
        int count = 0;

        // Outer loop: Start index of subarray
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;

            // Inner loop: End index of subarray
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j]; // Keep adding the next element to current sum

                if (currentSum == k) {
                    count++; // If the sum equals k, increase the count
                }
            }
        }
        return count; // Total number of subarrays that sum to k
    }

    /**
     * Optimized Approach: Prefix Sum + HashMap
     *
     * Explanation:
     * - Maintain a running prefix sum while traversing the array.
     * - For each element, check if (currentSum - k) exists in the map.
     *   If it does, that means there is a previous prefix sum which can be subtracted
     *   to get a subarray summing to k.
     * - Store the frequency of each prefix sum in the map.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     *
     * @param nums Array of integers
     * @param k Target sum
     * @return Total number of subarrays that sum to k
     */
    public static int subArraySumEqualsprefixSumHashing(int[] nums, int k) {
        int count = 0; // Total subarrays found
        int currentSum = 0; // Running prefix sum
        HashMap<Integer, Integer> map = new HashMap<>();

        // Base case: To handle subarrays starting from index 0
        map.put(0, 1);

        // Iterate through the array
        for (int num : nums) {
            currentSum += num;

            // Check if there is a prefix sum we can subtract to get sum = k
            if (map.containsKey(currentSum - k)) {
                count += map.get(currentSum - k);
            }

            // Record the frequency of the current prefix sum
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }

        return count; // Total number of subarrays that sum to k
    }

    /**
     * Main method to test both brute force and optimized approaches.
     */
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 1, 2, 3};

        // Brute Force Solution
        int subArraySumEqualsKBrute = subArraySumEqualsKBrute(arr, 3);
        System.out.println("Subarray sum count (Brute Force): " + subArraySumEqualsKBrute);

        // Optimized Solution using Prefix Sum + HashMap
        int sumHashing = subArraySumEqualsprefixSumHashing(arr, 3);
        System.out.println("Subarray sum count (Optimized): " + sumHashing);
    }
}
