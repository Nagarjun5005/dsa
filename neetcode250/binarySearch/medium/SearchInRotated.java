package binarySearch.medium;

/**
 * This class provides an implementation of binary search on a rotated sorted array.
 * A rotated sorted array is formed by taking a normally sorted array and rotating
 * it at some pivot. Example:
 *
 *   Original sorted array:  [1, 2, 3, 4, 5, 6, 7, 8, 9]
 *   Rotated version:        [7, 8, 9, 1, 2, 3, 4, 5, 6]
 *
 * The goal is to find the index of a given target value in O(log n) time.
 *
 * This algorithm works by:
 *  1. Finding the mid element.
 *  2. Determining which side (left or right half) is sorted.
 *  3. Checking if the target lies inside that sorted half.
 *  4. Narrowing the search boundaries accordingly.
 *
 * This approach ensures logarithmic time complexity even though the array is rotated.
 */
public class SearchInRotated {

    public static void main(String[] args) {
        int arr[] = {7, 8, 9, 1, 2, 3, 4, 5, 6};

        int index = binarySearch(arr, 1);
        System.out.println("Target found at index: " + index);
    }

    /**
     * Searches for a target value in a rotated sorted array using modified binary search.
     *
     * @param arr    The rotated sorted integer array.
     * @param target The value to search for.
     * @return Index of the target if found; otherwise, returns -1.
     *
     * <p><b>Algorithm Logic:</b></p>
     * <ol>
     *     <li>Use the standard binary search structure: compute mid = (low + high) / 2.</li>
     *     <li>If arr[mid] == target → return mid.</li>
     *     <li>Determine which half is sorted:
     *         <ul>
     *             <li>If arr[low] <= arr[mid] → left half is sorted.</li>
     *             <li>Otherwise → right half is sorted.</li>
     *         </ul>
     *     </li>
     *     <li>Check if the target lies within the sorted half:
     *         <ul>
     *             <li>If yes → move search within that half.</li>
     *             <li>If no → search in the other half.</li>
     *         </ul>
     *     </li>
     *     <li>Repeat until low > high. If not found → return -1.</li>
     * </ol>
     *
     * <p><b>Time Complexity:</b> O(log n)</p>
     * <p><b>Space Complexity:</b> O(1)</p>
     */
    public static int binarySearch(int arr[], int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            // If mid itself is the target, return index
            if (arr[mid] == target) {
                return mid;
            }

            // Check which side is sorted

            // Case 1: Left half is sorted
            if (arr[low] <= arr[mid]) {

                // If the target lies in the sorted left part → move left
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1;
                } else {
                    // Otherwise search in the right part
                    low = mid + 1;
                }

            }
            // Case 2: Right half is sorted
            else {

                // If the target lies in the sorted right part → move right
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    // Otherwise search in the left part
                    high = mid - 1;
                }
            }
        }

        return -1;  // Target not found
    }
}
