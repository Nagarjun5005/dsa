package arrays.hashing.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class demonstrates two approaches to solve the classic Two Sum problem:
 * 1. Brute Force (O(n²))
 * 2. Hashing (O(n))
 *
 * Given an array and a target sum, the goal is to find two indices i, j such that:
 * nums[i] + nums[j] == target.
 */
public class TwoSum {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4, 5};
        int target = 7;

        int[] bruteResult = twoSumBrute(nums, target);
        System.out.println("Brute Force Result : " + Arrays.toString(bruteResult));

        int[] hashingResult = twoSumHashing(nums, target);
        System.out.println("Hashing Result     : " + Arrays.toString(hashingResult));
    }

    /**
     * Brute Force Solution for Two Sum.
     *
     * Approach:
     * - Check every possible pair (i, j) such that i < j.
     * - If nums[i] + nums[j] == target, return indices.
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     *
     * @param nums   Input array of integers.
     * @param target Target sum.
     * @return Array of indices [i, j] whose values sum to the target.
     */
    public static int[] twoSumBrute(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};   // Found the pair
                }
            }
        }
        return new int[0];  // No pair found
    }

    /**
     * Optimal Hashing Solution for Two Sum.
     *
     * Approach:
     * - Use a HashMap to store values and their indices.
     * - For each number nums[i], compute the complement: target - nums[i].
     * - If the complement is already in the map, we found the pair.
     * - Else, store nums[i] in the map for future lookup.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Example:
     * nums = [1,2,3,4,5], target = 7
     * i = 0 → num = 1, complement = 6 → not found → store (1,0)
     * i = 1 → num = 2, complement = 5 → not found → store (2,1)
     * i = 2 → num = 3, complement = 4 → not found → store (3,2)
     * i = 3 → num = 4, complement = 3 → FOUND at index 2 → answer = [2,3]
     *
     * @param nums   Input array of integers.
     * @param target Target sum.
     * @return Array of indices [i, j] whose values sum to the target.
     */
    public static int[] twoSumHashing(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            // If complement exists in the map, return the pair
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            // Store current value and index in map
            map.put(nums[i], i);
        }

        return new int[0];  // No pair found
    }
}
