package binarySearch.easy;

/**
 * The {@code SearchInsertPosition} class provides a method to find the index at which
 * a target value should be inserted into a sorted array.
 *
 * <p>This is a modified form of Binary Search where:
 * <ul>
 *     <li>If the target element exists in the array, its index is returned.</li>
 *     <li>If the target does not exist, the method returns the correct index
 *         where it should be inserted to maintain sorted order.</li>
 * </ul>
 *
 * <p>The algorithm works in O(log n) time using Binary Search, and relies on the fact
 * that after the search boundaries cross, the {@code left} pointer will always point to
 * the correct insertion position.</p>
 *
 * Example:
 * <pre>
 * Input array: [10, 20, 30, 40, 50]
 * Target: 24
 * Output: 2   // because 24 should be inserted between 20 and 30
 * </pre>
 *
 */
public class SearchInsertPosition {

    /**
     * Main method for testing the searchInsertPosition method.
     *
     * @param args runtime arguments (not used)
     */
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        int target = 24;

        int searchInsertPosition = searchInsertPosition(arr, target);
        System.out.println("The element should be inserted at : " + searchInsertPosition);
    }

    /**
     * Performs a modified binary search to find the index of the target value.
     * If the target is found, its index is returned.
     * If the target is not found, the method returns the index where the target
     * should be inserted in order to maintain the sorted order of the array.
     *
     * <p>Logic:
     * <ul>
     *     <li>Use binary search to compare the target with the middle element.</li>
     *     <li>If target is smaller, move the {@code right} pointer left.</li>
     *     <li>If target is larger, move the {@code left} pointer right.</li>
     *     <li>When {@code left > right}, the loop stops, and {@code left} becomes
     *         the correct insertion index.</li>
     * </ul>
     *
     * @param arr    a sorted array of integers (no duplicates required, but allowed)
     * @param target the value to search for or determine the insertion position of
     * @return the index at which the target value should be inserted
     *         (or the index where it already exists)
     */
    public static int searchInsertPosition(int arr[], int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            // Case 1: Target found → return index immediately
            if (arr[mid] == target) {
                return mid;
            }
            // Case 2: Target is smaller → move to left half
            else if (arr[mid] > target) {
                right = mid - 1;
            }
            // Case 3: Target is larger → move to right half
            else {
                left = mid + 1;
            }
        }

        /**
         * At this point the target is not found.
         * The 'left' pointer is positioned at the exact index where
         * the target should be inserted.
         */
        return left;
    }
}
