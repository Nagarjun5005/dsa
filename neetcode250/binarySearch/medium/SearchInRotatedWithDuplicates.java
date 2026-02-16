package binarySearch.medium;

/**
 * File Name: SearchInRotatedWithDuplicates.java
 *
 * Problem:
 * Search for a target value in a rotated sorted array that may contain duplicates.
 *
 * A rotated sorted array is formed by taking a sorted array and rotating it
 * at some pivot. Example:
 *
 *   Original:  [1, 2, 3, 4, 5, 6, 7]
 *   Rotated:   [5, 6, 7, 1, 2, 3, 4]
 *
 * When duplicates are present, determining the sorted half becomes ambiguous
 * in certain cases (e.g., when arr[low] == arr[mid] == arr[high]).
 *
 * Approach:
 * We modify standard binary search:
 *
 * 1. Calculate mid.
 * 2. If arr[mid] == target → return true.
 * 3. If arr[low] == arr[mid] == arr[high],
 *    shrink the search space (low++, high--).
 * 4. Otherwise, determine which half is sorted.
 * 5. Check whether target lies inside the sorted half.
 * 6. Narrow search boundaries accordingly.
 *
 * Time Complexity:
 *   Average Case: O(log n)
 *   Worst Case (all duplicates): O(n)
 *
 * Space Complexity: O(1)
 */
public class SearchInRotatedWithDuplicates {

    public static void main(String[] args) {

        int[] arr = {2, 5, 6, 0, 0, 1, 2};
        int target = 0;

        boolean found = searchInRotatedWithDuplicates(arr, target);

        System.out.println("Target " + target + " found: " + found);
    }

    /**
     * Searches for target in rotated sorted array with duplicates.
     *
     * @param arr    Rotated sorted integer array (may contain duplicates)
     * @param target Value to search
     * @return true if target exists, otherwise false
     */
    public static boolean searchInRotatedWithDuplicates(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {

            // Safe mid calculation to avoid overflow
            int mid = low + (high - low) / 2;

            // Case 1: Target found
            if (arr[mid] == target) {
                return true;
            }

            // Case 2: Cannot determine sorted half due to duplicates
            if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                low++;
                high--;
                continue;
            }

            // Case 3: Left half is sorted
            if (arr[low] <= arr[mid]) {

                // Check if target lies in left sorted half
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            // Case 4: Right half is sorted
            else {

                // Check if target lies in right sorted half
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return false;  // Target not found
    }
}
