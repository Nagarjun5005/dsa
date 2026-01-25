package arrays.twoPointers.easy;

import java.util.Arrays;

/**
 * Problem: Move Zeroes
 *
 * Given an integer array, move all 0's to the end
 * while maintaining the relative order of non-zero elements.
 *
 * Constraints:
 * - Must be done in-place
 * - Minimize extra space usage
 */
public class MoveZeros {

    public static void main(String[] args) {

        // Sample input
        int[] nums = {0, 1, 0, 3, 12};

        // Uncomment to test brute-force approach
        // moveZeroesBrute(nums);

        // Two-pointer optimal approach
        moveZeroesTwoPointer(nums);

        // Print final result
        System.out.println(Arrays.toString(nums));
    }

    /**
     * Brute Force / Overwrite Approach
     *
     * Idea:
     * 1. Move all non-zero elements to the front of the array.
     * 2. Keep track of the index where the next non-zero should be placed.
     * 3. Fill the remaining positions with zero.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    private static void moveZeroesBrute(int[] nums) {

        int index = 0;

        // Step 1: Copy non-zero elements to the front
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }

        // Step 2: Fill remaining positions with zero
        for (int i = index; i < nums.length; i++) {
            nums[index] = 0;
            index++;
        }
    }

    /**
     * Two Pointer (Swap) Approach
     *
     * Pointers:
     * - start: points to the position where the next non-zero should go
     * - end: scans through the array
     *
     * Whenever a non-zero element is found, swap it with the element
     * at the start pointer and move start forward.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    private static void moveZeroesTwoPointer(int[] nums) {

        int start = 0;

        // Traverse array using end pointer
        for (int end = 0; end < nums.length; end++) {

            // If current element is non-zero, swap and move start
            if (nums[end] != 0) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
            }
        }
    }
}
