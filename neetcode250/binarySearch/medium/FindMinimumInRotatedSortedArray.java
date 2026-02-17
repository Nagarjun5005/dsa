package binarySearch.medium;

/**
 * File Name: FindMinimumInRotatedSortedArray.java
 *
 * Problem:
 * Given a rotated sorted array (without duplicates),
 * find the minimum element.
 *
 * Example:
 * Original Sorted: [0,1,2,4,5,6,7]
 * Rotated:         [4,5,6,7,0,1,2]
 *
 * Goal:
 * Return the smallest element in O(log n) time.
 *
 * Approach (Modified Binary Search):
 *
 * 1. If current subarray is already sorted (nums[low] <= nums[high]),
 *    then nums[low] is the minimum.
 *
 * 2. Otherwise, check which half is sorted:
 *
 *    - If left half is sorted:
 *         minimum must be in right half.
 *
 *    - If right half is sorted:
 *         minimum must be in left half (including mid).
 *
 * 3. Continue narrowing the search range.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */

public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {

        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        int min = findMin(nums);

        System.out.println("Minimum element: " + min);
    }

    /**
     * Returns the minimum element in a rotated sorted array.
     */
    public static int findMin(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            // If subarray is already sorted
            if (nums[low] <= nums[high]) {
                return nums[low];
            }

            int mid = low + (high - low) / 2;

            // Left half is sorted
            if (nums[low] <= nums[mid]) {
                // Minimum must be in right half
                low = mid + 1;
            } else {
                // Minimum lies in left half (including mid)
                high = mid;
            }
        }

        return -1; // Should never reach here for valid input
    }
}
