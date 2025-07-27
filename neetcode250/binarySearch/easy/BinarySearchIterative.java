package neetcode250.binarySearch.easy;

/**
 * This class demonstrates the binary search algorithm on a sorted integer array.
 * Binary search is an efficient algorithm for finding an item from a sorted list
 * by repeatedly dividing the search interval in half.
 */
public class BinarySearchIterative {

    /**
     * Performs binary search on a sorted array to find the target element.
     *
     * @param arr the sorted array of integers in ascending order
     * @param x   the target integer to search for
     * @return the index of the target element if found; otherwise, -1
     */
    public static int binarySearch(int[] arr, int x) {
        int start = 0;
        int end = arr.length - 1;

        // Loop runs as long as the search space is valid
        while (start <= end) { // ✅ Use <= to include the last remaining element

            // Calculate middle index safely to avoid integer overflow
            int mid = start + (end - start) / 2;

            // Check if the target element is found
            if (arr[mid] == x) {
                return mid;
            }
            // If target is greater, ignore left half
            else if (x > arr[mid]) {
                start = mid + 1;
            }
            // If target is smaller, ignore right half
            else {
                end = mid - 1;
            }
        }

        // Element not found
        return -1;
    }

    /**
     * Main method to test the binarySearch method.
     * Searches for a given number in a sorted array and prints the result.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50}; // Sorted array (required for binary search)
        int result = binarySearch(arr, 40); // Searching for the value 40

        if (result != -1) {
            System.out.println("Element was found at index: " + result);
        } else {
            System.out.println("Element not found in the array.");
        }
    }
}
