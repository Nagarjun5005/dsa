package arrays.arrays.medium;

/**
 * This class implements the Merge Sort algorithm to sort an array of integers.
 * Merge Sort is a divide-and-conquer algorithm with a time complexity of O(n log n).
 */
public class SortArrayMerge {

    /**
     * Main method to demonstrate sorting an array using Merge Sort.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        int[] arr = {6, 3, 9, 5, 2, 8};

        System.out.println("Original Array:");
        printArray(arr);

        // Sort the array using merge sort
        conquer(arr, 0, arr.length - 1);

        System.out.println("Sorted Array:");
        printArray(arr);
    }

    /**
     * Divide step: recursively divides the array into smaller subarrays.
     *
     * @param arr   the array to sort
     * @param start the starting index of the current subarray
     * @param end   the ending index of the current subarray
     */
    public static void conquer(int[] arr, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;

            // Recursively divide the left half
            conquer(arr, start, mid);

            // Recursively divide the right half
            conquer(arr, mid + 1, end);

            // Merge the divided subarrays
            merge(arr, start, mid, end);
        }
    }

    /**
     * Merge step: merges two sorted subarrays into a single sorted subarray.
     *
     * @param arr   the array containing the subarrays to merge
     * @param start the starting index of the first subarray
     * @param mid   the ending index of the first subarray
     * @param end   the ending index of the second subarray
     */
    private static void merge(int[] arr, int start, int mid, int end) {
        int[] merged = new int[end - start + 1];

        int idx1 = start;     // Pointer for the first subarray
        int idx2 = mid + 1;   // Pointer for the second subarray
        int x = 0;            // Pointer for the merged array

        // Merge the two subarrays into the merged array
        while (idx1 <= mid && idx2 <= end) {
            if (arr[idx1] <= arr[idx2]) {
                merged[x++] = arr[idx1++];
            } else {
                merged[x++] = arr[idx2++];
            }
        }

        // Copy remaining elements from the first subarray
        while (idx1 <= mid) {
            merged[x++] = arr[idx1++];
        }

        // Copy remaining elements from the second subarray
        while (idx2 <= end) {
            merged[x++] = arr[idx2++];
        }

        // Copy merged array back to the original array
        for (int i = 0, j = start; i < merged.length; i++, j++) {
            arr[j] = merged[i];
        }
    }

    /**
     * Utility method to print the elements of an array.
     *
     * @param arr the array to print
     */
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
