package neetcode250.binarySearch.easy;

/**
 * This class demonstrates a recursive implementation of the Binary Search algorithm.
 * Binary Search is used to find the index of a target value in a sorted array.
 * It works by repeatedly dividing the search interval in half.
 */
public class BinarySearchRecursive {

    /**
     * Public method to initiate the recursive binary search.
     *
     * @param arr The sorted array of integers.
     * @param x   The target value to search for.
     * @return The index of the target value if found, otherwise -1.
     */
    public static int binarySearchRecursive(int arr[], int x) {
        return binarySearchHelper(arr, x, 0, arr.length - 1);
    }

    /**
     * Recursive helper method for binary search.
     *
     * @param arr   The sorted array of integers.
     * @param x     The target value to search for.
     * @param start The starting index of the current search space.
     * @param end   The ending index of the current search space.
     * @return The index of the target value if found, otherwise -1.
     */
    public static int binarySearchHelper(int arr[], int x, int start, int end) {
        // Base case: if search space is invalid, return -1
        if (start > end) {
            return -1;
        }

        // Compute mid index (safe from overflow)
        int mid = start + (end - start) / 2;

        // Check if the mid element is the target
        if (arr[mid] == x) {
            return mid;
        }

        // If target is smaller than mid element, search left half
        if (arr[mid] > x) {
            return binarySearchHelper(arr, x, start, mid - 1);
        }

        // Else, search right half
        return binarySearchHelper(arr, x, mid + 1, end);
    }

    /**
     * Main method to run and test the binary search.
     */
    public static void main(String[] args) {
        int arr[] = {10, 22, 31, 43, 55};  // Sorted array
        int x = 43;                        // Target value to search
        int result = binarySearchRecursive(arr, x);

        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found in the array.");
        }
    }
}
