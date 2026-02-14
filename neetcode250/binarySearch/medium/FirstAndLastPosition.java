package binarySearch.medium;

/**
 * File Name: FirstAndLastPosition.java
 *
 * Problem:
 * Given a sorted array of integers, find the starting and ending
 * position of a given target value.
 *
 * If the target is not found, return [-1, -1].
 *
 * Approach:
 * We use Lower Bound and Upper Bound.
 *
 * Lower Bound  -> First index where element >= target
 * Upper Bound  -> First index where element > target
 *
 * Final Range:
 * start = lowerBound
 * end   = upperBound - 1
 *
 * Time Complexity  : O(log n)
 * Space Complexity : O(1)
 */

public class FirstAndLastPosition {

    public static void main(String[] args) {

        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

        int[] result = searchRange(nums, target);

        System.out.println("First Position : " + result[0]);
        System.out.println("Last Position  : " + result[1]);
    }


    /**
     * Returns the first and last occurrence of target.
     *
     * If target is not present, returns [-1, -1]
     */
    public static int[] searchRange(int[] nums, int target) {

        int n = nums.length;

        int lb = lowerBound(nums, target);

        // If target does not exist
        if (lb == n || nums[lb] != target) {
            return new int[]{-1, -1};
        }

        int ub = upperBound(nums, target);

        return new int[]{lb, ub - 1};
    }


    /**
     * Lower Bound:
     * Returns first index where element >= target
     *
     * If no such element exists, returns arr.length
     */
    public static int lowerBound(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (arr[mid] >= target) {
                ans = mid;
                high = mid - 1;  // move left
            } else {
                low = mid + 1;   // move right
            }
        }

        return ans;
    }


    /**
     * Upper Bound:
     * Returns first index where element > target
     *
     * If no such element exists, returns arr.length
     */
    public static int upperBound(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (arr[mid] > target) {
                ans = mid;
                high = mid - 1;  // move left
            } else {
                low = mid + 1;   // move right
            }
        }

        return ans;
    }
}
