package binarySearch.medium;

/**
 * Find minimum element in rotated sorted array (without duplicates)
 * using answer variable.
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

    public static int findMin(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        int ans = Integer.MAX_VALUE;

        while (low <= high) {

            // If current window is already sorted
            if (nums[low] <= nums[high]) {
                ans = Math.min(ans, nums[low]);
                break;
            }

            int mid = low + (high - low) / 2;

            // Left half is sorted
            if (nums[low] <= nums[mid]) {

                ans = Math.min(ans, nums[low]);  // left smallest candidate
                low = mid + 1;

            } else {

                ans = Math.min(ans, nums[mid]);  // mid could be minimum
                high = mid - 1;
            }
        }

        return ans;
    }
}
