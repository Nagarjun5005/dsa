package neetcode250.twoPointers.medium;

import java.util.Arrays;

/**
 * This class provides two implementations for solving the Two Sum II problem
 * on a sorted array using brute-force and two-pointer approaches.
 */
public class TwoSum2 {

    /**
     * Solves the Two Sum II problem using a brute-force approach.
     *
     * @param nums   A sorted integer array.
     * @param target The target sum to find.
     * @return An array containing 1-based indices of the two numbers that add up to the target,
     *         or an empty array if no such pair exists.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int[] twoSumBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i + 1, j + 1}; // 1-based index
                }
            }
        }
        return new int[0];
    }

    /**
     * Solves the Two Sum II problem using a two-pointer technique.
     * Assumes the input array is sorted in non-decreasing order.
     *
     * @param nums   A sorted integer array.
     * @param target The target sum to find.
     * @return An array containing 1-based indices of the two numbers that add up to the target,
     *         or an empty array if no such pair exists.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int[] twoSum2Pointer(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int currSum = nums[left] + nums[right];
            if (currSum > target) {
                right--;
            } else if (currSum < target) {
                left++;
            } else {
                return new int[]{left + 1, right + 1}; // 1-based index
            }
        }
        return new int[0];
    }

    /**
     * Demonstrates usage of the twoSumBrute and twoSum2Pointer methods.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] resultBrute = twoSumBrute(nums, target);
        System.out.println("Brute-force result: " + Arrays.toString(resultBrute));

        int[] resultOptimal = twoSum2Pointer(nums, target);
        System.out.println("Two-pointer result: " + Arrays.toString(resultOptimal));
    }
}
