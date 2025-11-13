package arrays.arrays.medium;

import java.util.*;

/**
 * Problem:
 * Given an integer array nums, return all the unique triplets [nums[i], nums[j], nums[k]] such that:
 * - i != j, i != k, and j != k
 * - nums[i] + nums[j] + nums[k] == 0
 *
 * Note:
 * The solution set must not contain duplicate triplets.
 */
public class ThreeSum {

    /**
     * Brute-force approach to solve the 3Sum problem.
     * Time Complexity: O(n^3)
     * Space Complexity: O(n) due to storing unique triplets in a HashSet.
     *
     * Approach:
     * - Try every combination of three different elements.
     * - For each combination, check if the sum is zero.
     * - Sort the triplet before adding it to the result set to handle duplicates.
     * - Use a HashSet to automatically filter out duplicate triplets.
     */
    public static List<List<Integer>> threeSumBrute(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();

        // Try every possible triplet
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    // If the sum of the triplet is zero, add it to the result set
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                        Collections.sort(list); // Sort to avoid duplicates like [1, -1, 0] vs [-1, 0, 1]
                        res.add(list); // HashSet ensures uniqueness
                    }
                }
            }
        }

        return new ArrayList<>(res); // Convert Set to List before returning
    }

    /**
     * Optimal solution using Sorting + Two Pointer technique.
     * Time Complexity: O(n^2)
     * Space Complexity: O(1) extra space (excluding the output list).
     *
     * Approach:
     * - Sort the array.
     * - Fix one number and use two pointers to find the other two numbers.
     * - Skip duplicate elements to avoid duplicate triplets.
     */
    public static List<List<Integer>> threeSumBruteOptimal(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Traverse the array and fix the first element
        for (int i = 0; i < nums.length; i++) {

            // Early exit: Since the array is sorted, no valid triplet can be formed if nums[i] > 0
            if (nums[i] > 0) break;

            // Skip duplicate elements for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // Skip to the next iteration
            }

            int left = i + 1; // Initialize the left pointer
            int right = nums.length - 1; // Initialize the right pointer

            // Step 3: Two-pointer search
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Valid triplet found
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    left++;
                    right--;

                    // Skip duplicate elements for the second number
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip duplicate elements for the third number
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                } else if (sum < 0) {
                    // If sum is too small, move the left pointer to the right to increase the sum
                    left++;
                } else {
                    // If sum is too large, move the right pointer to the left to decrease the sum
                    right--;
                }
            }
        }

        return result; // Return the list of unique triplets
    }

    public static void main(String[] args) {
        int arr[] = {-1, 0, 1, 2, -1, -4};

        // Run brute-force solution
        List<List<Integer>> lists = threeSumBrute(arr);
        System.out.println("Brute Force Result: " + lists);

        // Run optimal solution
        List<List<Integer>> lists1 = threeSumBruteOptimal(arr);
        System.out.println("Optimal Result: " + lists1);
    }
}
