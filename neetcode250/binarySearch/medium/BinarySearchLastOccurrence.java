package binarySearch.medium;

/**
 * This class finds the last occurrence of a given target
 * in a sorted array using binary search.
 */
public class BinarySearchLastOccurrence {

    /**
     * Performs binary search to find the last occurrence of a target in a sorted array.
     * @param arr Sorted array of integers (may contain duplicates)
     * @param target The value to find
     * @return The index of the last occurrence of target, or -1 if not found
     */
    public static int lastOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int res = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                res = mid;
                // Search on the right side for a later occurrence
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;

        int occurrence = lastOccurrence(arr, target);
        System.out.println("Last occurrence of " + target + " was found at index: " + occurrence);
    }
}
