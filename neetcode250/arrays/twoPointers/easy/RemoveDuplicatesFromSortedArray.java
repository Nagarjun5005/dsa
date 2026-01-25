package arrays.twoPointers.easy;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Problem: Remove Duplicates from Sorted Array
 *
 * Given a SORTED integer array, remove the duplicates such that each element
 * appears only once and return the resulting array.
 *
 * Note:
 * - One approach uses extra space (brute force) → easy but not interview-optimal
 * - Another approach uses two pointers (in-place) → interview-expected solution
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {

        // Sample sorted input array
        int nums[] = {0,0,1,1,1,2,2,3,3,4};

        // Brute-force approach (extra space)
        // int[] removeDuplicates = removeDuplicatesBrute(nums);
        // System.out.println(Arrays.toString(removeDuplicates));

        // Two-pointer in-place approach
        int[] removeDuplicatesTwoPointers = removeDuplicatesTwoPointers(nums);
        System.out.println(Arrays.toString(removeDuplicatesTwoPointers));
    }

    /**
     * Brute Force Approach using HashSet
     *
     * Idea:
     * 1. Insert all elements into a HashSet to remove duplicates.
     * 2. Convert the HashSet back into an array.
     *
     * Drawbacks:
     * - Uses extra space
     * - Order is NOT guaranteed because HashSet is unordered
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[] removeDuplicatesBrute(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        // Step 1: Add elements to set (duplicates removed)
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        // Step 2: Convert set to array
        int[] result = new int[set.size()];
        int index = 0;
        for (int val : set) {
            result[index++] = val;
        }

        return result;
    }

    /**
     * Two Pointers Approach (IN-PLACE, OPTIMAL)
     *
     * Key Insight:
     * - Array is sorted, so duplicates are adjacent.
     *
     * Pointer roles:
     * - slow: index of last unique element
     * - fast: scans the array
     *
     * When nums[fast] != nums[slow],
     * - move slow forward
     * - overwrite nums[slow] with nums[fast]
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int[] removeDuplicatesTwoPointers(int[] nums) {

        // Edge case: empty array
        if (nums.length == 0) return new int[]{};

        int slow = 0;

        // fast pointer starts from second element
        for (int fast = 1; fast < nums.length; fast++) {

            // Found a new unique element
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }

        /*
         * After execution:
         * - Unique elements are stored from index 0 to slow
         * - Remaining elements beyond slow are irrelevant
         *
         * Example:
         * Input  -> [0,0,1,1,1,2,2,3,3,4]
         * Output -> [0,1,2,3,4,2,2,3,3,4]
         * Valid part is from index 0 to slow
         */

          return Arrays.copyOf(nums, slow + 1);
    }

}
