package neetcode250.binarySearch.medium;

/**
 * This class demonstrates how to find the first occurrence of a target
 * element in a sorted array using binary search.
 */
public class BinarySearchFirstOccurrence {

    /**
     * Finds the first occurrence index of the target in the sorted array.
     * @param arr The sorted input array
     * @param target The target value to search for
     * @return The index of the first occurrence, or -1 if not found
     */
    public static int firstOccurrence(int arr[], int target){
        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;
                // keep searching on the left side
                right = mid - 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;

        int occurrence = firstOccurrence(arr, target);
        System.out.println("First occurrence of " + target + " was found at index: " + occurrence);
    }
}
