package binarySearch.medium;

/**
 * File Name: FindRotationsInRotatedSortedArray.java
 *
 * Problem:
 * Given an increasing sorted rotated array of distinct integers,
 * find the number of times the array has been rotated.
 *
 * Key Insight:
 * The number of rotations is equal to the index of the minimum element.
 *
 * Example:
 * Original Sorted: [0,1,2,4,5,6,7]
 * Rotated:         [4,5,6,7,0,1,2]
 *
 * Minimum element = 0
 * Index of 0 = 4
 * So, rotation count = 4
 *
 * Approach:
 * 1. Use modified binary search.
 * 2. If current window is already sorted (nums[low] <= nums[high]),
 *    then nums[low] is the minimum.
 * 3. Otherwise:
 *      - If left half is sorted → pivot lies in right half.
 *      - If right half is sorted → pivot lies in left half.
 * 4. Keep updating the minimum value and its index.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */

public class FindRotationsInRotatedSortedArray {

    public static void main(String[] args) {

        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        int rotations = findRotations(nums);

        System.out.println("Rotation count: " + rotations);
    }

    /**
     * Returns number of right rotations applied to the sorted array.
     * Equivalent to index of minimum element.
     */
    public static int findRotations(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        int ans = Integer.MAX_VALUE;
        int index = -1;

        while (low <= high) {

            // Case 1: Current segment already sorted
            if (nums[low] <= nums[high]) {

                if (nums[low] < ans) {
                    ans = nums[low];
                    index = low;
                }

                break;
            }

            int mid = low + (high - low) / 2;

            // Case 2: Left half is sorted
            if (nums[low] <= nums[mid]) {

                // Smallest in left half is nums[low]
                if (nums[low] < ans) {
                    ans = nums[low];
                    index = low;
                }

                // Pivot must be in right half
                low = mid + 1;
            }
            // Case 3: Right half is sorted
            else {

                // nums[mid] could be minimum
                if (nums[mid] < ans) {
                    ans = nums[mid];
                    index = mid;
                }

                // Pivot must be in left half
                high = mid - 1;
            }
        }

        return index;
    }
}
